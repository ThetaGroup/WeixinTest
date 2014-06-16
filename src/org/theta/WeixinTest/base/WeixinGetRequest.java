package org.theta.WeixinTest.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/**
 * 
 * @author Ranger Chen
 * 
 */
public class WeixinGetRequest implements WeixinRequest {
	private String _url;
	private String _parametersString;
	private String _encrypt;

	/**
	 * @throws IOException
	 * @return String
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public String send() throws IOException, NoSuchAlgorithmException,
			KeyManagementException {
		String result = "";
		BufferedReader in = null;
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
				new java.security.SecureRandom());
		String urlString = this._url + this._parametersString;
		URL realUrl = new URL(urlString);
		HttpsURLConnection con = (HttpsURLConnection) realUrl.openConnection();
		con.setSSLSocketFactory(sc.getSocketFactory());
		con.setHostnameVerifier(new TrustAnyHostnameVerifier());
		con.setRequestProperty("accept", "*/*");
		con.setRequestProperty("connection", "Keep-Alive");
		con.setRequestProperty("user-agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		con.connect();

		in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
			result += line;
		}

		result = new String(result.getBytes(), this._encrypt);
		return result;
	}

	/**
	 * 
	 * @param url
	 * @param parametersString
	 */
	public WeixinGetRequest(String url, String parametersString, String encrypt) {
		this._url = url;
		this._parametersString = parametersString;
		this._encrypt = encrypt;
	}
}
