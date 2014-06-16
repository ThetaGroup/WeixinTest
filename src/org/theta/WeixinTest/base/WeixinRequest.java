package org.theta.WeixinTest.base;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author Ranger Chen
 * 
 */
public interface WeixinRequest {

	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public String send() throws IOException, NoSuchAlgorithmException,
			KeyManagementException;
}
