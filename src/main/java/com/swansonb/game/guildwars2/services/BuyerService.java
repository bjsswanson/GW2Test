package com.swansonb.game.guildwars2.services;

import com.google.gson.*;
import com.swansonb.game.guildwars2.factory.HttpClientFactory;
import com.swansonb.game.guildwars2.factory.HttpRequestFactory;
import com.swansonb.game.guildwars2.utils.GWConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;

@Service
public class BuyerService {

	public static final int MAX_BUY_PRICE = 10000;
	public static final int COUNT = 3;
	public static final int ADD_BUY_PRICE = 20;
	public static final String DONE = "Done.";
	private static final String MISSING_SESSION = "Missing Session";
	@Autowired
	HttpClientFactory httpClientFactory;

	@Autowired
	HttpRequestFactory httpRequestFactory;

	Gson gson;

	public BuyerService() {
		this.gson = new Gson();
	}

	public String getArmorMasterWork15to20(String session) throws IOException {
		if(StringUtils.isNotBlank(session)){
			return executeRequest(httpRequestFactory.getListing(session, GWConstants.ARMOR, GWConstants.MASTERWORK, 15, 20));
		} else {
			return MISSING_SESSION;
		}
	}

	public String buy(String session, int type, int rarity, int min_level, int max_level, int coins) throws IOException {
		if(StringUtils.isNotBlank(session)){
			String json = executeRequest(httpRequestFactory.getListing(session, type, rarity, min_level, max_level));
			return buyFromJson(session, json, coins);
		} else {
			return MISSING_SESSION;
		}
	}

	public String buyArmorMasterWork15to20(String session) throws IOException {
		if(StringUtils.isNotBlank(session)){
			String json = executeRequest(httpRequestFactory.getListing(session, GWConstants.ARMOR, GWConstants.MASTERWORK, 15, 20));
			return buyFromJson(session, json, Integer.MAX_VALUE);
		} else {
			return MISSING_SESSION;
		}
	}

	public String buyWeaponMasterWork15to20(String session) throws IOException {
		if(StringUtils.isNotBlank(session)){
			String json = executeRequest(httpRequestFactory.getListing(session, GWConstants.WEAPON, GWConstants.MASTERWORK, 15, 20));
			return buyFromJson(session, json, Integer.MAX_VALUE);
		} else {
			return MISSING_SESSION;
		}
	}

	private String buyFromJson(String session, String json, int coins) throws IOException {
		JsonObject object = new JsonParser().parse(json).getAsJsonObject();

		JsonArray results = object.getAsJsonArray("results");
		Iterator<JsonElement> iterator = results.iterator();

		JsonArray output = new JsonArray();

		while(iterator.hasNext()){
			JsonObject resObj = iterator.next().getAsJsonObject();
			int data_id = resObj.get("data_id").getAsInt();
			int buy_price = resObj.get("buy_price").getAsInt();
			int sell_price = resObj.get("sell_price").getAsInt();

			if(buy_price < MAX_BUY_PRICE && (buy_price * 2) < sell_price){
				if(coins >= 0){
					executeRequest(httpRequestFactory.buyItem(session, data_id, COUNT, buy_price + ADD_BUY_PRICE));
					coins -= (buy_price + ADD_BUY_PRICE) * COUNT;
					resObj.addProperty("buy_price", buy_price + ADD_BUY_PRICE);
					output.add(resObj);
				}
			}
		}

		return output.toString();
	}

	public String executeRequest(HttpUriRequest request) throws IOException {
		HttpClient client = httpClientFactory.getClient();
		HttpResponse execute = client.execute(request);
		return EntityUtils.toString(execute.getEntity());
	}

}
