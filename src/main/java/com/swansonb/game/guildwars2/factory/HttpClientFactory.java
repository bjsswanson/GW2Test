package com.swansonb.game.guildwars2.factory;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DecompressingHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Component
public class HttpClientFactory {

	private HttpClient httpClient;

	public HttpClient getClient(){
		if(httpClient == null) {
			httpClient = getNewClient();
		}

		return httpClient;
	}

	private HttpClient getNewClient(){
		HttpHost proxy = new HttpHost("127.0.0.1",8888);
		HttpClient httpClient = new DecompressingHttpClient(wrapClient(new DefaultHttpClient()));
		httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
		return httpClient;
	}

	public static DefaultHttpClient wrapClient(DefaultHttpClient base) {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new CustomX509TrustManager();
			X509HostnameVerifier verifier = new CustomX509HostnameVerifier();
			ctx.init(null, new TrustManager[]{tm}, null);
			org.apache.http.conn.ssl.SSLSocketFactory ssf = new org.apache.http.conn.ssl.SSLSocketFactory(ctx);
			ssf.setHostnameVerifier(verifier);
			ClientConnectionManager ccm = new PoolingClientConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", ssf, 443));
			return new DefaultHttpClient(ccm, base.getParams());
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private static class CustomX509TrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}

	private static class CustomX509HostnameVerifier implements X509HostnameVerifier {

		@Override
		public void verify(String string, SSLSocket ssls) throws IOException {
		}

		@Override
		public void verify(String string, X509Certificate xc) throws SSLException {
		}

		@Override
		public void verify(String string, String[] strings, String[] strings1) throws SSLException {
		}

		@Override
		public boolean verify(String string, SSLSession ssls) {
			return true;
		}
	}
}
