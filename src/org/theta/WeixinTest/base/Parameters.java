package org.theta.WeixinTest.base;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * 
 * @author Ranger Chen
 *
 */
public class Parameters {
	private String _token;
	private String _signatrue;
	private String _timestamp;
	private String _nonce;
	private String _echostr;
	
    private static final String ALGORITHM="SHA1";
    private static final char[] HEX_DIGITS={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

    /**
     * ��ȡ�����ִ�����echo������һ������
     * @return
     */
    public String getParametersString(boolean first){
    	String result="";
    	result+=first?"?":"&";
    	result+="signature="+this._signatrue;
    	result+="&timestamp="+this._timestamp;
    	result+="&nonce="+this._nonce;
    	return result;
    }
    
    /**
     * ��ȡ�����ִ�����echo�������״���֤����
     * @return
     */
    public String getParametersStringWithEchostr(boolean first){
    	String result=this.getParametersString(first);
    	result+="&echostr="+this._echostr;
    	return result;
    }
    
	/**
	 * �Զ����ɲ���
	 * @param token
	 * @throws NoSuchAlgorithmException
	 */
	public Parameters(String token) throws NoSuchAlgorithmException{
		this._token=token;
		this._timestamp=String.valueOf(System.currentTimeMillis()/1000);
		Random rand=new Random(System.currentTimeMillis());
		long nonce=Math.abs(rand.nextInt(1000000000-1-100000000))+100000000;
		this._nonce=String.valueOf(nonce);
		this._signatrue=this.createSignatrue();
		this._echostr=this._nonce;
	}
	
	/**
	 * �ֶ����ɲ���
	 * @param token
	 * @param timestamp
	 * @param nonce
	 * @throws NoSuchAlgorithmException
	 */
	public Parameters(String token,String timestamp,String nonce) throws NoSuchAlgorithmException{
		this._token=token;
		this._timestamp=timestamp;
		this._nonce=nonce;
		
		this._signatrue=this.createSignatrue();
		this._echostr=this._nonce;
	}
	
	/**
	 * ����Signatture
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
    private String createSignatrue() throws NoSuchAlgorithmException{
        ArrayList<String> tmpStrList=new ArrayList<String>();
        tmpStrList.add(this._token);
        tmpStrList.add(this._timestamp);
        tmpStrList.add(this._nonce);
        Collections.sort(tmpStrList);         
        String  tmpStr="";
        for (String t:tmpStrList){
            tmpStr+=t;
        }
        MessageDigest md=MessageDigest.getInstance(Parameters.ALGORITHM);
        md.update(tmpStr.getBytes());
        tmpStr=this.getFormattedText(md.digest());
        return tmpStr;
    }
    
   /**
    * 
 	* @param bytes
 	* @return
 	*/
    private String getFormattedText(byte[] bytes){
        int len=bytes.length;
        StringBuilder buf=new StringBuilder(len*2);
        for (int j=0;j<len;j++){
            buf.append(HEX_DIGITS[(bytes[j]>>4)&0x0f]);
            buf.append(HEX_DIGITS[bytes[j]&0x0f]);
        }
        return buf.toString();
    }
    
    /**
     * 
     * @return
     */
    public String getSignatrue(){
    	return this._signatrue;
    }
    
    /**
     * 
     * @return
     */
    public String getTimestamp(){
    	return this._timestamp;
    }
    
    /**
     * 
     * @return
     */
    public String getNonce(){
    	return this._nonce;
    }
    
    /**
     * 
     * @return
     */
    public String getEchostr(){
    	return this._echostr;
    }
}
