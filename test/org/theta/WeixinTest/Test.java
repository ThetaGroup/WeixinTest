package org.theta.WeixinTest;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.theta.WeixinTest.base.RequestMachine;
import org.theta.WeixinTest.data.EventKey;
import org.theta.WeixinTest.data.WeixinXmlDataFactory;
import org.theta.WeixinTest.data.XMLPrinter;

/**
 * KXD微信测试
 * 注：不支持多线程，不支持同步队列
 * 
 * @author Ranger Chen
 * @date 2014年6月16日
 * 
 */
public class Test {
	/* 配置文件中token明文值 */
	private static final String token = "kxd";
	/* 微信请求url */
	private static final String url = "https://172.31.100.83/assist/weixin/wxsPort";
	/* 请求参数是否为url第一个参数 */
	private static final boolean first = true;

	public static void main(String args[]) throws NoSuchAlgorithmException,
			IOException, InterruptedException, KeyManagementException {

		/* 设置xml中toUserName和openid */
		WeixinXmlDataFactory wxdf = new WeixinXmlDataFactory("gh_243a745235c2",
				"octOFjvWhiYyGP2BcKA9c0GS08vk");

		/* request基本设置 */
		RequestMachine tm = new RequestMachine(token, url, "utf-8", first);

		//tm.isConnected();

		String result=tm.post(wxdf.getClickEventXml(EventKey.ZHBD));
		
		XMLPrinter.print(result);
		
		// String result=tm.post(wxdf.getClickEventXml("e_zhbd"));
		// String result=tm.post(wxdf.getTextXml("jcbd"));
	}

}