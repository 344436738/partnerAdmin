package com.business.util.resume;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;

public class MyWordExtractor {
	public static void main(String[] args) {
		String file="F:\\张赛JAVA简历.doc";
		String p="F:\\张赛JAVA简历.docx";
		System.out.println(MyWordExtractor.doc1(file));
		//System.out.println(MyWordExtractor.docx(p));
		
	}
	//docx
	public static String  docx(String file) {
		String str="";
		try {
			   OPCPackage opcPackage = POIXMLDocument.openPackage(file);
	           POIXMLTextExtractor ex = new XWPFWordExtractor(opcPackage);
	           str=ex.getText();
//	           String filePath = "f:\\51猎简历解析文档.txt";
//	           File filename = new File(filePath);
//	           creatTxtFile(filename);
//	           writeTxtFile(filePath, ex.getText());
//	           readTxtFile("f:\\51猎简历解析文档.txt");
	           
		   } catch (FileNotFoundException e) {
			   e.printStackTrace();
		   } catch (IOException e) {
			   e.printStackTrace();
		   }catch (OpenXML4JException e) {
			   e.printStackTrace();
		   } catch (XmlException e) {
			   e.printStackTrace();
		   }
		   return str;
	}
	
	public static String doc(String path) {
		String str="";

		   File file = new File(path);
		   try {
			    FileInputStream fis = new FileInputStream(file);
			    WordExtractor wordExtractor = new WordExtractor(fis);
			    str=wordExtractor.getText();
		   } catch (FileNotFoundException e) {
			   e.printStackTrace();
		   } catch (IOException e) {
			   e.printStackTrace();
		   }
		return str;
	}
	public static String doc1(String path) {
		   File file = new File(path);
		   String str="";
		   try {
			    FileInputStream fis = new FileInputStream(file);
			    WordExtractor wordExtractor = new WordExtractor(fis);
			    StringBuffer buffer=new StringBuffer();
			    //System.out.println("【 使用getText()方法提取的Word文件的内容如下所示：】");
			    String[] paragraph = wordExtractor.getParagraphText();
			    //System.out.println("该Word文件共有"+paragraph.length+"段。");
			    int j=0;
			    for(int i=0;i<paragraph.length;i++){
			    	j++;
			    	buffer.append(paragraph[i]+"\t");
			    	 
			    	int kongge = paragraph[i].indexOf("")>=0?1:0;
			    	if (kongge==1) {
						String[] strs = paragraph[i].split("");
						for (int k = 0; k < strs.length; k++) {
							buffer.append(strs[k]+"\t");
							String str1=strs[k];
							System.out.println(str1);
							j++;
						}
					}else {
						 //System.out.println("< 第 "+(j)+" 行的内容为 >");
					     //buffer.append(paragraph[i]+"\t");
					     String s=paragraph[i].replaceAll("", " ");
					     System.out.println(s);
					}
			    
			    }
			  //str=buffer.toString().replaceAll("", " ");
		   } catch (FileNotFoundException e) {
			   e.printStackTrace();
		   } catch (IOException e) {
			   e.printStackTrace();
		   }
		   return str;
		}
	
	public static void creatTxtFile(File filename) throws IOException{
        if (!filename.exists()) {
            filename.createNewFile();
            System.out.println(filename + "已创建！");
        }
    }
	public static String readTxtFile(String filePath){
        String read;
        FileReader fileread;
        BufferedReader bufread = null;
        String readStr="";
		try {
            fileread = new FileReader(filePath);
            bufread = new BufferedReader(fileread);
            int i=0;
            try {
                while ((read = bufread.readLine()) != null) {
                	i++;
                    readStr = readStr + read+ "\r\n";
                    System.out.println("第+"+i+"行内容是:"+ "\r\n" + read);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
        	try {
				bufread.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        //System.out.println("文件内容是:"+ "\r\n" + readStr);
        return readStr;
    }
	public static void writeTxtFile(String filePath,String newStr) throws IOException{
		//String readStr = readTxtFile(filePath);
        String filein = newStr + "\r\n";
        BufferedWriter bw = null;
        try {
        	bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
        	bw.write(filein);
        } catch (IOException e1) {
            // TODO 自动生成 catch 块
            e1.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e2) {
                    // TODO 自动生成 catch 块
                    e2.printStackTrace();
                }
            }
        }
    }
}