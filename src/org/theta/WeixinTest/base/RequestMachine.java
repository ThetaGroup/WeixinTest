package org.theta.WeixinTest.base;

import java.io.IOException;
import java.security.KeyManagementException;
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
	public RequestMachine(String token, String baseUrl, String encrypt,
			boolean first) {
		this.setToken(token);
		this.setBaseUrl(baseUrl);
		this.setEncrypt(encrypt);
		this.setFirst(first);
	}

	/**
	 * 通锟斤拷锟斤拷一锟轿帮拷echostr锟斤拷get锟斤拷锟斤拷锟叫讹拷锟角凤拷锟杰癸拷锟斤拷锟斤拷
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws KeyManagementException
	 */
	public boolean isConnected() throws NoSuchAlgorithmException, IOException,
			KeyManagementException {
		boolean isConnected = false;
		Parameters para = new Parameters(this._token);
		WeixinRequest request = new WeixinGetRequest(this._baseUrl,
				para.getParametersStringWithEchostr(this._first), this._encrypt);
		String result = request.send();
		isConnected = result.trim().equals(para.getEchostr());
		return isConnected;
	}

	/**
	 * 锟斤拷锟斤拷post锟斤拷锟斤拷
	 * 
	 * @param postData
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws KeyManagementException
	 */
	public String post(String postData) throws NoSuchAlgorithmException,
			IOException, InterruptedException, KeyManagementException {
		String result = "";
		WeixinRequest request = new WeixinPostRequest(this._baseUrl,
				this.getParaStr(), this._encrypt, postData);
		result = request.send();

		return result;
	}

	/**
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private String getParaStr() throws NoSuchAlgorithmException {
		return (new Parameters(this._token)).getParametersString(this._first);
	}

	/**
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@SuppressWarnings("unused")
	private String getParaStrWithEcho() throws NoSuchAlgorithmException {
		return (new Parameters(this._token))
				.getParametersStringWithEchostr(this._first);
	}

	/**
	 * 
	 * @param token
	 */
	public void setToken(String token) {
		this._token = token;
	}

	/**
	 * 
	 * @param baseUrl
	 */
	public void setBaseUrl(String baseUrl) {
		this._baseUrl = baseUrl;
	}

	/**
	 * 
	 * @param encrypt
	 */
	public void setEncrypt(String encrypt) {
		this._encrypt = encrypt;
	}

	/**
	 * 
	 * @param first
	 */
	public void setFirst(boolean first) {
		this._first = first;
	}

}
