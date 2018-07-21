package com.business.util.resume;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
public class Opinion {
	public static void main(String[] args) {
		String string=1+"";
		System.out.println(string.length());
	}
	public static Integer sexOpin(String sex){
		if(sex!=null){
			if (sex.contains("男")) {
				return 1;
			}else if (sex.contains("女")) {
				return 2;
			}else {
				return 0;
			}
		}
		else return 0;
		
	}
	public static int enterpType(String string){
		if (string.contains("外资")) {
			return 1;
		}else if (string.contains("合资")) {
			return 2;
		}else if (string.contains("国企")) {
			return 3;
		}else if (string.contains("民营")) {
			return 4;
		}else if (string.contains("外企")) {
			return 5;
		}else if (string.contains("政府")) {
			return 6;
		}else if (string.contains("事业")) {
			return 7;
		}else if (string.contains("非盈利")) {
			return 8;
		}else if (string.contains("上市")) {
			return 9;
		}else if (string.contains("股份")) {
			return 10;
		}else if (string.contains("其它")) {
			return 11;
		}else {
			return 11;
		}
	}
	//时间
	public static String workTime(String time){
		if(time.indexOf("一")>-1){
			time="1";
		}else if(time.indexOf("二")>-1 || time.indexOf("两")>-1){
			time="2";
		}else if(time.indexOf("三")>-1){
			time="3";
		}else if(time.indexOf("四")>-1){
			time="4";
		}else if(time.indexOf("五")>-1){
			time="5";
		}else if(time.indexOf("六")>-1){
			time="6";
		}else if(time.indexOf("七")>-1){
			time="7";
		}else if(time.indexOf("八")>-1){
			time="8";
		}else if(time.indexOf("九")>-1){
			time="9";
		}else if(time.indexOf("十")>-1){
			time="10";
		}else if(time.indexOf("十一")>-1){
			time="11";
		}else if(time.indexOf("十二")>-1){
			time="12";
		}else if(time.indexOf("十三")>-1){
			time="13";
		}else if(time.indexOf("十四")>-1){
			time="14";
		}else if(time.indexOf("十五")>-1){
			time="15";
		}else if(time.indexOf("十六")>-1){
			time="16";
		}else if(time.indexOf("十七")>-1){
			time="17";
		}else if(time.indexOf("十八")>-1){
			time="18";
		}else if(time.indexOf("十九")>-1){
			time="19";
		}else if(time.indexOf("二十年")>-1){
			time="20";
		}
		return time;
	}
	
	public static String areaOpin2(String area){
		if(area!=null){
			if (area.indexOf("北京")>-1) {
				return 11+"";
			}else if (area.indexOf("上海")>-1) {
				return 12+"";
			}else if (area.indexOf("天津")>-1) {
				return 13+"";
			}else if (area.indexOf("重庆")>-1) {
				return 14+"";
			}else if (area.indexOf("广东")>-1 || area.indexOf("广州")>-1 ||  area.indexOf("深圳")>-1) {
				return 15+"";
			}else if (area.indexOf("山西")>-1) {
				return 16+"";
			}else if (area.indexOf("河北")>-1|| area.indexOf("石家庄")>-1) {
				return 17+"";
			}else if (area.indexOf("辽宁")>-1) {
				return 18+"";
			}else if (area.indexOf("吉林")>-1) {
				return 19+"";
			}else if (area.indexOf("黑龙江")>-1) {
				return 20+"";
			}else if (area.indexOf("江苏")>-1) {
				return 21+"";
			}else if (area.indexOf("安徽")>-1) {
				return 22+"";
			}else if (area.indexOf("山东")>-1) {
				return 23+"";
			}else if (area.indexOf("浙江")>-1 ||area.indexOf("杭州")>-1) {
				return 24+"";
			}else if (area.indexOf("江西")>-1) {
				return 25+"";
			}else if (area.indexOf("福建")>-1) {
				return 26+"";
			}else if (area.indexOf("湖南")>-1) {
				return 27+"";
			}else if (area.indexOf("湖北")>-1 || area.indexOf("武汉")>-1) {
				return 28+"";
			}else if (area.indexOf("河南")>-1) {
				return 29+"";
			}else if (area.indexOf("内蒙古")>-1) {
				return 30+"";
			}else if (area.indexOf("海南")>-1) {
				return 31+"";
			}else if (area.indexOf("广西")>-1) {
				return 32+"";
			}else if (area.indexOf("贵州")>-1) {
				return 33+"";
			}else if (area.indexOf("四川")>-1) {
				return 34+"";
			}else if (area.indexOf("云南")>-1) {
				return 35+"";
			}else if (area.indexOf("陕西")>-1) {
				return 36+"";
			}else if (area.indexOf("甘肃")>-1) {
				return 37+"";
			}else if (area.indexOf("宁夏")>-1) {
				return 38+"";
			}else if (area.indexOf("青海")>-1) {
				return 39+"";
			}else if (area.indexOf("新疆")>-1) {
				return 40+"";
			}else if (area.indexOf("西藏")>-1) {
				return 41+"";
			}else {
				//String[] strings=area.split("市");
				//sunAreaMapper.queryCode(strings[0]);
				return 11+"";
			}
		}
		else {
			return 11+"";
		}
		
	}
	
	public static String areaOpin(String area){
		if (area.contains("北京")) {
			return 11+"";
		}else if (area.contains("上海")) {
			return 12+"";
		}else if (area.contains("天津")) {
			return 13+"";
		}else if (area.contains("重庆")) {
			return 14+"";
		}else if (area.contains("广东")) {
			return 15+"";
		}else if (area.contains("山西")) {
			return 16+"";
		}else if (area.contains("河北")) {
			return 17+"";
		}else if (area.contains("辽宁")) {
			return 18+"";
		}else if (area.contains("吉林")) {
			return 19+"";
		}else if (area.contains("黑龙江")) {
			return 20+"";
		}else if (area.contains("江苏")) {
			return 21+"";
		}else if (area.contains("安徽")) {
			return 22+"";
		}else if (area.contains("山东")) {
			return 23+"";
		}else if (area.contains("浙江")) {
			return 24+"";
		}else if (area.contains("江西")) {
			return 25+"";
		}else if (area.contains("福建")) {
			return 26+"";
		}else if (area.contains("湖南")) {
			return 27+"";
		}else if (area.contains("湖北")) {
			return 28+"";
		}else if (area.contains("河南")) {
			return 29+"";
		}else if (area.contains("内蒙古")) {
			return 30+"";
		}else if (area.contains("海南")) {
			return 31+"";
		}else if (area.contains("广西")) {
			return 32+"";
		}else if (area.contains("贵州")) {
			return 33+"";
		}else if (area.contains("四川")) {
			return 34+"";
		}else if (area.contains("云南")) {
			return 35+"";
		}else if (area.contains("陕西")) {
			return 36+"";
		}else if (area.contains("甘肃")) {
			return 37+"";
		}else if (area.contains("宁夏")) {
			return 38+"";
		}else if (area.contains("青海")) {
			return 39+"";
		}else if (area.contains("新疆")) {
			return 40+"";
		}else if (area.contains("西藏")) {
			return 41+"";
		}else {
			String[] strings=area.split("市");
			return 11+"";
		}
	}
	public static String eduOpin(String edu){
		if (edu.contains("不限")) {
			return 1+"";
		}else if (edu.contains("大专")) {
			return 2+"";
		}else if (edu.contains("本科")) {
			return 3+"";
		}else if (edu.contains("硕士")) {
			return 4+"";
		}else if (edu.contains("博士后")) {
			return 6+"";
		}else if (edu.contains("博士")) {
			return 5+"";
		}else if (edu.contains("MBA/EMBA")) {
			return 7+"";
		}else {
			return 8+"";
		}
	}
	
}
