package com.business.util.resume;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;

public class ReadAndWrite {
	public static String readDocx(String file) throws IOException,
			XmlException, OpenXML4JException {

		OPCPackage opcPackage = POIXMLDocument.openPackage(file);
		POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
		String text = extractor.getText();
		return text;
	}

	// 读取doc文件
	public static String readDoc(String file) {
		FileInputStream in;
		WordExtractor extractor = null;
		try {
			in = new FileInputStream(new File(file));
			extractor = new WordExtractor(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("文件无法读取");
		}
		String text = null;
		// 创建WordExtractor
		// 对doc文件进行提取
		text = extractor.getText();
		return text;
	}
	// 分行续写入文件 flag(true:分行续写，false:重新从头写)
	public static void DOWriteTxt(String file, String txt, boolean flag) {
		try {
			FileOutputStream os = new FileOutputStream(new File(file), flag);
			os.write((txt + "\r\n").getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void DOWriteTxt(File file, String txt, boolean flag) {
		try {
			FileOutputStream os = new FileOutputStream(file, flag);
			os.write((txt + "\r\n").getBytes());
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static ArrayList<String> getWordlist() {
		HashSet<String> wordset = new HashSet<String>();
		File file = new File("E:/io/ziye/keyword.txt");// 文件地址
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				wordset.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		ArrayList<String> list = new ArrayList<String>();
		Object[] obj = wordset.toArray(); // 转换为数组
		System.out.println(obj.length);
		String tmp = "";
		for (int j = 0; j < obj.length; j++) {
			tmp = (String) obj[j];
			list.add(tmp);
		}
		return list;
	}
	public static ArrayList<String> getWordlist(File file) {
		HashSet<String> wordset = new HashSet<String>();
		// File file = new File("E:/io/ziye/keyword.txt");//文件地址
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				wordset.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		ArrayList<String> list = new ArrayList<String>();
		Object[] obj = wordset.toArray(); // 转换为数组
		System.out.println(obj.length);
		String tmp = "";
		for (int j = 0; j < obj.length; j++) {
			tmp = (String) obj[j];
			list.add(tmp);
		}
		return list;
	}
	// 读取文件，按顺序
	public static ArrayList<String> getWordlistSort(File file) {
		// HashSet<String> wordset = new HashSet<String>();
		ArrayList<String> list = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				list.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return list;
	}

}
