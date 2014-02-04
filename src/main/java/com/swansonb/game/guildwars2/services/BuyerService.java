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

	public static final int COUNT = 3;
	public static final int MAX_PRICE = 10000;
	private static final int MIN_PRICE = 0;
	public static final int ADD_PRICE = 20;
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

	public String list(String session, int type, int rarity, int min_level, int max_level) throws IOException {
		if(StringUtils.isNotBlank(session)){
			return executeRequest(httpRequestFactory.getListing(session, type, rarity, min_level, max_level));
		} else {
			return MISSING_SESSION;
		}
	}

	public String buy(String session, int type, int rarity, int min_level, int max_level, int coins, int min_price, int max_price, int add_price, int count, float profit_margin) throws IOException {
		if(StringUtils.isNotBlank(session)){
			String json = executeRequest(httpRequestFactory.getListing(session, type, rarity, min_level, max_level));
			return buyFromJson(session, json, coins, min_price, max_price, add_price, count, profit_margin);
		} else {
			return MISSING_SESSION;
		}
	}

	private String buyFromJson(String session, String json, int coins, int min_price, int max_price, int add_price, int count, float profit_margin) throws IOException {
		JsonObject object = new JsonParser().parse(json).getAsJsonObject();

		JsonArray results = object.getAsJsonArray("results");
		Iterator<JsonElement> iterator = results.iterator();

		JsonArray output = new JsonArray();

		while(iterator.hasNext()){
			JsonObject resObj = iterator.next().getAsJsonObject();
			int data_id = resObj.get("data_id").getAsInt();
			int buy_price = resObj.get("buy_price").getAsInt();
			int sell_price = resObj.get("sell_price").getAsInt();

			if(meetsBuyCriteria(coins, min_price, max_price, profit_margin, buy_price, sell_price)){
					executeRequest(httpRequestFactory.buyItem(session, data_id, count, buy_price + add_price));
					coins -= (buy_price + add_price) * count;
					resObj.addProperty("buy_price", buy_price + add_price);
					output.add(resObj);
			}
		}

		return output.toString();
	}

	private boolean meetsBuyCriteria(int coins, int min_price, int max_price, float profit_margin, int buy_price, int sell_price) {
		return ((buy_price * profit_margin)) < (float)sell_price &&
				buy_price > min_price &&
				buy_price < max_price &&
				coins >= 0;
	}

	public String executeRequest(HttpUriRequest request) throws IOException {
		HttpClient client = httpClientFactory.getClient();
		HttpResponse execute = client.execute(request);
		return EntityUtils.toString(execute.getEntity());
	}

}
