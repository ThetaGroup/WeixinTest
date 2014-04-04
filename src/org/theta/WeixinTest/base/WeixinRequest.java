package org.theta.WeixinTest.base;

import java.io.IOException;

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
	 */
	public String send() throws IOException;
}
