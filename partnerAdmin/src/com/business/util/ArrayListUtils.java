package com.business.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * 排序查大小 找出最大和最小的数字
 * @author geqilin
 *
 */
public class ArrayListUtils {
	public static String getMaxAndMin(Integer[] nums) {
		for (int i = 0; i <= nums.length - 1; i++) {
			for (int j = i + 1; j <= nums.length - 1; j++) {
				if (nums[i] > nums[j]) {
					int temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				}
			}
		}
		System.out.println("最小值为:" + nums[0] + ",最大值为:" + nums[nums.length - 1]);
		
		String max=nums[nums.length - 1]/12+"年"+nums[nums.length - 1]%12+"月";
		String min= nums[0]/12+"年"+ nums[0]%12+"月";
		String time=getcha(max, min);
		return time;
	}
	
	
	public static void main(String[] args) {
		System.out.println(getcha("2014年08月", "2012年01月"));
		int s=2014*12+(Integer.valueOf("10"));
		System.out.println(s);
		
		
		
	}
	public static String getcha(String max,String min){
	String time="";
	DateFormat df = new SimpleDateFormat("yyyy年MM月");
	try{    
		Date d1 = df.parse(max);  
		Date d2 = df.parse(min);   
	long diff = d1.getTime() - d2.getTime();  
	long day = diff / (1000 * 60 * 60 * 24);
	 day=day/30;
	 if(day<12){
		     time="0."+day;
	 }else{
		 time=day/12+"";
		 if(day%12!=0){
			  //time=time+"."+day;
		 }
	 }
	}catch (Exception e){}
	 return time+"";
	}

	public static void getListArray() {
		List<Double> list = new ArrayList();
		list.add(Double.valueOf(12));
		list.add(Double.valueOf(23));
		list.add(Double.valueOf(34));
		double max = Collections.max(list);
		double min = Collections.min(list);
	}
}
