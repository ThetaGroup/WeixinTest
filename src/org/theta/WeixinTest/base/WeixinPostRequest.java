package org.theta.WeixinTest.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.HttpURLConnection;

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
	
	private static HttpURLConnection conn;
	
	/**
	 * @throws IOException
	 * @return
	 */
	public String send() throws IOException{
		OutputStreamWriter out = null;
	    BufferedReader in = null;
	    String result = "";
	    
	    URL realUrl = new URL(this._url+this._parametersString);
	    conn = (HttpURLConnection)realUrl.openConnection();
	    conn.setRequestMethod("POST");
	    conn.setDoOutput(true);
	    conn.setDoInput(true);
	    conn.setUseCaches(false);
	    conn.setRequestProperty("Content-Type", "text/xml");
	    
	    out = new OutputStreamWriter(conn.getOutputStream(),"utf-8");
	    out.write(this._postData);
	    out.flush();
	    out.close();
	    
	    
	    in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String line;
	    while ((line = in.readLine()) != null) {
	    	result += line;
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
		System.out.println(postData);
	}

}
