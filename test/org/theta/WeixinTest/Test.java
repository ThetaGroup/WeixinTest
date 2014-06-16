package org.theta.WeixinTest;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.theta.WeixinTest.base.RequestMachine;
import org.theta.WeixinTest.data.EventKey;
import org.theta.WeixinTest.data.WeixinXmlDataFactory;
import org.theta.WeixinTest.data.XMLPrinter;


public class Test {
	//private static final String token="jsjnkxdkfb";
	private static final String token="kxd";
	//private static final String url="http://172.31.18.188:8009/KXD/wxs.shtml?action=wxsPort";private static final boolean first=false;
	//private static final String url="http://www.gkkxd.com/kxd-portal/assist/weixin/wxsPort";private static final boolean first=true;	
	//private static final String url="http://172.31.18.188/kxd/assist/weixin/wxsPort";private static final boolean first=true;
	private static final String url="https://test.kxd.com/assist/weixin/wxsPort";private static final boolean first=true;
	
	public static void main(String args[]) throws NoSuchAlgorithmException, IOException, InterruptedException{
		System.setProperty("jsse.enableSNIExtension", "false");
		
		WeixinXmlDataFactory wxdf=new WeixinXmlDataFactory("gh_243a745235c2","octOFjvWhiYyGP2BcKA9c0GS08vk");
		
		RequestMachine tm=new RequestMachine(token,url,"UTF-8",first);

		tm.isConnected();
		//String result=tm.post(wxdf.getClickEventXml(EventKey.ZHBD));
		//tm.isConnected();
		//String result=tm.post("<xml><ToUserName><![CDATA[gh_243a745235c2]]></ToUserName><FromUserName><![CDATA[octOFjvWhiYyGP2BcKA9c0GS08vk]]></FromUserName><CreateTime>1396235429</CreateTime><MsgType><[CDATA[event]]></MsgType><Event><![CDATA[CLICK]]></Event></xml>");
		//String result=tm.post(wxdf.getClickEventXml("e_zhbd"));
		//String result=tm.post(wxdf.getTextXml("jcbd"));
		//XMLPrinter.print(result.toString());
		//System.out.println(result);
	}
	
}