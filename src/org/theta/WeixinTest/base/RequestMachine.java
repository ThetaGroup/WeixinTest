package org.theta.WeixinTest.base;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author Ranger Chen
 *
 */
public class RequestMachine {
	private String _token;
	private String _baseUrl;
	private String _encrypt;
	private boolean _first;
	
	/**
	 * 
	 * @param token
	 * @param baseUrl
	 */
	public RequestMachine(String token,String baseUrl,String encrypt,boolean first){
		this.setToken(token);
		this.setBaseUrl(baseUrl);
		this.setEncrypt(encrypt);
		this.setFirst(first);
	}
	
	/**
	 * ͨ����һ�ΰ�echostr��get�����ж��Ƿ��ܹ�����
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public boolean isConnected() throws NoSuchAlgorithmException, IOException{
		boolean isConnected=false;
		Parameters para=new Parameters(this._token);
		WeixinRequest request=new WeixinGetRequest(
				this._baseUrl,
				para.getParametersStringWithEchostr(this._first),
				this._encrypt);
		String result=request.send();
		isConnected=result.trim().equals(para.getEchostr());
		return isConnected;
	}
	
	/**
	 * ����post����
	 * @param postData
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public String post(String postData) throws NoSuchAlgorithmException, IOException, InterruptedException{
		String result="";
		WeixinRequest request=new WeixinPostRequest(this._baseUrl,this.getParaStr(),this._encrypt,postData);
		result=request.send();
		return result;
	}
	
	/**
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private String getParaStr() throws NoSuchAlgorithmException{
		return (new Parameters(this._token)).getParametersString(this._first);
	}
	
	/**
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@SuppressWarnings("unused")
	private String getParaStrWithEcho() throws NoSuchAlgorithmException{
		return (new Parameters(this._token)).getParametersStringWithEchostr(this._first);
	}
	
	/**
	 * 
	 * @param token
	 */
	public void setToken(String token){
		this._token=token;
	}
	
	/**
	 * 
	 * @param baseUrl
	 */
	public void setBaseUrl(String baseUrl){
		this._baseUrl=baseUrl;
	}
	
	/**
	 * 
	 * @param encrypt
	 */
	public void setEncrypt(String encrypt){
		this._encrypt=encrypt;
	}
	
	/**
	 * 
	 * @param first
	 */
	public void setFirst(boolean first){
		this._first=first;
	}
	
	
}
