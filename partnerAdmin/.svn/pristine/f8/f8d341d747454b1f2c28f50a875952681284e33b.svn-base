package com.business.util.resume;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 关键字的检索过程
 * 
 * @author geqilin
 * 
 */
public class KeyWord {
	public static void main(String[] args) throws Exception {
		String mseg = "C++=linux C++,windows C++,嵌入式 C++,驱动 C++,WINCE C++ Asp.NET";
		String path = "F:\\workspace\\suntray\\conf\\position.txt";
		System.out.println(getKeyPosition(mseg, path));
		System.out.println(getKeyWord(mseg));

	}

	// 关键信息的提取
	public static String getKeyWord(String nofe) {
		StringBuffer bufferKey = new StringBuffer();
		String reg = "[a-zA-Z]{1,}(\\++|\\s{0,})";
		Pattern ps = Pattern.compile(reg);
		Matcher ms = ps.matcher(nofe);
		while (ms.find()) {
			String key = ms.group().trim();
			bufferKey.append(key).append(" ");
		}
		// 特殊情况的加里面
		nofe = nofe.toLowerCase();
		if (nofe.indexOf(".net") > -1) {
			bufferKey.append(".net");
		}
		System.out.println("关键信息词："+bufferKey.toString());
		return bufferKey.toString();
	}
	public static String getKeyPosition(String nofe, String path) {
		StringBuffer buffer = new StringBuffer();
		Map map = txt(path);
		for (Object m : map.keySet()) {
			List<String> list = (List<String>) map.get(m);
			for (int i = 0; i < list.size(); i++) {
				String key = list.get(i);
				// 换成小写
				key = key.toLowerCase();
				if (nofe.indexOf(key) > -1) {
					// 返回职位
					if (buffer.indexOf(m.toString()) <= -1) {
						buffer.append(m).append(" ");
					}
				}
			}
		}
		System.out.println("专业领域:"+buffer.toString());
		return buffer.toString();
	}
	// 读取文本信息
	public static Map txt(String path) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		try {
			File file = new File(path);
			BufferedReader bufferedReader = new BufferedReader(
					(new InputStreamReader(new FileInputStream(file), "GBK")));
			String line = null;
			// 读取信息进行判断
			while ((line = bufferedReader.readLine()) != null) {
				if (line.indexOf("=") > -1) {
					String key = line.split("=")[0];
					String value = line.split("=")[1];
					if (value.indexOf(",") > -1) {
						value = value + ",";
					}
					String str[] = value.split(",");
					List<String> listValue = new ArrayList<String>();
					for (int i = 0; i < str.length; i++) {
						// 存小写的
						listValue.add(str[i].toLowerCase());
					}
					map.put(key, listValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
