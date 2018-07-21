package com.business.util.resume;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	//按指定规则匹配字符串的字串，包含返回true。str.matches(regEx)
	//str没有判断
	public static boolean pattern(String str, String regEx){
		boolean rs = false;
		if(regEx != null && regEx.length() > 0){
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);
			rs = m.find();
		}
		return rs;
	}
	//输入字符串和匹配的规则，返回匹配的次数
	public static int matchNum(String str, String regEx){
		int a=0;
		if(regEx != null && regEx.length() > 0){
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);
			while(m.find()){
				a++;
			}
		}
		return a;
	}
	//输入字符串和匹配的规则，返回匹配的字符串
	public static LinkedList<String> matchCon(String str, String regEx){
		LinkedList<String> linkedList=new LinkedList<String>();
		if(regEx != null && regEx.length() > 0){
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);
			while(m.find()){
				linkedList.addLast(m.group().trim());
			}
		}
		return linkedList;
	}
	//输入字符串和匹配的规则，返回匹配的字符串  这个是后面加了一个空格的，这个可以返回多个，用空格分开，还不如用list
	public static String matchConString(String str, String regEx){
		String string="";
		if(regEx != null && regEx.length() > 0){
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);
			while(m.find()){
				string=string+m.group().trim()+" ";
			}
		}
		return string;
	}
	//这个只返回一个值
	public static String matchConStringOne(String str, String regEx){
		String string="";
		if(regEx != null && regEx.length() > 0){
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);
			while(m.find()){
				string=m.group().trim();
				break;
			}
		}
		return string;
	}
	//返回匹配到的数据
	public static String getValueByPattern(String str, String regEx,int i) {
		String rtnStr = "";
		if (regEx != null && regEx.length() > 0) {
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);
			boolean rs = m.find();

			if (rs && m.groupCount() > 0) {
				 rtnStr = m.group(i);
			}
		} else {
			rtnStr = str;
		}
		return rtnStr;
	}
	//这个是去掉字符串里的空格什么的
	public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
	
}
