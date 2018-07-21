package com.weixin.utils;

public class HdUtils {
	
	public static boolean createPriced(String gl){
		String glArr[] = gl.split("/");
		int fz=Integer.valueOf(glArr[0]);
		int fm=Integer.valueOf(glArr[1]);
		int randomNum = 0;
		for(int i = 0 ;i<fz;i++){
			randomNum = (int)(Math.random()*fm);
			if(randomNum==1){
				return true;
			}
		}
		return false;
	}
	
	
	/*public static boolean createPriced(int fz,int fm){
		int randomNum = 0;
		for(int i = 0 ;i<fz;i++){
			randomNum = (int)(Math.random()*fm);
			if(randomNum==1){
				return true;
			}
		}
		return false;
	}*/
	public static int createPrice(int fz,int fm){
		int randomNum = 0;
		for(int i = 0 ;i<fz;i++){
			randomNum = (int)(Math.random()*fm);
			if(randomNum==1){
				return randomNum;
			}
		}
		return randomNum;
	}
	
}
