package com.business.util.resume;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 企业性质
 * 
 * @author geqilin
 * 
 */
public class EnterpriseProperty {
	public  static String getName = "外资,合资,国企,民营,外企,政府,事业,非盈利,上市,股份,其它";
	public static String getEnterprise(String nofe){
		String enterprise="";
		String posionList =EnterpriseProperty.getName;
		posionList = posionList.replaceAll(",", "|");
		Pattern ps = Pattern.compile("(" + posionList + ")");
		Matcher ms = ps.matcher(nofe);
		if (ms.find()) {
			 String posin = ms.group();
			 String s[]=nofe.split("\\s{1,}");
			 for (int i = 0; i < s.length; i++) {
				 if (s[i].indexOf(posin) > -1) {
						//特殊情况,中间没有空格
						if(s[i].indexOf("。")>-1 || s[i].length()>5|| s[i].length()>30|| s[i].indexOf(":")>-1 || s[i].indexOf("：")>-1|| s[i].indexOf("，")>-1){
							enterprise=posin;
						}else{
							enterprise = s[i];
						}
						break;
					}
			 }
		}
		return enterprise;
	}
}
