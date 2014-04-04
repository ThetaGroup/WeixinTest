package org.theta.WeixinTest.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 
 * @author Ranger Chen
 *
 */
public class WeixinGetRequest implements WeixinRequest{
	private String _url;
	private String _parametersString;
	private String _encrypt;
	
	/**
	 * @throws IOException
	 * @return String
	 */
	public String send() throws IOException{
		String result="";
		BufferedReader in=null;
		
		String urlString=this._url+this._parametersString;
		URL realUrl=new URL(urlString);
		URLConnection con=realUrl.openConnection();
        con.setRequestProperty("accept", "*/*");
        con.setRequestProperty("connection", "Keep-Alive");
        con.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        con.connect();

        /*
        Map<String,List<String>> map=con.getHeaderFields();
        for (String key:map.keySet()){
        	System.out.println(key+"--->"+map.get(key));
        }
        */
        
        in=new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line;
		while ((line=in.readLine())!=null){
			result+=line;
		}
		
		result=new String(result.getBytes(),this._encrypt);
        return result;
	}
	
	/**
	 * 
	 * @param url
	 * @param parametersString
	 */
	public WeixinGetRequest(String url,String parametersString,String encrypt){
		this._url=url;
		this._parametersString=parametersString;
		this._encrypt=encrypt;
	}
}
