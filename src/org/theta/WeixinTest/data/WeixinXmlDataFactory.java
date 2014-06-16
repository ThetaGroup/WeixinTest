package org.theta.WeixinTest.data;

/**
 * 微锟斤拷实锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟皆わ拷锟斤拷锟剿拷锟斤拷没锟�
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
	 * 锟斤拷取锟侥憋拷锟斤拷息实锟斤拷
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
	 * 锟斤拷取锟斤拷迎锟斤拷息实锟斤拷
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
	 * 锟斤拷取锟剿碉拷锟斤拷息实锟斤拷
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
	 * 锟斤拷取锟侥憋拷锟斤拷息XML
	 * @param content
	 * @return
	 */
	public String getTextXml(String content){
		WeixinXmlData instance=this.getTextInstance(content);
		return (instance==null)?null:instance.getData();
	}
	
	/**
	 * 锟斤拷取锟斤拷迎锟斤拷息XML
	 * @return
	 */
	public String getSubscribeXml(){
		WeixinXmlData instance=this.getSubscribeEventInstance();
		return (instance==null)?null:instance.getData();
	}
	
	/**
	 * 锟斤拷取锟剿碉拷锟斤拷息XML
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
