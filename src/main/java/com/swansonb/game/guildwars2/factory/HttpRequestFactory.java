package com.swansonb.game.guildwars2.factory;

import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Component;

@Component
public class HttpRequestFactory {

	private static final String SEARCH_URI = "https://tradingpost-live.ncplatform.net/ws/search.json?";



	public HttpGet getListing(String session, int type, int rarity, int levelmin, int levelmax){
		return get(getSearchUrl(type, rarity, levelmin, levelmax), session);
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

		return sb.toString();
	}
}
