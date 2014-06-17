package org.theta.WeixinTest.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author Ranger Chen
 * 
 */
public class WeixinPostRequest implements WeixinRequest {
	private String _url;
	private String _parametersString;
	private String _encrypt;
	private String _postData;

	private static HttpsURLConnection conn;

	/**
	 * @throws IOException
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public String send() throws IOException, NoSuchAlgorithmException,
			KeyManagementException {
		// OutputStreamWriter out = null;
		// BufferedReader in = null;
		// String result = "";
		//
		SSLContext sc = SSLContext.getInstance("TLS");
		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
				new java.security.SecureRandom());

		//
		// URL realUrl = new URL(this._url + this._parametersString);
		// conn = (HttpsURLConnection) realUrl.openConnection();
		// conn.setSSLSocketFactory(sc.getSocketFactory());
		//
		// conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
		// conn.setRequestMethod("POST");
		//
		// conn.setDoOutput(true);
		//
		// conn.setRequestProperty("Content-Type",
		// "text/plain;charset="+this._encrypt);
		// conn.setRequestProperty("Accept-Charset", this._encrypt);
		//
		// out = new OutputStreamWriter(conn.getOutputStream(), this._encrypt);
		// out.write(this._postData);
		// out.flush();
		// out.close();
		//
		// System.out.println(conn.getHeaderFields());
		//
		// in = new BufferedReader(new InputStreamReader(conn.getInputStream(),
		// this._encrypt));
		// String line;
		// while ((line = in.readLine()) != null) {
		// result += new String(line.getBytes(), this._encrypt);
		// }
		//
		// result = new String(result.getBytes(), this._encrypt);
		//
		// // TODO 未作多线程处理，添加处理代码后删除
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// return result;

		SSLContextBuilder builder = new SSLContextBuilder();
		try {
			builder.loadTrustMaterial(null, new MyTrustSelfSignedStrategy());
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		builder.useTLS();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				builder.build(),
				SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		CloseableHttpClient httpclient = HttpClients.custom()
				.setSSLSocketFactory(sslsf).build();

		HttpPost httpPost = new HttpPost(this._url + this._parametersString);
		HttpEntity entity=new StringEntity(this._postData);
		httpPost.setEntity(entity);
		
		
		CloseableHttpResponse response = httpclient.execute(httpPost);
		String body = EntityUtils.toString(response.getEntity());
		return body;
	}

	/**
	 * 
	 * @param url
	 * @param parametersString
	 * @param postData
	 * @throws IOException
	 */
	public WeixinPostRequest(String url, String parametersString,
			String encrypt, String postData) throws IOException {
		this._url = url;
		this._parametersString = parametersString;
		this._encrypt = encrypt;
		this._postData = postData;
	}

}
