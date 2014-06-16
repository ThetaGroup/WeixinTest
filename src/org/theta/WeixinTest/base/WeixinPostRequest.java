package org.theta.WeixinTest.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
public class WeixinPostRequest implements WeixinRequest{
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
	public String send() throws IOException, NoSuchAlgorithmException, KeyManagementException{
		OutputStreamWriter out = null;
	    BufferedReader in = null;
	    String result = "";
	    
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
				new java.security.SecureRandom());
	    
	    URL realUrl = new URL(this._url+this._parametersString);
	    conn = (HttpsURLConnection)realUrl.openConnection();
	    
		conn.setSSLSocketFactory(sc.getSocketFactory());
		conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
	    conn.setRequestMethod("POST");
	    
	    conn.setDoOutput(true);
	    conn.setDoInput(true);
	    conn.setUseCaches(true);
	    
	    conn.setRequestProperty("Accept-Language", "zh_CN");
	    conn.setRequestProperty("Content-Type", "text/xml");
	    conn.setRequestProperty("Charset", this._encrypt);
	    
	    out = new OutputStreamWriter(conn.getOutputStream(),this._encrypt);
	    out.write(this._postData);
	    out.flush();
	    out.close();
	    
	    in = new BufferedReader(new InputStreamReader(conn.getInputStream(),this._encrypt));
	    String line;
	    while ((line = in.readLine()) != null) {
	    	result += new String(line.getBytes(),this._encrypt);
	    }
	    
	    result=new String(result.getBytes(),this._encrypt);
	    
	    //TODO 未作多线程处理，添加处理代码后删除
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    return result;
	}
	
	/**
	 * 
	 * @param url
	 * @param parametersString
	 * @param postData
	 * @throws IOException 
	 */
	public WeixinPostRequest(String url,String parametersString,String encrypt,String postData) throws IOException{
		this._url=url;
		this._parametersString=parametersString;
		this._encrypt=encrypt;
		this._postData=postData;
	}

}
