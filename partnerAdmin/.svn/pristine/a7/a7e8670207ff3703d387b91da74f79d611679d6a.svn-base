package com.base.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Base64类
 * <br>功能：字符串的BASE64编码解码。
 */
public class Base64 
{
	/**
	 * 将字符串转化为base64编码
	 * @param s
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String getBase64(String s) throws UnsupportedEncodingException {
		byte[] bytes= org.apache.commons.codec.binary.Base64.encodeBase64(s.getBytes("utf-8"));
		return new String(bytes, "utf-8");
		
	}

	/**
	 * 将 BASE64 编码的字符串 s 进行解码
	 * @param s
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String getFromBase64(String s) throws UnsupportedEncodingException {
		byte[] bytes=s.getBytes("utf-8");
		byte[] convertBytes=org.apache.commons.codec.binary.Base64.decodeBase64(bytes);
		return new String(convertBytes, "utf-8");
	}
	
	
	public static String decodeWithCaesar(String s) throws UnsupportedEncodingException{
		return  getFromBase64(CaesarCipher.decrypt(s));
	}
	
	
	public static String encodeWithCaesar(String s) throws UnsupportedEncodingException{
		return  CaesarCipher.encrypt(getBase64(s));
	}
	
	
	public static void main(String[] args){
		try {
			String s="你好##1#"+DateUtils.format(new Date(), DateUtils.FORMAT_LONG);
			s=getBase64(s);
			System.out.println(s);
			s=getFromBase64(s);
			System.out.println(s);
			
//			s=getBase64(s);
//			System.out.println(s);
//			s=CaesarCipher.encrypt(s);
//			System.out.println(s);
//			
//			s=CaesarCipher.decrypt(s);
//			System.out.println(s);
//			s=getFromBase64(s);
//			System.out.println(s);
//			
//			String password="111111";
//			System.out.println(getBase64(password));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
