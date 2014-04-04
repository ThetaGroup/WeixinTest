package org.theta.WeixinTest.data;

public class XMLPrinter {
	
	public static void print(String xml){
		System.out.println(format(xml));
	}
	
	@SuppressWarnings("unused")
	public static String format(String xml){
		//TODO tabs show undo.
		int tabs=0;
		String out="";
		for (int i=0;i<xml.length();i++){
			char b=xml.charAt(i);
			out+=b;
			if (b=='>'){
				out+="\n";
			}
		}
		return out;
	}
}
