package org.theta.WeixinTest.data;

/**
 * ΢��ʵ�����������Ԥ����˫���û�
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
	 * ��ȡ�ı���Ϣʵ��
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
	 * ��ȡ��ӭ��Ϣʵ��
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
	 * ��ȡ�˵���Ϣʵ��
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
	 * ��ȡ�ı���ϢXML
	 * @param content
	 * @return
	 */
	public String getTextXml(String content){
		WeixinXmlData instance=this.getTextInstance(content);
		return (instance==null)?null:instance.getData();
	}
	
	/**
	 * ��ȡ��ӭ��ϢXML
	 * @return
	 */
	public String getSubscribeXml(){
		WeixinXmlData instance=this.getSubscribeEventInstance();
		return (instance==null)?null:instance.getData();
	}
	
	/**
	 * ��ȡ�˵���ϢXML
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
