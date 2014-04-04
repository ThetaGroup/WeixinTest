package org.theta.WeixinTest.data;

/**
 * 
 * @author Ranger Chen
 *
 */
public class WeixinXmlData {
	public static final String TEXTTYPE="text";
	public static final String EVENTTYPE="event";
	public static final String MSGID="5995419636952154999";
	
	private String _toUserName;
	private String _fromUserName;
	private String _createTime;
	private String _msgType;
	private String _content;
	private String _event;
	private String _eventKey;
	
	/**
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param contentOrEvent
	 */
	public WeixinXmlData(String toUserName,String fromUserName,String createTime,String msgType,String contentOrEvent){
		this._toUserName=toUserName;
		this._fromUserName=fromUserName;
		this._createTime=createTime;
		this._msgType=msgType;
		if (this._msgType.equals(TEXTTYPE)){
			this._content=contentOrEvent;
		}else if (this._msgType.equals(EVENTTYPE)){
			this._event=contentOrEvent;
		}
		this._eventKey=null;
	}
	
	/**
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param event
	 * @param eventKey
	 */
	public WeixinXmlData(String toUserName,String fromUserName,String createTime,String msgType,String event,String eventKey){
		this._toUserName=toUserName;
		this._fromUserName=fromUserName;
		this._createTime=createTime;
		this._msgType=msgType;
		this._event=event;
		this._eventKey=eventKey;
		this._content=null;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getData(){
		String result="<?xml version=\"1.0\" encoding=\"utf-8\"?><xml>";
		result+="<ToUserName><![CDATA["+this._toUserName+"]]></ToUserName>";
		result+="<FromUserName><![CDATA["+this._fromUserName+"]]></FromUserName>";
		result+="<CreateTime>"+this._createTime+"</CreateTime>";
		result+="<MsgType><![CDATA["+this._msgType+"]]></MsgType>";
		if (this._msgType.equals(TEXTTYPE)){
			result+="<Content><![CDATA["+this._content+"]]></Content>";
			result+="<MsgId>"+MSGID+"</MsgId>";
		}else if (this._msgType.equals(EVENTTYPE)){
			result+="<Event><![CDATA["+this._event+"]]></Event>";
			if (this._eventKey!=null){
				result+="<EventKey><![CDATA["+this._eventKey+"]]></EventKey>";
			}
		}
		result+="</xml>";
		return result;
	}
	
}
