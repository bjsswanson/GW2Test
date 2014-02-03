package com.swansonb.game.guildwars2.services;

import com.google.gson.*;
import com.swansonb.game.guildwars2.factory.HttpClientFactory;
import com.swansonb.game.guildwars2.factory.HttpRequestFactory;
import com.swansonb.game.guildwars2.utils.GWConstants;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.Iterator;

@Service
public class BuyerService {

	@Autowired
	HttpClientFactory httpClientFactory;

	@Autowired
	HttpRequestFactory httpRequestFactory;

	Gson gson;

	public BuyerService() {
		this.gson = new Gson();
	}

	public JsonObject getArmorListing(String session) throws IOException {
		String json = executeRequest(httpRequestFactory.getListing(session, GWConstants.ARMOR, GWConstants.MASTERWORK, 15, 20));
		return new JsonParser().parse(json).getAsJsonObject();
	}

	public JsonObject buyArmor(String session, JsonObject listing, int coins, int amount) {
		JsonArray items = listing.getAsJsonArray();

		Iterator<JsonElement> iterator = items.iterator();
		while(iterator.hasNext()){
			JsonElement item = iterator.next();

			if(item.isJsonObject()){
				int buyPrice = ((JsonObject) item).get("buyPrice").getAsInt();
				int sellPrice = ((JsonObject) item).get("sellPrice").getAsInt();

				if(coins > 0 && sellPrice > (buyPrice / 2)){

				}
			}
		}
		return null;
	}

	public String executeRequest(HttpUriRequest request) throws IOException {
		HttpClient client = httpClientFactory.getClient();
		HttpResponse execute = client.execute(request);
		return EntityUtils.toString(execute.getEntity());
	}

}
