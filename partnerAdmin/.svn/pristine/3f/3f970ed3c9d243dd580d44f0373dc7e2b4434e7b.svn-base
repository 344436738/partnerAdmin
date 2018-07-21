package com.business.util.resume;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;
public class WordPro {
	public static String wordPro(File file) throws IOException, XmlException, OpenXML4JException{
		String filename=file.getAbsolutePath();
		if (filename.contains("docx")) {
			return ReadAndWrite.readDocx(filename);
		}else {
			return ReadAndWrite.readDoc(filename);
		}
	}
	public static Map<String, Object> zlPro(String string) throws Exception{
		LinkedHashMap<String,Object> mapfiles = new LinkedHashMap<String, Object>();
		//读取到的关键字作为key，值可能多行用object存储
		return mapfiles;
	}
	public static String wordPro(String string) throws IOException, Exception{
		string=string.replaceAll("\n",	"\r\n");
		String filedir="F:\\temp.txt";
		File file=new File(filedir);
		if (file.exists()) {
			file.delete();
		}
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filedir),"GBK");
		BufferedWriter bufw = new BufferedWriter(osw);
		if (string!=null) {
			bufw.write(string);
		}else {
			throw new RuntimeException("不是满足的doc和txt格式");
		}
		bufw.newLine();
		bufw.close();
		return filedir;
	}
	public static String wordPro123(String string) throws IOException, Exception{
		String filedir="F:\\temp.txt";
		File file=new File(filedir);
		if (file.exists()) {
			file.delete();
		}
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filedir),"GBK");
		BufferedWriter bufw = new BufferedWriter(osw);
		if (string!=null) {
			bufw.write(string);
		}else {
			throw new RuntimeException("不是满足的doc和txt格式");
		}
		bufw.newLine();
		bufw.close();
		return filedir;
	}
}
