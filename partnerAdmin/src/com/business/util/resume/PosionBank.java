package com.business.util.resume;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 职位库
 * 
 * @author geqilin
 * 
 */
public class PosionBank {
	//职位名称库
	public static String posionList() {
		String str = "经理,总监,CEO,助理,总裁,人事,专员,主管,分析师,秘书,顾问,前台,主任,代表,美工,产品,测试,研发工程师,工程师,管理员,人员,java,JAVA,C++,软件工程师,程序员,分析员,网络管理,质量,执行官,招聘专员";
		return str;
	}
	public static void main(String[] args) {
		String str="2012 /1--至今：北京慧点科技（150-500人） [ 6个月] 所属行业：	互联网/电子商务 办公室 	行政专员/助理";
		System.out.println(getPosion(str));

	}
	public static String getPosion(String nofe){
		String posion="";
		String posionList = posionList();
		posionList = posionList.replaceAll(",", "|");
		Pattern ps = Pattern.compile("(" + posionList + ")");
		Matcher ms = ps.matcher(nofe);
		if (ms.find()) {
			 String posin = ms.group();
			 nofe=nofe.replaceAll("\\|", " ");
			 String s[]=nofe.split("\\s{1,}");
			 for (int i = 0; i < s.length; i++) {
				 if (s[i].indexOf(posin) > -1) {
						//特殊情况,中间没有空格
						if(s[i].indexOf("。")>-1 || s[i].length()>15|| s[i].indexOf(":")>-1 || s[i].indexOf("：")>-1){
							posion=posin;
						}else{
							posion = s[i];
							posion=posion.replaceAll("(\\:|\\.|/)",",");
						}
						break;
					}
			 }
		}
		return posion;
	}

}
