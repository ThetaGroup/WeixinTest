package org.theta.WeixinTest.data;

/**
 * 微信实例生成器，需预定义双方用户
 * @author Ranger Chen
 *
 */
public class WeixinXmlDataFactory {
	private String DefaultToUserName=null;
	private String DefaultFromUserName=null;
	
	private static final String SUBSCRIBEEVENT="subscribe";
	private static final String CLICKEVENT="CLICK";
	
	/**
	 * 
	 * @param toUserName
	 * @param fromUserName
	 */
	public WeixinXmlDataFactory(String toUserName,String fromUserName){
		this.DefaultToUserName=toUserName;
		this.DefaultFromUserName=fromUserName;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isInitialized(){
		return ((DefaultToUserName!=null)&&(DefaultFromUserName!=null));
	}
	
		
	/**
	 * 获取文本消息实例
	 * @param content
	 * @return
	 */
	public WeixinXmlData getTextInstance(String content){
		return (!this.isInitialized())?null:new WeixinXmlData(
				DefaultToUserName,
				DefaultFromUserName,
				WeixinXmlDataFactory.getTime(),
				WeixinXmlData.TEXTTYPE,
				content);
	}
	
	/**
	 * 获取欢迎消息实例
	 * @return
	 */
	public WeixinXmlData getSubscribeEventInstance(){
		return (!this.isInitialized())?null:new WeixinXmlData(
					DefaultToUserName,
					DefaultFromUserName,
					WeixinXmlDataFactory.getTime(),
					WeixinXmlData.EVENTTYPE,
					WeixinXmlDataFactory.SUBSCRIBEEVENT
				);
	}
	
	/**
	 * 获取菜单消息实例
	 * @param eventKey
	 * @return
	 */
	public WeixinXmlData getClickEventInstance(String eventKey){
		return (!this.isInitialized())?null:new WeixinXmlData(
					DefaultToUserName,
					DefaultFromUserName,
					WeixinXmlDataFactory.getTime(),
					WeixinXmlData.EVENTTYPE,
					WeixinXmlDataFactory.CLICKEVENT,
					eventKey
				);
	}
	
	/**
	 * 获取文本消息XML
	 * @param content
	 * @return
	 */
	public String getTextXml(String content){
		WeixinXmlData instance=this.getTextInstance(content);
		return (instance==null)?null:instance.getData();
	}
	
	/**
	 * 获取欢迎消息XML
	 * @return
	 */
	public String getSubscribeXml(){
		WeixinXmlData instance=this.getSubscribeEventInstance();
		return (instance==null)?null:instance.getData();
	}
	
	/**
	 * 获取菜单消息XML
	 * @param eventKey
	 * @return
	 */
	public String getClickEventXml(String eventKey){
		WeixinXmlData instance=this.getClickEventInstance(eventKey);
		return (instance==null)?null:instance.getData();
	}
	
	/**
	 * 
	 * @return
	 */
	private static String getTime(){
		return String.valueOf((System.currentTimeMillis()/1000));
	}
}
