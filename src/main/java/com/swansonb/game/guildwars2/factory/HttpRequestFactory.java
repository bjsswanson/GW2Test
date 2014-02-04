package com.swansonb.game.guildwars2.factory;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HttpRequestFactory {

	private static final int BUY = 1;
	private static final int SELL = 0;

	private static final String SEARCH_URI = "https://tradingpost-live.ncplatform.net/ws/search.json?";
	private static final String BUY_URI = "https://tradingpost-live.ncplatform.net/ws/item/";
	private static final String ORDERS_URL = "https://tradingpost-live.ncplatform.net/ws/me.json?";
	private static final String CANCEL_URL = "https://tradingpost-live.ncplatform.net/ws/item/10387/cancel.json?";

	public HttpGet getListing(String session, int type, int rarity, int levelmin, int levelmax){
		return get(getSearchUrl(type, rarity, levelmin, levelmax), session);
	}

	public HttpGet getBuyListings(String session) {
		return get(getOrdersUrl("buy"), session);
	}

	public HttpGet getSellListings(String session) {
		return get(getOrdersUrl("sell"), session);
	}

	public HttpPost cancelBuyOrder(String session, String listing){
		return post(getCancelUrl(listing, BUY), session);
	}

	public HttpPost cancelSellOrder(String session, String listing){
		return post(getCancelUrl(listing, SELL), session);
	}

	public HttpPost buyItem(String session, int item, int count, int price){
		HttpPost post = post(getBuyUrl(item), session);

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("count", Integer.toString(count)));
		nvps.add(new BasicNameValuePair("price", Integer.toString(price)));
		nvps.add(new BasicNameValuePair("charid", ""));

		post.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		return post;
	}

	private HttpGet get(String uri, String session){
		HttpGet get = new HttpGet(uri);

		get.setHeader("Accept", "*/*");
		get.setHeader("Accept-Charset", "iso-8859-1,*,utf-8");
		get.setHeader("Accept-Encoding", "gzip,deflate");
		get.setHeader("Accept-Language", "en");
		get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1003.1 Safari/535.19 Awesomium/1.7.1");
		get.setHeader("Connection", "keep-alive");
		get.setHeader("Host", "tradingpost-live.ncplatform.net");
		get.setHeader("Referer", "https://tradingpost-live.ncplatform.net/");
		get.setHeader("X-Requested-With", "XMLHttpRequest");
		get.setHeader("Cookie", "s=" + session);

		return get;
	}

	private HttpPost post(String uri, String session){
		HttpPost post = new HttpPost(uri);

		post.setHeader("Accept", "*/*");
		post.setHeader("Accept-Charset", "iso-8859-1,*,utf-8");
		post.setHeader("Accept-Encoding", "gzip,deflate");
		post.setHeader("Accept-Language", "en");
		post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1003.1 Safari/535.19 Awesomium/1.7.1");
		post.setHeader("Connection", "keep-alive");
		post.setHeader("Host", "tradingpost-live.ncplatform.net");
		post.setHeader("Referer", "https://tradingpost-live.ncplatform.net/");
		post.setHeader("X-Requested-With", "XMLHttpRequest");
		post.setHeader("Cookie", "s=" + session);

		return post;
	}

	private String getBuyUrl(int item){
		StringBuilder sb = new StringBuilder(BUY_URI);
		sb.append(item);
		sb.append("/buy");
		return sb.toString();
	}

	private String getCancelUrl(String listing, int isBuy){
		StringBuilder sb = new StringBuilder(CANCEL_URL);
		sb.append("listing=");
		sb.append(listing);
		sb.append("&isbuy=");
		sb.append(isBuy);
		sb.append("&charid=undefined");
		return sb.toString();
	}

	private String getOrdersUrl(String buySell){
		StringBuilder sb = new StringBuilder(ORDERS_URL);
		sb.append("time=now");
		sb.append("&type=");
		sb.append(buySell);
		sb.append("&charid=&count=0");
		return sb.toString();
	}

	private String getSearchUrl(int type, int rarity, int levelmin, int levelmax){
		StringBuilder sb = new StringBuilder(SEARCH_URI);
		sb.append("text=");
		sb.append("&type=");
		sb.append(type);
		sb.append("&rarity=");
		sb.append(rarity);
		sb.append("&levelmin=");
		sb.append(levelmin);
		sb.append("&levelmax=");
		sb.append(levelmax);
		sb.append("&offset=1");
		sb.append("&orderby=price");
		sb.append("&sortdescending=1");
		sb.append("&count=0");

		return sb.toString();
	}
}
