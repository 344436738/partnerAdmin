package com.business.util.resume;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.icu.util.Calendar;
import com.business.util.ArrayListUtils;

/**
 * @author geqilin 对简历进行解析处理的过程
 */
public class ResumePro1 {
	public final static String charSet = "GBK";
	static String reg = "　{0,}\\s{0,}";
	//目前只支持三中格式的文件，比如txt doc docx
	public static String txt(MultipartFile file) throws IOException {
		BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(file.getInputStream(), charSet)));
		//用BufferedReader读取文件的每一行
		String line = null;
		String read = "";
		// 读取信息进行判断
		int i = 0;
		while ((line = bufferedReader.readLine()) != null) {
			if (line.indexOf("------") <= -1 && line.indexOf("________") <= -1) {
				if (i < 50) {
					line = line.replaceAll("\\：\\s{0,}", ":");
					if (line.indexOf("简历关键字:") > -1
							&& line.indexOf("简历编号") <= -1) {
						line = line + "@#";
					}
					if (line.indexOf("应聘职位:") > -1
							&& line.indexOf("简历编号") <= -1) {
						line = line + "@#";
					}
					if (line.indexOf("应聘名称:") > -1
							&& line.indexOf("简历编号") <= -1) {
						line = line + "@#";
					}
					if (line.indexOf("投递时间:") > -1) {
						line = line + "@#";
					}
					if (line.indexOf("智联招聘 ") > -1) {
						line = line + "@#";
					}
					if (line.indexOf("更新时间:") > -1) {
						line = line + "@#";
					}
					if (line.indexOf("简历匹配度:") > -1) {
						line = line + "@#";
					}
					if (line.indexOf("个人简历 ") > -1) {
						line = line + "@#";
					}
					if (line.indexOf("个人信息 ") > -1) {
						line = line + "@#";
					}
					if (line.indexOf("基本资料 ") > -1) {
						line = line + "@#";
					}
				}
				read += line + "\t\n";
				i++;
			}
		}
		return read;
	}
	// 2.doc格式的数据
	public static String docx(MultipartFile file) {
		String strdoc = "";
		try {
			InputStream is = file.getInputStream();
			XWPFDocument doc = new XWPFDocument(is);
			XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
			String text = extractor.getText();
			// System.out.println(text);
			// OPCPackage opcPackage =
			// POIXMLDocument.openPackage(file.getPath());
			// POIXMLTextExtractor ex = new XWPFWordExtractor(opcPackage);
			// strdoc = ex.getText();
			// CoreProperties coreProps = ex.getCoreProperties();
			System.out.println("获取不到：" + strdoc);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strdoc;
	}

	public String readWordDocument(File file) {
		String documentData = "";
		try {
			XWPFWordExtractor document = new XWPFWordExtractor(new XWPFDocument(new FileInputStream(file)));
			documentData = document.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return documentData;
	}

	// 3.docx格式的数据
	public static String doc(MultipartFile files) {
		String str = "";
		try {
			InputStream is = files.getInputStream();
			WordExtractor wordExtractor = new WordExtractor(is);
			str = wordExtractor.getText();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	// 对文本进行整理
	public static String setString(String read) {
		// 将中文：换成英文:
		String strTxt = read.replaceAll("\\：\\s{0,}", ":");
		//去除特殊符号
		strTxt = strTxt.replaceAll("\\【", " ");
		strTxt = strTxt.replaceAll("\\】", " ");
		//0.个人简历
		//strTxt = strTxt.replaceAll("(基本资料|个人信息)", "");
		
		strTxt = strTxt.replaceAll("\\s{0,}个\\s{0,}人{1,2}\\s{0,}(简\\s{0,}历|信\\s{0,}息)","\n个人简历");
		//1.自我评价
		strTxt = strTxt.replaceAll("\\s{0,}[\u4e00-\u9fa5]{1,2}\\s{0,}[\u4e00-\u9fa5]{1,2}\\s{0,}评\\s{0,}(价|估)(\\s{0,}|:)","\n@自我评价\n\t");
		//特殊情况
		strTxt = strTxt.replaceAll("(年|以上|无)工作经(验|历)\\s{0,}", "年工作经验");
		
		strTxt = strTxt.replaceAll("\\s{0,}工作履历(\\s{0,}|:)", "\n@工作经历\n\t");
		
		strTxt = strTxt.replaceAll("\\s{1,}社会经(验|历)(\\s{1,}|:)", "\n@工作经历\n\t");
		//同时存在
		if (strTxt.indexOf("工作经历 ") > -1 && strTxt.indexOf("社会经历") > -1) {
			strTxt = strTxt.replaceAll("社会经历", "");
		}
		strTxt = strTxt.replaceAll("\\s{0,}工作经验top", "");
		
		strTxt = strTxt.replaceAll("\\s{0,}工\\s{0,}作\\s{0,}经\\s{0,}(验|历)(\\s{1,}|:)","\n@工作经历\n\t");
		// 替换一个
		if (strTxt.indexOf("工作经历") <= -1) {
			strTxt = strTxt.replaceAll("\\s{0,}实践经验(\\s{1,}|:)", "\n@工作经历\n\t");
		}
		// 替换一个
		if (strTxt.indexOf(" 职责和业绩") > -1) {
			strTxt = strTxt.replace("\\s{0,}职责和业绩(\\s{1,}|:)", "\n@工作经历\n\t");
		}
		//------------------------------教育经历---------------------------------
		// 教育经历
		strTxt = strTxt.replaceAll("\\s{0,}教\\s{0,}育(经\\s{0,}验|经\\s{0,}历|背\\s{0,}景|概\\s{0,}况)(\\s{1,}|:|top)","\n@教育经历\n\t");
		strTxt = strTxt.replaceAll("\\s{0,}毕\\s{0,}业\\s{0,}学\\s{0,}校(\\s{2,}|:)", "\n@教育经历\n\t");

		// ------------------------------项目经历---------------------------------
		// 项目经历
		strTxt = strTxt.replaceAll("\\s{0,}项目(\\s{0,}|管理)经(验|历)(\\s{1,}|:)","\n@项目经历\n\t");
		// 防止有多个名称，不能全部替换
		if (strTxt.indexOf(" 经验:") > -1) {
			strTxt = strTxt.replace("\\s{1,}经验:", "\n@项目经历\n\t");
		}
		// ------------------------------专业技能---------------------------------
		strTxt = strTxt.replaceAll("\\s{0,}(IT|职业|专业)\\s{0,}技能(\\s{0,}|及特长|与特长)", "\n@专业技能\n\t");
		strTxt = strTxt.replaceAll("\\s{0,}职业特长和技能(\\s{1,}|:)", "\n@专业技能\n\t");
		strTxt = strTxt.replaceAll("\\s{0,}擅长技术(\\s{1,}|:)", "\n@专业技能\n\t");
		strTxt = strTxt.replaceAll("\\s{0,}技能特长(\\s{1,}|:)", "\n@专业技能\n\t");
		strTxt = strTxt.replaceAll("\\s{0,}专业技术(\\s{1,}|:)", "\n@专业技能\n\t");

		// ------------------------------证书---------------------------------
		strTxt = strTxt.replaceAll("\\s{0,}证\\s{0,}书(\\s{1,}|:)", "\n@证书\n\t");
		// 求职意向
		strTxt = strTxt.replaceAll("\\s{0,}求职意向(\\s{1,}|:)", "\n@求职意向\n\t");
		// 培训经历
		strTxt = strTxt.replaceAll("\\s{0,}培训经(验|历)(\\s{1,}|:)", "\n@培训经历\n\t");
		// 语言能力
		strTxt = strTxt.replaceAll("\\s{0,}语言能力(\\s{1,}|:)", "\n@语言能力\n\t");
		strTxt = strTxt.replaceAll("\\s{0,}语言(及|或)方言(\\s{0,}|:)", "\n@语言能力\n\t");
		// 职业状况
		strTxt = strTxt.replaceAll("\\s{0,}职业概况(\\s{1,}|:)", "\n@职业概况\n\t");
		// 专业描述
		strTxt = strTxt.replaceAll("\\s{0,}专\\s{0,}业\\s{0,}描\\s{0,}述(\\s{1,}|:)", "\n@专业描述\n\t");
		// 其他信息
		strTxt = strTxt.replaceAll("\\s{0,}其\\s{0,}他\\s{0,}信\\s{0,}息(\\s{1,}|:)", "\n@其他信息\n\t");
		// 若有特色字符，再次替换
		strTxt = strTxt.replaceAll("(top|\\?|●)", "");
		return strTxt + "@";

	}
	//提取时间格式的正则
	public static String getRutunTimeReg() {
		String statTimeAge = "[0-9]{4}\\s{0,}[年|\\-|/|.](\\s{1,}|(\\s{0,}|(\\s{0,}[0-9]{1,2}(月|\\s{0,}))))";
		String center = "\\s{0,}(\\-|至|到|--|—|——)\\s{0,}";// 中间连接符合
		String endTimage = "([0-9]{4}\\s{0,}[年|\\-|/|.](\\s{1,}|(\\s{0,}[0-9]{1,2}(月|\\s{0,})))|至今|现在|now)";
		String reg = statTimeAge + center + endTimage;
		return reg;
	}

	// 提取时间格式的正则 类似 2002-2008
	public static String getRutunTimeReg2() {
		String statTimeAge = "([0-9]{4}\\s{0,}|[0-9]{4}\\s{0,}[年|\\-|/|.](\\s{1,}|(\\s{0,}|(\\s{0,}[0-9]{1,2}(月|\\s{0,})))))";
		String center = "\\s{0,}(\\-|至|到|--|—|——)\\s{0,}";// 中间连接符合
		String endTimage = "([0-9]{4}\\s{0,}|[0-9]{4}\\s{0,}[年|\\-|/|.](\\s{1,}|(\\s{0,}[0-9]{1,2}(月|\\s{0,})))|至今|现在|now)";
		String reg = statTimeAge + center + endTimage;
		return reg;
	}
	//日期测试
	public static void main1(String[] args) {
		String str = "2011 /12--2010 /3  山东 1997年-2001年  2003/6—2007/5   大连 2010年9月--2011年11月 河北 2010年9月--至今   2007.08 - 2011.02北京";
		String jiaoyuReg = getRutunTimeReg();
		Pattern p = Pattern.compile(jiaoyuReg);
		Matcher m = p.matcher(str);
		while (m.find()) {
			System.out.println(m.group());
		}
	}
	//提取汉字，包括中英文 +，.
	public static String getString() {
		String reg = "(\\w|\\s{0,}|\\D)(\\w+\\D+[^a-z]+[\u4e00-\u9fa5]){1,}";
		return reg;
	}
	//时间统一转换
	public static String getTime(String time) {
		time = time.replaceAll("\\.", "年");
		time = time.replaceAll("\\s{0,}/","年");
		time = time.replaceAll("\\s{0,}//","年");
		time = time.replaceAll("——", "-");
		time = time.replaceAll("—", "-");
		time = time.replaceAll("\\-\\-", "-");
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		time = time.replaceAll("(至今|现在|now)", year + "年" + month + "月");
		time = time.replaceAll("至", "-");
		time = time.replaceAll("到", "-");
		time = time.replaceAll("\\s{1,}", "");
		return time;
	}
	//信息读取
	public static Map<String, Object> txtDis_Map(MultipartFile file)
		throws Exception {
		LinkedHashMap<String, Object> mapfiles = new LinkedHashMap<String, Object>();
		//读取到的关键字作为key，值可能多行用object存储作为value
		String read = "";
		String houzui = file.getOriginalFilename();
		if (houzui.endsWith(".txt") || houzui.endsWith(".TXT")) {
			read = txt(file);
		} else if (houzui.endsWith(".doc") || houzui.endsWith(".DOC")) {
			read = doc(file);
		} else if (houzui.endsWith(".docx") || houzui.endsWith(".DOCX")) {
			read = docx(file);
		} else {
			System.out.println("当前文件是:" + houzui);
			return null;
		}
		//对文本进行统一处理
		read = setString(read);
		//姓名
		mapfiles.put("name", getName(read));
		// 性别
		mapfiles.put("sex", getSex(read));
		// 年龄
		mapfiles.put("age", getAge_year(read));
		// 邮箱
		mapfiles.put("email", getEmail(read));
		// 手机号
		mapfiles.put("tel", getMobile(read));
		// 户口
		mapfiles.put("huku", getHukou(read));
		// 现在居住地方
		mapfiles.put("address", getNowPlace(read));
		// 工作年限
		mapfiles.put("worktime", getworkTime(read));
		// 最高学历
		mapfiles.put("highestdegree", getHighDegree(read));
		// 目前公司
		mapfiles.put("company", getcompany(read));
		// 目前职位
		mapfiles.put("position", getposition(read));
		// 目前从事行业
		mapfiles.put("curindustry", getCurindustry(read));
		// 目前从事地区
		mapfiles.put("currentlocation", getNowPlace(read));
		// 目前年薪
		mapfiles.put("nowmoney", getNowmoney(read));
		// 期望从事行业
		mapfiles.put("期望从事行业", getExpectationsindustry(read));
		// 期望从事职位
		mapfiles.put("期望从事职位", getExpectedpositions(read));
		// 期望从事地区
		mapfiles.put("期望从事地区", getExpectationsplace(read));
		// 期望月薪
		mapfiles.put("期望月薪", getMyYexin(read));
		// 自我评价
		mapfiles.put("自我评价", getMyPinJia(read));
		// 工作经历
		mapfiles.put("工作经历", workExpPro(read));
		// 项目经历
		mapfiles.put("项目经验", prodc(read));
		// 证书
		mapfiles.put("证书", letter(read));
		// 教育经历
		mapfiles.put("教育经历", getJiaoYu(read));
		// 语言能力
		mapfiles.put("语言能力", getYuyan(read));
		// 技能
		mapfiles.put("专业技能", proSkills(read));
		// 专业关键词
		mapfiles.put("keyword","");
		// 专业领域
		mapfiles.put("keywordmessage", KeyWord.getKeyWord(read));

		return mapfiles;
	}

	public static void main(String[] args) throws Exception {
		String read = "";
		String path = "F:\\51简历\\2011.7以后--2012年6月1日之前\\JAVA\\测试\\傅尚斌--推荐宽连十方.txt";
		// Tools.tree(new File("F:\\51简历\\异常简历"));
		// for (int i = 0; i <4; i++) {
		List list=new ArrayList();
		for (int i = 0; i <list.size(); i++) {
			String test =list.get(i).toString();
			File file = new File(test);
			String houzui = file.getAbsolutePath();
			if (houzui.endsWith(".txt") || houzui.endsWith(".TXT")) {
//				read = txt(file);
			} else if (houzui.endsWith(".doc") || houzui.endsWith(".DOC")) {
				read = ReadFileUtils.readWORD(file);
			} else if (houzui.endsWith(".docx") || houzui.endsWith(".DOCX")) {
				read = ReadFileUtils.readWORD2007(file);
			} else if (houzui.endsWith(".pdf") || houzui.endsWith(".PDF")) {
				//read = ReadFileUtils.readPDF(file);
			} else if (houzui.endsWith(".xls") || houzui.endsWith(".XLS")) {
				//read = ReadFileUtils.readEXCEL(file.getPath());
			} else if (houzui.endsWith(".xlsx") || houzui.endsWith(".XLSx")) {
			     //read = ReadFileUtils.readEXCEL2007(file.getPath());
			}
			// 格式化内容
			read = setString(read);
			//System.out.println(read);
			//System.out.println(i + "===" + test);
			System.err.println("1姓名："+ ResumePro1.getName(read));
			System.err.println("2性别：" + ResumePro1.getSex(read));
			System.err.println("3工作年限：" + ResumePro1.getworkTime(read));
			System.err.println("4手机号码：" + ResumePro1.getMobile(read));
			System.err.println("5年龄：" + ResumePro1.getAge_year(read));
			System.err.println("6电子邮件：" + ResumePro1.getEmail(read));
			System.err.println("7学历：" + ResumePro1.getHighDegree(read));
			System.err.println("7户口：" + ResumePro1.getHukou(read));
			System.err.println("8现居住地：" + ResumePro1.getNowPlace(read));
			System.err.println("9当前公司：" + ResumePro1.getcompany(read));
			System.err.println("10当前职位:" + ResumePro1.getposition(read));
			System.err.println("11当前行业:" + ResumePro1.getCurindustry(read));
			System.err.println("12期望从事职业："
					+ ResumePro1.getExpectedpositions(read));
			System.err.println("13期望从事行业："
					+ ResumePro1.getExpectationsindustry(read));
			System.err.println("14期望从事地区："
					+ ResumePro1.getExpectationsplace(read));
			System.err.println("15期望薪水：" + ResumePro1.getMyYexin(read));
			System.err.println("16目前年薪：" + ResumePro1.getNowmoney(read));
			System.err.println("16自我评价：" + ResumePro1.getMyPinJia(read));
			System.err.println("17证书：" + ResumePro1.letter(read));
			System.err.println("18项目经历：" + ResumePro1.prodc(read));
			System.err.println("19工作经历：" + ResumePro1.workExpPro(read));
			System.err.println("20教育经历：" + ResumePro1.getJiaoYu(read));
			System.err.println("21语言能力：" + ResumePro1.getYuyan(read));
			System.err.println("22专业技能：" + ResumePro1.proSkills(read));
		}

	}

	// 1.个人姓名
	public static String getName(String nofe) {
		String name = "";
		nofe = nofe.replaceAll("姓" + reg + "名", "姓名");
		if (nofe.indexOf("姓名:") <= -1) {
			nofe = nofe.replaceAll("姓名\\s{1,}", "姓名:");
		}
		nofe = nofe.replaceAll("姓名:\\s{0,}", "姓名:");
		//1.有标题的和没有标题的
		if (nofe.indexOf("姓名:") > -1) {
			//如果姓后面有空格，去掉在提取
			Pattern pk = Pattern.compile("姓名:\\s{0,}[\u4E00-\u9FA5]{1,3}\\s{1,}");
			Matcher mk = pk.matcher(nofe);
			if (mk.find()) {
				nofe = nofe.replaceAll("姓名:\\s{0,}[\u4E00-\u9FA5]{1,3}\\s{1,}",mk.group().trim());
			} else {
				nofe = nofe.substring(nofe.indexOf("姓名:") + 3, nofe.length());
				name = nofe.split("\\s{1,}")[0];
			}
				//先去空格，在提取
				Pattern p = Pattern.compile("姓名:\\s{0,}[\u4E00-\u9FA5]{1,4}");
				Matcher m = p.matcher(nofe);
				if (m.find()) {
					name = m.group().replaceAll("姓名:", "");
				}
			}else {
				//头部
				nofe = nofe.replaceAll("智联招聘", "");
				nofe = nofe.replaceAll("手" + reg + "机", "手机:");
				nofe = nofe.replaceAll("邮" + reg + "箱", "邮箱:");
				//尾部截取
				if (nofe.indexOf("男 ") > -1) {
					nofe = nofe.substring(0, nofe.indexOf("男 "));
				} else if (nofe.indexOf("女 ") > -1) {
					nofe = nofe.substring(0, nofe.indexOf("女 "));
				} else if (nofe.indexOf("手机:") > -1) {
					nofe = nofe.substring(0, nofe.indexOf("手机:"));
				} else if (nofe.indexOf("出生日期") > -1) {
					nofe = nofe.substring(0, nofe.indexOf("出生日期"));
				} else if (nofe.indexOf("出生年月") > -1) {
					nofe = nofe.substring(0, nofe.indexOf("出生年月"));
				} else if (nofe.indexOf("岁 ") > -1) {
					nofe = nofe.substring(0, nofe.indexOf("岁 "));
				} else if (nofe.indexOf("邮箱:") > -1) {
					nofe = nofe.substring(0, nofe.indexOf("邮箱:"));
				} else if (nofe.indexOf("E-mail:") > -1) {
					nofe = nofe.substring(0, nofe.indexOf("E-mail:"));
				}else if (nofe.indexOf("工作经历") > -1) {
					nofe = nofe.substring(0, nofe.indexOf("工作经历"));
				}
				//头部截取
				if (nofe.indexOf("中文简历") > -1) {
					int count = nofe.indexOf("中文简历") + 4;
					nofe = nofe.substring(count, nofe.length());
				}if(nofe.indexOf("个人信息") > -1) {
					int count = nofe.indexOf("个人信息") + 4;
					nofe = nofe.substring(count, nofe.length());
				}
				if (nofe.indexOf("英文简历") > -1) {
					int count = nofe.indexOf("英文简历") + 4;
					nofe = nofe.substring(count, nofe.length());
				}
				if (nofe.indexOf("简历编号") > -1) {
					int count = nofe.indexOf("简历编号");
					nofe = nofe.substring(0, count);
				}
				if (nofe.indexOf("添加标签") > -1) {
					int count = nofe.indexOf("添加标签");
					nofe = nofe.substring(count, nofe.length());
				}
				if (nofe.lastIndexOf("简历关键字:") > -1) {
					int s = nofe.lastIndexOf("简历关键字:") + 5;
					nofe = nofe.substring(s, nofe.length());
				}
				if (nofe.indexOf("简历匹配度:") > -1) {
					int count = nofe.indexOf("简历匹配度:") + 7;
					nofe = nofe.substring(count, nofe.length());
				}
				if (nofe.indexOf("应聘职位:") > -1) {
					int count = nofe.indexOf("应聘职位:") + 6;
					nofe = nofe.substring(count, nofe.length());
				}
				if (nofe.indexOf("应聘公司:") > -1) {
					int count = nofe.indexOf("应聘公司:") + 5;
					nofe = nofe.substring(count, nofe.length());
				}
				if(nofe.indexOf("投递时间:") > -1) {
					int count = nofe.indexOf("投递时间:") + 4;
					nofe = nofe.substring(count, nofe.length());
				}if(nofe.indexOf("个人信息 ") > -1) {
					int count = nofe.indexOf("个人信息 ") + 4;
					nofe = nofe.substring(count, nofe.length());
				}if(nofe.indexOf("简历更新时间") > -1) {
					int count = nofe.indexOf("简历更新时间") +6;
					nofe = nofe.substring(count, nofe.length());
				}
					//txt有对标题的分割
					if (nofe.lastIndexOf("@#") > -1) {
						nofe = nofe.substring(nofe.lastIndexOf("@#"), nofe.length());
					}
					//姓名库提取
					name = NameKu.getNameByKu(nofe);
					//姓名
					name = name.replaceAll(reg, "");
				}
			//暂时没复姓
		if (!name.equals("") && name.length() >= 4) {
			name = name.substring(0, 3);
		}
		name = name.replaceAll("(的|简|出|工|性|手|\\|)", "");
		name = name.replaceAll(reg, "");
		return name;
	}
	// 工作年限
	public static String getworkTime(String nofe) {
		String workTime = "";
		if (nofe.indexOf("工作年限:") <= -1) {
			nofe = nofe.replaceAll("工作年限\\s{1,}", "工作年限:");
			nofe = nofe.replaceAll("工作年限:\\s{0,}", "工作年限:");
		}
		if (nofe.indexOf("工作年限:") > -1) {
			Pattern p = Pattern.compile("工作年限:\\s{0,}([0-9]|[\u4E00-\u9FA5]){1,4}年");
			Matcher m = p.matcher(nofe);
			if (m.find()) {
				workTime = m.group().replaceAll("(工作年限|\\:)", "");
			}
		}
		//没有查询到的
		if (workTime.equals("") || workTime.indexOf("0年") > -1) {
			if (nofe.indexOf("年以上工作经验") > -1) {
				workTime = nofe.substring(0, nofe.indexOf("年以上工作经验") + 1);
			} else if (nofe.indexOf("年工作经验") > -1) {
				workTime = nofe.substring(0, nofe.indexOf("年工作经验") + 1);
			} else if (nofe.indexOf("年工作年限") > -1) {
				workTime = nofe.substring(0, nofe.indexOf("年工作年限") + 1);
			} else if (nofe.indexOf("最近工作" + reg + "[") > -1) {
				workTime = nofe.substring(nofe.indexOf("最近工作"), nofe.length());
				workTime = workTime.substring(5, workTime.indexOf("年") + 1);
			}
			// 上面的情况存在
			if (!workTime.equals("")) {
				// 去掉2012年的情况，防止出现生日日期
				Pattern p = Pattern.compile("[0-9]{4}\\s{0,}年");
				Matcher m = p.matcher(workTime);
				while (m.find()) {
					workTime = workTime.replaceAll(m.group(), "");
				}
				//开始查询2年
				Pattern p2 = Pattern.compile("[0-9]{1,}年");
				Matcher m2 = p2.matcher(workTime);
				if (m2.find()) {
					workTime = m2.group();
				} else {
					//查询汉字两年
					Pattern p3 = Pattern.compile("[\u4E00-\u9FA5]{1,}年");
					Matcher m3 = p3.matcher(workTime);
					if (m3.find()) {
						workTime = m3.group();
						workTime=workTime.replaceAll("年", "");
					}
				}
			} else {
				// 工作时间段
				if (nofe.indexOf("工作时间:") <= -1) {
					nofe = nofe.replaceAll("工作时间\\s{1,}", "工作时间:");
					nofe = nofe.replaceAll("工作时间:\\s{0,}", "工作时间:");
				}
				if (nofe.indexOf("工作日期:") <= -1) {
					nofe = nofe.replaceAll("工作日期\\s{1,}", "工作日期:");
					nofe = nofe.replaceAll("工作日期:\\s{0,}", "工作日期:");
				}
				if (nofe.indexOf("工作时间:") > -1 || nofe.indexOf("工作日期:") > -1) {
					Pattern chup = Pattern.compile("工作(时间|日期):\\s{0,}[0-9]{4}[年|\\-|/][0-9]{1,2}月[至|到]\\s{0,}[0-9]{4}[年|\\-|/][0-9]{1,2}月");
					Matcher ms = chup.matcher(nofe);
					if (ms.find()) {
						workTime = ms.group();
						Pattern ps = Pattern.compile("[0-9]{4}");
						Matcher my = ps.matcher(workTime);
						String amin = "";
						while (my.find()) {
						    amin += my.group() + "-";
						}
						if (amin.indexOf("-") > -1) {
							String starime = amin.split("-")[0];
							String endtime = amin.split("-")[1];
							workTime = Integer.valueOf(endtime)- Integer.valueOf(starime)+"";
						}
					}
				} else {
					if (nofe.indexOf("@工作经历") > -1) {
						nofe = nofe.substring(nofe.indexOf("@工作经历") + 6, nofe.length());
						nofe = nofe.substring(0, nofe.indexOf("@"));
						//提取时间格式的正则
						String jiaoyuReg = getRutunTimeReg();
						Pattern p = Pattern.compile(jiaoyuReg);
						Matcher m = p.matcher(nofe);
						List<Integer> list = new ArrayList<Integer>();
						int s = 0;
						while (m.find()) {
							//对时间进行同一整理
							String time = m.group();
							time = getTime(time);
							if (time.indexOf("-") > -1) {
								String strp[] = time.split("-");
								for (int j = 0; j < strp.length; j++) {
									String year = strp[j].replaceAll(reg, "");
									if (year.indexOf("年") > -1) {
										String years = year.split("年")[0];
										String yue = "0";
										if (year.split("年").length == 2) {
											yue = year.split("年")[1];
										}
										if (yue.indexOf("月") > -1) {
											yue = yue.substring(0, yue.indexOf("月"));
										}
										int yueDay = 0;
										try {
											if (!yue.equals("")) {
												try {
													yueDay = Integer.valueOf(yue);
												} catch (Exception e) {
												}
											}
											//对时间进行调整
											int times = Integer.valueOf(years)* 12 + yueDay;
											list.add(times);
											s++;
										} catch (Exception e) {
										}
									}

								}
							}
						}
						Integer[] array = new Integer[s];
						if (list != null && list.size()>0) {
							workTime = ArrayListUtils.getMaxAndMin((Integer[]) list.toArray(array));
						}
					}
				}
			}
		}
		workTime = workTime.replaceAll("(发|\\|)", "");
		if (workTime.equals("")) {
			workTime = "0";
		}
		//对时间统一转化
		String workTimeReturn=Opinion.workTime(workTime.replaceAll(reg, ""));
		//如果是年份
		if(workTimeReturn.length()==4){
			try {
				workTimeReturn=(new Date().getYear()-Integer.valueOf(workTimeReturn))+"";
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return workTimeReturn;
	}
	// 2.性别
	public static String getSex(String nofe) {
		String sex = "";
		nofe = nofe.replaceAll("性" + reg + "别", "性别");
		if (nofe.indexOf("性别:") <= -1) {
			nofe = nofe.replaceAll("性别\\s{1,}",  "性别:");
			nofe = nofe.replaceAll("性别:\\s{0,}", "性别:");
		}
		if (nofe.indexOf("性别:") > -1) {
			nofe = nofe.substring(nofe.indexOf("性别:") + 3, nofe.length());
		}
		//直接检索
		if (nofe.indexOf("男") > -1 || nofe.indexOf("女") > -1) {
			sex = nofe.indexOf("男") > -1 ? "男" : "女";
		} else {
			sex = "不详";
		}
		return sex.replaceAll(reg, "");
	}

	// 手机号码 电话
	public static String getMobile(String nofe) {
		nofe = nofe.replaceAll("电" + reg + "话", "电话");
		nofe = nofe.replaceAll("手" + reg + "机", "手机");
		if (nofe.indexOf("电话:") <= -1) {
			nofe = nofe.replaceAll("电话\\s{1,}", "电话:");
			nofe = nofe.replaceAll("电话:\\s{0,}", "电话:");
		}
		if (nofe.indexOf("电话:") > -1) {
			nofe = nofe.substring(nofe.indexOf("电话:") + 3, nofe.length());
		}
		if (nofe.indexOf("手机:") > -1) {
			nofe = nofe.substring(nofe.indexOf("手机:") + 3, nofe.length());
		}
		if (nofe.indexOf("女 ") > -1) {
			nofe = nofe.substring(nofe.indexOf("女 "), nofe.length());
		}
		if (nofe.indexOf("男 ") > -1) {
			nofe = nofe.substring(nofe.indexOf("男 "), nofe.length());
		}
		// 缩短距离
		if (nofe.indexOf("@") > -1) {
			nofe = nofe.substring(0, nofe.indexOf("@"));
		}if(nofe.indexOf("E-mail") > -1) {
			nofe = nofe.substring(0, nofe.indexOf("E-mail"));
		}
		String mobile = "";
		//从字符串中提取电话号码
		Pattern p = Pattern.compile("[^0]\\d{1,2}[-]?\\d{7,8}|13\\d{9}|15\\d{9}");
		Matcher m = p.matcher(nofe);
		//这是提取多个，这里只提取一个
		if (m.find()) {
			mobile = m.group();
		}
		mobile = mobile.replaceAll(":", "");
		return mobile.replaceAll(reg, "");
	}
	//3.年龄
	public static String getAge_year(String nofe) {
	    String age = "";
	    String myear = "";
	    String month = "";
	    nofe = nofe.replaceAll("年" + reg + "龄", "年龄");
	    if (nofe.indexOf("年龄:") <= -1) {
	      nofe = nofe.replaceAll("年龄\\s{1,}", "年龄:");
	      nofe = nofe.replaceAll("年龄:\\s{0,}", "年龄:");
	    }
	    nofe = nofe.replaceAll("出" + reg + "生" + reg + "日" + reg + "期", "出生日期");
	    Pattern p = Pattern.compile("[0-9]{2}\\s{0,}(岁|\\s{0,})\\s{0,}（\\s{0,}[0-9]{4}[年|\\-|/][0-9]{1,2}");
	    Matcher m = p.matcher(nofe);
	    if (m.find()) {
		      String time = m.group();
		      Matcher my = Pattern.compile("[0-9]{4}").matcher(time);
		      if (my.find()) {
		        myear = my.group();
		      }
		      Matcher mt = Pattern.compile("[年|\\-|/][0-9]{1,2}").matcher(time);
		      if (mt.find()) {
		        month = mt.group().replaceAll("[年|\\-|/]", "");
		      }
		      age = myear + "-" + month;
	    }
	    else if (nofe.indexOf("年龄:") > -1) {
		      Matcher mt = Pattern.compile("年龄:\\s{0,}[0-9]{1,2}").matcher(nofe);
		      String newAge = "";
		      int nowage = 0;
		      if (mt.find()) {
		        newAge = mt.group().replaceAll(reg, "");
		        newAge = newAge.substring(newAge.indexOf(":") + 1);
		      }
		      int year = Calendar.getInstance().get(1);
		      try {
		        if (!(newAge.equals(""))) {
		          nowage = year - Integer.valueOf(newAge).intValue();
		          age = nowage + "-" + "1";
		        }
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
	    } else {
	        Pattern p3 = Pattern.compile("[0-9]{1,2}\\s{0,}岁");
	        Matcher m3 = p3.matcher(nofe);
	        if (m3.find()) {
	          String time = m3.group();
	          int nowage = 0;
	          Matcher my = Pattern.compile("[0-9]{1,2}").matcher(time);
	          if (my.find()) {
	            myear = my.group();
	            int year = Calendar.getInstance().get(1);
	            try{
	              nowage = year - Integer.valueOf(myear);
	              age = nowage + "-" + "1";
	            } catch (Exception e) {
	              e.printStackTrace();
	            }
	          }
		        } else {
		          Pattern chup = Pattern.compile("(\\s{0,}|(出生(时间|日期):))\\s{0,}[0-9]{4}\\s{0,}[.|年|\\-|/][0-9]{1,2}[月|\\s{0,}]");
		   	      Matcher mt = chup.matcher(nofe);
		   	      if (mt.find()) {
		   	        String time = mt.group();
		   	        Matcher my = Pattern.compile("[0-9]{4}").matcher(time);
		   	        if (my.find()) {
		   	          myear = my.group();
		   	        }
		   	        Matcher mts = Pattern.compile("[年|\\-|/][0-9]{1,2}").matcher(
		   	          time);
		   	        if (mts.find()) {
		   	          month = mts.group().replaceAll("[年|\\-|/]", "");
		   	        }
		   	        age = myear + "-" + month;
		   	      } else {
		   	    	 age = "1980-1";
		   	      }
		        }
	    }
	    return age.replaceAll(reg, "");
	  }
	// 4.电子邮件 E-mail
	public static String getEmail(String nofe) {
		String mobile = "";
		Pattern p = Pattern.compile("[\\w[.-]]+\\@[\\w[.-]]{2,}\\.[\\w[.-]]+");
		Matcher m = p.matcher(nofe);
		if (m.find()) {
			mobile = m.group();
		}
		return mobile.replaceAll(reg, "");
	}
	//最高学历
	public static String getHighDegree(String nofe) {
		String highDegree = "";
		if (nofe.indexOf("MBA/EMBA") > -1) {
			highDegree = "MBA/EMBA";
		} else if (nofe.indexOf("硕士后") > -1) {
			highDegree = "硕士后";
		} else if (nofe.indexOf("硕士") > -1) {
			highDegree = "硕士";
		} else if (nofe.indexOf("博士") > -1) {
			highDegree = "博士";
		} else if (nofe.indexOf("本科") > -1) {
			highDegree = "本科";
		} else if (nofe.indexOf("大专") > -1) {
			highDegree = "大专";
		} else {
			highDegree = "";
		}
		//转换学历
		return Opinion.eduOpin(highDegree.replaceAll(reg, ""));
	}
	// 现居住地
	public static String getNowPlace(String nofe) {
		String place = "";
		nofe = nofe.replaceAll("住" + reg + "址", "住址");
		nofe = nofe.replaceAll("住" + reg + "地", "住地");
		if (nofe.indexOf("住址") > -1) {
			place = nofe.substring(nofe.indexOf("住址") + 3, nofe.length());
		} else if (nofe.indexOf("所在地") > -1) {
			place = nofe.substring(nofe.indexOf("所在地") + 4, nofe.length());
		} else if (nofe.indexOf("住地") > -1) {
			place = nofe.substring(nofe.indexOf("住地") + 3, nofe.length());
		} else if (nofe.indexOf("居住地") > -1) {
			place = nofe.substring(nofe.indexOf("居住地") + 4, nofe.length());
		} else if (nofe.indexOf("现居住于") > -1) {
			place = nofe.substring(nofe.indexOf("现居住于") + 4, nofe.length());
		} else if (nofe.indexOf("现居住城市") > -1) {
			place = nofe.substring(nofe.indexOf("现居住城市") + 6, nofe.length());
		} else if (nofe.indexOf("现居城市") > -1) {
			place = nofe.substring(nofe.indexOf("现居城市") + 5, nofe.length());
		}
		Matcher matcher = Pattern.compile("[\u4e00-\u9fa5]{1,}").matcher(place);
		if (matcher.find()) {
			place = matcher.group();
		} else {
			place = "不详";
		}
		return place.replaceAll(reg, "");
	}

	// 目前年薪
	public static String getNowmoney(String nofe) {
		String nowmoney = "";
		if (nofe.indexOf("年薪:") > -1) {
			nowmoney = nofe.substring(nofe.indexOf("年薪:") + 3, nofe.length());
		} else if (nofe.indexOf("薪金:") > -1) {
			nowmoney = nofe.substring(nofe.indexOf("薪金:") + 3, nofe.length());
		} else if (nofe.indexOf("目前薪资:") > -1) {
			nowmoney = nofe.substring(nofe.indexOf("目前薪资:") + 5, nofe.length());
		}
		if (!nowmoney.equals("")) {
			nowmoney = nowmoney.trim();
			nowmoney = nowmoney.split("\\s{1,}")[0];
		}
		if (nowmoney.indexOf("万") > -1) {
			nowmoney = nowmoney.substring(0, nowmoney.indexOf("万"));
		} else if (nowmoney.indexOf("W") > -1) {
			nowmoney = nowmoney.substring(0, nowmoney.indexOf("W"));
		}
		nowmoney = nowmoney.replaceAll(reg, "");
		if (nowmoney.length() > 8) {
			nowmoney = nowmoney.substring(0, 8);
		}
		return nowmoney;
	}

	// 户口
	public static String getHukou(String nofe) {
		String hukou = "";
		nofe = nofe.replaceAll("户" + reg + "口", "户口");
		nofe = nofe.replaceAll("籍" + reg + "贯", "籍贯");
		if (nofe.indexOf("户口所在地") > -1) {
			hukou = nofe.substring(nofe.indexOf("户口所在地") + 6, nofe.length());
		} else if (nofe.indexOf("户口") > -1) {
			hukou = nofe.substring(nofe.indexOf("户口") + 3, nofe.length());
		} else if (nofe.indexOf("籍贯") > -1) {
			hukou = nofe.substring(nofe.indexOf("籍贯") + 3, nofe.length());
		} else if (nofe.indexOf("国家或地区:") > -1) {
			hukou = nofe.substring(nofe.indexOf("国家或地区:") + 6, nofe.length());
		}
		hukou = hukou.trim();
		hukou = hukou.split("(\\s{1,}|\\||\\:)")[0];
		return hukou.replaceAll(reg, "");
	}
	//自我评价
	public static String getMyPinJia(String nofe) {
		String pinJia = "";
		if(nofe.indexOf("@自我评价") > -1) {
			pinJia = nofe.substring(nofe.indexOf("@自我评价") + 5, nofe.length());
			//截取第一步
			pinJia = pinJia.substring(0, pinJia.indexOf("@") > -1 ? pinJia.indexOf("@") : pinJia.length());
			//截取第二步
			pinJia = pinJia.substring(0, pinJia.indexOf(":") > -1? pinJia.indexOf(":"):pinJia.length());
			//截取第三步
			pinJia = pinJia.substring(0, pinJia.indexOf("期望从事") > -1? pinJia.indexOf("期望从事"):pinJia.length());
			//截取第四步
			pinJia = pinJia.substring(0, pinJia.indexOf("。 ") > -1?pinJia.lastIndexOf("。 ")+1:pinJia.length());
			//替换
			pinJia = pinJia.replaceAll("自我评价(\\:|\\s{0,})", "");
		}
		return pinJia.replaceAll(reg, "");
	}

	// 期望从事行业
	public static String getExpectationsindustry(String nofe) {
		String hangye = "";
		if (nofe.indexOf("期望从事行业:") > -1) {
			hangye = nofe.substring(nofe.indexOf("期望从事行业:") + 7, nofe.length());
		} else if (nofe.indexOf("望行业:") > -1) {
			hangye = nofe.substring(nofe.indexOf("望行业:") + 4, nofe.length());
		} else if (nofe.indexOf("期望从事工作行业:") > -1) {
			hangye = nofe.substring(nofe.indexOf("期望从事工作行业:") + 9, nofe.length());
		} else {
			//查询全文
			nofe = nofe.substring(0, nofe.indexOf("@工作经历") > -1?nofe.indexOf("@工作经历"):nofe.length());
			//期望行业
			hangye = Hangye.getHangye(nofe, true);
		}
		// 替换所有工作中的特殊字符
		hangye = hangye.replaceAll("(;|，|\\||、|；)", ",");
		hangye = hangye.replaceAll("\\s{0,},\\s{0,}", ",");
		hangye = hangye.trim();
		hangye = hangye.split("\\s{1,}")[0];
		if (hangye.indexOf(",") <= -1) {
			hangye = hangye + ",";
		}
		hangye = hangye.substring(0, hangye.length()>45?45:hangye.length());
		// 防止没有空格
		if (hangye.indexOf("期望") > -1) {
			hangye = hangye.substring(0, hangye.indexOf("期望"));
		}
		return hangye.replaceAll(reg, "");
	}
	//期望从事职业
	public static String getExpectedpositions(String nofe) {
		String zhiwei = "";
		if (nofe.indexOf("期望从事职业:") > -1) {
			zhiwei = nofe.substring(nofe.indexOf("期望从事职业:") + 7, nofe.length());
		} else if (nofe.indexOf("望职位:") > -1) {
			zhiwei = nofe.substring(nofe.indexOf("望职位:") + 4, nofe.length());
		} else if (nofe.indexOf("期望从事工作职业:") > -1) {
			zhiwei = nofe.substring(nofe.indexOf("期望从事工作职业:") + 9, nofe.length());
		} else if (nofe.indexOf("望职业:") > -1) {
			zhiwei = nofe.substring(nofe.indexOf("望职业:") + 4, nofe.length());
		} else if (nofe.indexOf("目标职能") > -1) {
			zhiwei = nofe.substring(nofe.indexOf("目标职能") + 5, nofe.length());
		} else if (nofe.indexOf("推荐职位") > -1) {
			zhiwei = nofe.substring(nofe.indexOf("推荐职位") + 5, nofe.length());
		}else{
			// 查询全文
			if (nofe.indexOf("@工作经历") > -1) {
				nofe = nofe.substring(0, nofe.indexOf("@工作经历"));
				zhiwei = PosionBank.getPosion(nofe);
			}
		}
		//防止没有空格的情况出现
		zhiwei = zhiwei.substring(0, zhiwei.indexOf(":") > -1? zhiwei.indexOf(":"):zhiwei.length());
		// 替换所有职位中的特殊字符
		zhiwei = zhiwei.replaceAll("(;|，|\\||、|；)", ",");
		zhiwei = zhiwei.replaceAll("\\s{0,},\\s{0,}", ",");
		zhiwei = zhiwei.trim();
		zhiwei = zhiwei.split("\\s{1,}")[0];
		zhiwei = zhiwei.replaceAll(reg, "");
		if (!zhiwei.equals("") && zhiwei.indexOf(",") <= -1) {
			zhiwei = zhiwei + ",";
		}
		return zhiwei;
	}
	//期望从事地区
	public static String getExpectationsplace(String nofe) {
		String expectationsplace = "";
		if (nofe.indexOf("期望地区") > -1) {
			expectationsplace = nofe.substring(nofe.indexOf("期望地区") + 4, nofe.length());
		} else if (nofe.indexOf("期望工作地区") > -1) {
			expectationsplace = nofe.substring(nofe.indexOf("期望工作地区") + 7, nofe.length());
		} else if (nofe.indexOf("期望地点") > -1) {
			expectationsplace = nofe.substring(nofe.indexOf("期望地点") + 5, nofe.length());
		} else if (nofe.indexOf("目标地点") > -1) {
			expectationsplace = nofe.substring(nofe.indexOf("目标地点") + 5, nofe
					.length());
		} else if (nofe.indexOf("工作地点") > -1) {
			expectationsplace = nofe.substring(nofe.indexOf("工作地点") + 5, nofe.length());
		} else if (nofe.indexOf("目标城市") > -1) {
			expectationsplace = nofe.substring(nofe.indexOf("目标城市") + 5, nofe.length());
		}
		expectationsplace = expectationsplace.trim();
		if (!expectationsplace.equals("")&& expectationsplace.indexOf(" ") > -1) {
			expectationsplace = expectationsplace.trim();
			expectationsplace = expectationsplace.split("\\s{1,}")[0];
		}
		expectationsplace = Opinion.areaOpin2(expectationsplace);
		return expectationsplace;
	}

	// 期望月薪
	public static String getMyYexin(String nofe) {
		String money = "";
		if (nofe.indexOf("期望月薪:") > -1) {
			money = nofe.substring(nofe.indexOf("期望月薪:") + 5, nofe.length()).replaceAll(reg, "");
		} else if (nofe.indexOf("期望薪水:") > -1) {
			money = nofe.substring(nofe.indexOf("期望薪水:") + 5, nofe.length()).replaceAll(reg, "");
		} else if (nofe.indexOf("期望年薪:") > -1) {
			money = nofe.substring(nofe.indexOf("期望年薪:") + 5, nofe.length()).replaceAll(reg, "");
		} else if (nofe.indexOf("期望薪金:") > -1) {
			money = nofe.substring(nofe.indexOf("期望薪金:") + 5, nofe.length()).replaceAll(reg, "");
		} else if (nofe.indexOf("期望薪资:") > -1) {
			money = nofe.substring(nofe.indexOf("期望薪资:") + 5, nofe.length()).replaceAll(reg, "");
		}
		if (money.indexOf("元/月") > -1) {
			money = money.substring(0, money.indexOf("元/月") + 3);
		} else if (money.indexOf("人民币") > -1) {
			money = money.substring(0, money.indexOf("人民币") + 3);
		}
		// 缩短距离
		if (money.indexOf("@") > -1) {
			money = money.substring(0, money.indexOf("@"));
		}
		if (money.length() > 13) {
			money = money.substring(0, 12);
		}
		if (money.indexOf(" ") > -1) {
			money = money.substring(0, money.indexOf(" "));
		}
		Pattern pd = Pattern.compile("([0-9]{1,}(\\-|--|到|至)[0-9]{1,}|[0-9]{1,})");
		Matcher md = pd.matcher(money);
		if (md.find()) {
			money = md.group() + "元/月";
		} else {
			if (money.indexOf("面议") > -1) {
				money = "面议";
			} else {
				money = "保密";
			}
		}
		if (money.length() > 15) {
			money = money.substring(0, 15);
		}
		return money.replaceAll(reg, "");
	}

	// 当前所在公司
	public static String getcompany(String nofe) {
		String company = "";
		if (nofe.indexOf("职业概况") > -1) {
			nofe = nofe.substring(nofe.indexOf("职业概况"), nofe.length());
			if (nofe.indexOf("公司名称:") > -1) {
				company = nofe.substring(nofe.indexOf("公司名称:") + 5, nofe.length());
			}
		} else if (nofe.indexOf("最近工作") > -1) {
			nofe = nofe.substring(nofe.indexOf("最近工作") + 6, nofe.length());
			nofe = nofe.replaceAll("公" + reg + "司", "公司");
			if (nofe.indexOf("公司:") > -1) {
				company = nofe
						.substring(nofe.indexOf("公司:") + 3, nofe.length());
				if (!company.equals("")) {
					company = company.trim();
					company = company.split("(\\s{1,}|\\})")[0];
					if (company.indexOf(":") > -1 || company.indexOf("至今") > -1|| company.indexOf("--") > -1) {
						company = "";
					}
				}
			}
		}
		if (company.equals("")) {
			if (nofe.indexOf("@工作经历") > -1) {
				nofe = nofe.substring(nofe.indexOf("@工作经历") + 6, nofe.length());
				if (nofe.indexOf("@") > -1) {
					nofe = nofe.substring(0, nofe.indexOf("@"));
				}
				//防止工作经历中没有标题
				if (nofe.indexOf("公司名称:") > -1) {
					nofe = nofe.substring(nofe.indexOf("公司名称:") + 5, nofe.length());
				} else if (nofe.indexOf("公司:") > -1) {
					nofe = nofe.substring(nofe.indexOf("公司:") + 3, nofe.length());
				}
				// 没有分隔的时候
				company = nofe.replaceAll("\\|", " ");
				company = nofe.replaceAll("\\[", " [");
				company = nofe.replaceAll("\\(", " (");
				// 时间分割
				String meg = getRutunTimeReg();
				String msg[] = company.split(meg);
				if (msg.length >= 2) {
					company = msg[1];
				} else {
					company = "";
				}
				company = company.replaceAll("\\s{0,}" + meg + "(\\:|\\s{0,})","");
				String string = "";
				// 去掉时间后面的[2年3个月]
				if (company.indexOf("[") > -1 && company.indexOf("]") > -1) {
					string = company.substring(company.indexOf("["), company.indexOf("]") + 1);
				}
				company = company.replaceAll(string, "");
				company = company.replaceAll("\\s{0,}\\:", "");
				company = company.replaceAll("\\s{0,}[\\[\\]]\\s{0,}", "");
				// 时间和公司的位置
				company = company.trim();
				if (company.indexOf(" ") > -1) {
					String timeOrCompany[] = company.split("\\s{1,}");
					company = timeOrCompany[0];
				}
			}
		}
		if (company.indexOf("公司") > -1) {
			company = company.substring(0, company.indexOf("公司") + 2);
		} else if (company.indexOf("中心") > -1) {
			company = company.substring(0, company.indexOf("中心") + 2);
		} else if (company.lastIndexOf("(") > -1) {
			company = company.substring(0, company.indexOf("("));
		} else if (company.lastIndexOf("（") > -1) {
			company = company.substring(0, company.indexOf("（"));
		}
		company = company.replaceAll(":", "");
		company = company.replaceAll(reg, "");
		// 没有查询到，找全部公司
		if (company.equals("")) {
			Pattern p2 = Pattern.compile("[\u4E00-\u9FA5]{1,}(科技|\\s{0,})(有限(责任|\\s{0,})|\\s{0,})公司");
			Matcher m2 = p2.matcher(nofe);
			if (m2.find()) {
				company = m2.group();
			}
		}
		if (company.length() > 20) {
			company = company.substring(0, 20);
		}
		if (company.indexOf(",") > -1 || company.indexOf("、") > -1
		   || company.indexOf("，") > -1 || company.indexOf("。") > -1) {
			company = "不详";
		}
		company= company.replaceAll("(/[0-9]{1,}|\\.)", "");
		return company;
	}
	//当前职位
	public static String getposition(String nofe) {
		String posintion = "";
		if (nofe.indexOf("职业概况") > -1) {
			nofe = nofe.substring(nofe.indexOf("职业概况"), nofe.length());
			if (nofe.indexOf("所任职位") > -1) {
				nofe = nofe.substring(nofe.indexOf("所任职位") + 5, nofe.length());
				nofe = nofe.trim();
				posintion = nofe.split("(\\s{1,}|\\})")[0];
			}
		} else if (nofe.indexOf("最近工作") > -1) {
			nofe = nofe.substring(nofe.indexOf("最近工作") + 6, nofe.length());
			nofe = nofe.replaceAll("" + reg + "位", "职位");
			if (nofe.indexOf("职位:") > -1) {
				posintion = nofe.substring(nofe.indexOf("职位:") + 3, nofe.length());
				if (!posintion.equals("")) {
					posintion = posintion.trim();
					posintion = posintion.split("\\s{1,}")[0];
					if (posintion.equals("") ||posintion.indexOf("学历")>-1 || posintion.indexOf("--") > -1 || posintion.indexOf(":") > -1) {
						posintion = "";
					}
				}
			}
		}
		if (posintion.equals("")) {
			//首先在项目经历中找
			if (nofe.indexOf("@工作经历") > -1) {
				nofe = nofe.substring(nofe.indexOf("@工作经历") + 5, nofe.length());
				//缩短距离
				if (nofe.indexOf("@") > -1) {
					nofe = nofe.substring(0, nofe.indexOf("@"));
				}
				// 经历中有职位名称
				if (nofe.indexOf("职位类别:") > -1) {
					posintion = nofe.substring(nofe.indexOf("职位类别:") + 5, nofe.length());
					if (!posintion.equals(" ")) {
						posintion = posintion.trim();
						posintion = posintion.split("(\\s{1,}|\\|)")[0];
					}
				} else if (nofe.indexOf("职位:") > -1) {
					posintion = nofe.substring(nofe.indexOf("职位:") + 3, nofe.length());
					if (!posintion.equals("")) {
						posintion = posintion.trim();
						posintion = posintion.split("(\\s{1,}|\\|)")[0];
					}
				} else {
					if (nofe.indexOf("工作描述") > -1) {
						nofe = nofe.substring(0, nofe.indexOf("工作描述"));
					}
					posintion = PosionBank.getPosion(nofe);
					if (posintion.equals("")) {
						posintion = "不详";
					}
				}
			}
		}
		if (posintion.length() > 12) {
			posintion = posintion.substring(0, 12);// 截取防止超出长度
		}
		return posintion;
	}

	// 当前所在行业
	public static String getCurindustry(String nofe) {
		String curindustry = "";
		if (nofe.indexOf("职业概况") > -1) {
			nofe = nofe.substring(nofe.indexOf("职业概况"), nofe.length());
			if (nofe.indexOf("所在行业") > -1) {
				nofe = nofe.substring(nofe.indexOf("所在行业") + 5, nofe.length());
				nofe = nofe.trim();
				curindustry = nofe.split("(\\s{1,}|\\})")[0];
			}
		} else if (nofe.indexOf("最近工作") > -1) {
			nofe = nofe.substring(nofe.indexOf("最近工作") + 6, nofe.length());
			nofe = nofe.replaceAll("行" + reg + "业", "行业");
			if (nofe.indexOf("行业:") > -1) {
				curindustry = nofe.substring(nofe.indexOf("行业:") + 3, nofe.length());
				if (!curindustry.equals("")) {
					curindustry = curindustry.trim();
					curindustry = curindustry.split("(\\s{1,}|\\})")[0];
				}
			}
		} else {
			if (curindustry.equals("")) {
				// 工作经历中
				if (nofe.indexOf("@工作经历") > -1) {
					nofe = nofe.substring(nofe.indexOf("@工作经历") + 5, nofe.length());
					if (nofe.indexOf("@") > -1) {
						nofe = nofe.substring(0, nofe.indexOf("@"));
					}
					// 缩短距离
					if (nofe.indexOf("工作描述") > -1) {
						nofe = nofe.substring(0, nofe.indexOf("工作描述"));
					}
					// 查询工作经历的内容
					curindustry = Hangye.getHangye(nofe, true);
				}
			}
		}
		if (curindustry.length() > 12) {
			curindustry = curindustry.substring(0, 12);// 截取防止超出长度
		}
		return curindustry;
	}

	// 教育经历
	public static LinkedList<Map<String, String>> getJiaoYu(String nofe) {
		LinkedList<Map<String, String>> List = new LinkedList<Map<String, String>>();
		String jiaoyu = "";
		if (nofe.lastIndexOf("@教育经历") > -1) {
			nofe = nofe.substring(nofe.lastIndexOf("@教育经历") + 6, nofe.length());
			jiaoyu = nofe.substring(0, nofe.indexOf("@"));
		}
		// 提取时间格式的正则
		jiaoyu = jiaoyu.replaceAll("\\s{0,}/\\s{0,}", "/");
		String jiaoyuReg = getRutunTimeReg();
		Pattern p = Pattern.compile(jiaoyuReg);
		Matcher m = p.matcher(jiaoyu);
		int i = 0;
		while (m.find()) {
			// 多个时间段
			String str[] = jiaoyu.split(jiaoyuReg);
			LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
			String time = m.group();
			// 对时间进行同一整理
			time = getTime(time);
			if (time.indexOf("-") > -1) {
				String startYear = "";
				if (time.split("-")[0].indexOf("年") <= -1) {
					startYear = time.split("-")[0];
				}
				if (time.split("-")[0].split("年").length == 2) {
					startYear = time.split("-")[0].split("年")[0].replaceAll("年", "");
				}
				String startMonth = "1";
				if (time.split("-")[0].split("年").length == 2) {
					startMonth = time.split("-")[0].split("年")[1].replaceAll(
							"月", "");
				}
				String endYear = "";
				if (time.split("-")[1].indexOf("年") <= -1) {
					endYear = time.split("-")[1];
				}
				if (time.split("-")[1].split("年").length == 2) {
					endYear = time.split("-")[1].split("年")[0].replaceAll("年",
							"");
				}
				String endMonth = "1";
				if (time.split("-")[1].split("年").length == 2) {
					endMonth = time.split("-")[1].split("年")[1].replaceAll("月",
							"");
				}
				linkedHashMap.put("开始年", startYear.replaceAll(reg, ""));
				linkedHashMap.put("开始月", startMonth.replaceAll(reg, ""));
				linkedHashMap.put("结束年", endYear.replaceAll(reg, ""));
				linkedHashMap.put("结束月", endMonth.replaceAll(reg, ""));
			}
			// 专业名称
			String message = "";// i+1永远取时间后面的
			if (i + 1 < str.length) {
				message = str[i + 1];// 要加一才行
			} else {
				message = str[i];
			}
			i++;
			//提取大学名称
			String schoolName = "";
			Pattern pd = Pattern.compile("[\u4E00-\u9FA5]{1,}(大学|学院)");
			Matcher md = pd.matcher(message);
			if (md.find()) {
				schoolName = md.group();
			}
			linkedHashMap.put("学校名称", schoolName);
			message = message.replaceAll("\\|", " ");
			String zhuanye = "";
			String reg = "专业(类别|\\s{0,}):\\s{0,}[\u4e00-\u9fa5]{1,}";
			Matcher pg = Pattern.compile(reg).matcher(message);
			if (pg.find()) {
				zhuanye = pg.group();
				if (!zhuanye.equals("") && zhuanye.indexOf(":") > -1) {
					zhuanye = zhuanye.substring(zhuanye.indexOf(":") + 1,zhuanye.length());
				}
			} else {
				String zg = "[\u4e00-\u9fa5]{1,}专业";
				Matcher png = Pattern.compile(zg).matcher(message);
				if (png.find()) {
					zhuanye = png.group();
				}
			}
			// 还没有找到
			if (zhuanye.equals("")) {
				// 默认在大学后面找
				if (!schoolName.equals("")) {
					String sp[] = message.split(schoolName);
					if (sp.length >= 2) {
						message = sp[1].trim();
						zhuanye = message.split("\\s{1,}")[0];
					}
				}
			}
			zhuanye = zhuanye.replaceAll(reg, "");
			// 防止溢出
			if (zhuanye.length() > 11) {
				zhuanye = zhuanye.substring(0, 10);
			}
			linkedHashMap.put("专业名称", zhuanye);
			// 找学位
			String xuewei = getHighDegree(message);
			linkedHashMap.put("学历/学位", xuewei);

			List.add(linkedHashMap);
			linkedHashMap = null;
		}

		return List;
	}

	// 语言能力
	public static LinkedList<Map<String, String>> getYuyan(String nofe)
			throws Exception {
		LinkedList<Map<String, String>> List = new LinkedList<Map<String, String>>();

		if (nofe.indexOf("@语言能力") > -1) {
			nofe = nofe.substring(nofe.indexOf("@语言能力") + 5, nofe.length());
		}
		// 现在是默认一个
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
		String yuzhong = "";
		if (nofe.indexOf("英语") > -1) {
			yuzhong = "英语";
		}
		if (nofe.indexOf("日语") > -1) {
			yuzhong = "  日语";
		}
		if (nofe.indexOf("法语") > -1) {
			yuzhong = "  法语";
		}
		if (nofe.indexOf("普通话") > -1) {
			yuzhong = " 普通话";
		}
		String ting = "";
		if (nofe.indexOf("听说（") > -1) {
			ting = nofe.substring(nofe.indexOf("听说（"), nofe.length());
			ting = nofe.substring(3, nofe.indexOf("）"));
		}
		String du = "";
		if (nofe.indexOf("读写（") > -1) {
			du = nofe.substring(nofe.indexOf("读写（"), nofe.length());
			du = nofe.substring(3, nofe.indexOf("）"));
		}
		if (nofe.indexOf("普通") > -1) {
			du = "普通";
			ting = "普通";
		} else if (nofe.indexOf("一般") > -1) {
			du = "一般";
			ting = "一般";
		} else if (nofe.indexOf("良好") > -1) {
			du = "良好";
			ting = "良好";
		} else if (nofe.indexOf("熟练") > -1) {
			du = "熟练";
			ting = "熟练";
		}
		linkedHashMap.put("外语语种", yuzhong);
		linkedHashMap.put("读写能力", du);
		linkedHashMap.put("听说能力", ting);
		List.addLast(linkedHashMap);
		linkedHashMap = null;
		return List;
	}

	//工作经历
	public static LinkedList<Map<String, String>> workExpPro(String nofe)
		throws Exception {
		LinkedList<Map<String, String>> List = new LinkedList<Map<String, String>>();
		String workProc = "";
		if (nofe.lastIndexOf("@工作经历") > -1) {
			nofe = nofe.substring(nofe.lastIndexOf("@工作经历") + 6, nofe.length());
			workProc = nofe.substring(0, nofe.indexOf("@"));
			workProc = workProc.replaceAll("\\|", "  ");
			//提取时间格式的正则
			String jiaoyuReg = getRutunTimeReg();
			//---------如果项目中出现时间段，则去掉时间段 类似这样的---------
			Pattern p2 = Pattern.compile("[\u4E00-\u9FA5]" + jiaoyuReg);
			Matcher m2 = p2.matcher(workProc);
			while (m2.find()) {
				String str = m2.group();
				workProc = workProc.replaceAll(str, "");
			}
			Pattern p3 = Pattern.compile(jiaoyuReg + ",");
			Matcher m3 = p3.matcher(workProc);
			while (m3.find()) {
				String str = m3.group();
				workProc = workProc.replaceAll(str, "");
			}
			//开始
			Pattern p = Pattern.compile(jiaoyuReg);
			Matcher m = p.matcher(workProc);
			int i = 0;
			while (m.find()) {
				LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
				// 每一块的内容
				String str[] = workProc.split(jiaoyuReg);
				// 对时间进行同一整理
				String time = m.group();
				time = getTime(time);
				if (time.indexOf("-") > -1) {
					String startYear = "";
					if (time.split("-")[0].split("年").length == 2) {
						startYear = time.split("-")[0].split("年")[0].replaceAll("年", "");
					}
					String startMonth = "";
					if (time.split("-")[0].split("年").length == 2) {
						startMonth = time.split("-")[0].split("年")[1].replaceAll("月", "");
					}
					String endYear = "";
					if (time.split("-")[1].split("年").length == 2) {
						endYear = time.split("-")[1].split("年")[0].replaceAll("年", "");
					}
					String endMonth = "";
					if (time.split("-")[1].split("年").length == 2) {
						endMonth = time.split("-")[1].split("年")[1].replaceAll("月", "");
					}
					//0.任职时间
					linkedHashMap.put("开始年", startYear.replaceAll(reg, ""));
					linkedHashMap.put("开始月", startMonth.replaceAll(reg, ""));
					linkedHashMap.put("结束年", endYear.replaceAll(reg, ""));
					linkedHashMap.put("结束月", endMonth.replaceAll(reg, ""));
				}
				String workInfo = "";
				if (i + 1 < str.length) {
					workInfo = str[i + 1];//要加一才行
				} else {
					workInfo = str[i];
				}
				workInfo = workInfo.trim();//去掉首尾空格
				// 1.公司名称
				String complay = "";
				String string = "";
				// 去掉时间后面的[2年3个月]
				if (workInfo.indexOf("[") > -1 && workInfo.indexOf("]") > -1) {
					string = workInfo.substring(workInfo.indexOf("["), workInfo.indexOf("]") + 1);
				}
				string = string.replaceAll("\\-", "至");
				complay = workInfo.replaceAll(string, "");
				complay = complay.replaceAll("\\s{0,}[\\[\\]]\\s{0,}", "");
				// 去时间段落
				complay = complay.trim();
				complay = complay.split("\\s{1,}")[0];
				if (complay.length() > 18) {
					complay = complay.substring(0, 18);
				}
				if (complay.indexOf("(") > -1) {
					complay = complay.substring(0, complay.indexOf("("));
				}else if (complay.indexOf("（") > -1) {
					complay = complay.substring(0, complay.indexOf("（"));
				}
				if (complay.indexOf("公司") > -1) {
					complay = complay.substring(0, complay.indexOf("公司") + 2);
				}
				complay = complay.replaceAll(":", "");
				
				linkedHashMap.put("公司名称", complay.trim());
				// 2.所在部门
				String bumen = "";
				if (workInfo.indexOf("所在部门:") > -1) {
					bumen = workInfo.substring(workInfo.indexOf("所在部门:") + 5,workInfo.length());
					bumen = bumen.split("\\s{1,}")[0];
				} else {
					Pattern pbm = Pattern.compile("[\u4E00-\u9FA5]{1,}(部门|中心|部|办公室)");
					Matcher mbm = pbm.matcher(workInfo);
					if (mbm.find()) {
						bumen = mbm.group().replaceAll(":", "");
					} else {
						bumen = "";
					}
				}
				bumen = bumen.replaceAll(reg, "");
				bumen = bumen.equals("") ? "" : bumen;
				if (bumen.length() > 12) {
					//截取防止超出长度
					bumen = bumen.substring(0, 12);
				}
				linkedHashMap.put("所在的部门", bumen.trim());
				//3.1去掉部门
				workInfo = workInfo.replaceAll(bumen, "");
				//3.2搜索职位信息
				String buzw = PosionBank.getPosion(workInfo);
				if (buzw.length() > 15) {
					//截取防止超出长度
					buzw = buzw.substring(0, 15);
				}
				linkedHashMap.put("职位名称", buzw);
				//4.行业类别
				String hangye = "";
				if (workInfo.indexOf("行业:") > -1) {
					hangye =workInfo.substring(workInfo.indexOf("行业:") + 3,workInfo.length());
				} else if (workInfo.indexOf("类别:") > -1) {
					hangye = workInfo.substring(workInfo.indexOf("类别:") + 3,workInfo.length());
				}
				hangye = hangye.trim();
				hangye = hangye.split("(\\s{1}|\\:)")[0];
				hangye = hangye.replaceAll(reg, "");
				//查找行业库
				if (hangye.equals("")) {
					hangye = Hangye.getHangye(workInfo, true);
				}
				linkedHashMap.put("行业类别", hangye);
				// 5.企业性质
				String stxz = "";
				if (workInfo.indexOf("性质:") > -1) {
					stxz = workInfo.substring(workInfo.indexOf("性质:") + 3,workInfo.length());
					stxz = stxz.trim();
					stxz = stxz.split("\\s{1,}")[0];
				}
				//性质库
				if (stxz.equals("")) {
					stxz = EnterpriseProperty.getEnterprise(nofe);
				}
				linkedHashMap.put("企业性质", stxz);
				
				// 6.公司规模
				String peple = "";
				if (workInfo.indexOf("规模:") > -1) {
					peple = workInfo.substring(workInfo.indexOf("规模:") + 3,workInfo.length());
					peple = peple.trim();
					peple = peple.split("\\s{1,}")[0];
				}
				if (peple.equals("")) {
					Pattern ppe = Pattern.compile("\\s{0,}([0-9]{1,5}\\s{0,}(到|\\-)[0-9]{1,5}\\s{0,}|[0-9]{1,}\\s{0,})人");
					Matcher mpe = ppe.matcher(workInfo);
					if (mpe.find()) {
						peple = mpe.group().replaceAll(":", "");
					}
					peple = peple.replaceAll(reg, "");
					peple = peple.equals("") ? "" : peple;
				}
				linkedHashMap.put("企业规模", peple);
				// 7.公司描述
				String comnofe = "";
				if(workInfo.indexOf("公司描述:") > -1) {
					comnofe = workInfo.substring(workInfo.indexOf("公司描述:") + 5,workInfo.length());
				}else if(workInfo.indexOf("公司介绍:") > -1) {
					comnofe = workInfo.substring(workInfo.indexOf("公司介绍:") + 5,workInfo.length());
				}
				 //头部截取
				 if (comnofe.indexOf(":") > -1) {
					 comnofe = comnofe.substring(0,comnofe.indexOf(":"));
				 }
				 //尾部截取
				 if(comnofe.lastIndexOf("。") > -1) {
					 comnofe = comnofe.substring(0,comnofe.lastIndexOf("。")+1);
				 }
				comnofe = comnofe.replaceAll(":", "");
				comnofe = comnofe.replaceAll(reg, "");
				linkedHashMap.put("公司描述", comnofe);
				// 8.回报对象
				String huibao = "";
				if (workInfo.indexOf("汇报对象:") > -1) {
					huibao = workInfo.substring(workInfo.indexOf("汇报对象:") + 5,workInfo.length());
					huibao = huibao.trim();
					huibao = huibao.split("\\s{1,}")[0];
				}
				huibao = huibao.replaceAll(reg, "");
				linkedHashMap.put("汇报对象", huibao.trim());
				// 9.下属人数
				String xiashu = "";
				if (workInfo.indexOf("下属人数:") > -1) {
					xiashu = workInfo.substring(workInfo.indexOf("下属人数:") + 5,workInfo.length());
					xiashu = xiashu.trim();
					xiashu = xiashu.split("\\s{1,}")[0];
				}
				xiashu = xiashu.replaceAll(reg, "");
				linkedHashMap.put("下属人数", xiashu);
				// 10.薪资状况
				String money = "";
				Pattern pmon = Pattern.compile("([0-9]{1,}\\s{0,}(\\-|--|到|至)[0-9]{1,}\\s{0,}|[0-9]{1,})(/月|元|RMB)");
				Matcher mmon = pmon.matcher(workInfo);
				if (mmon.find()) {
					money = mmon.group().replaceAll(":", "");
				}
				money = money.replaceAll(reg, "");
				money = money.equals("") ? "":money + "/月";
				linkedHashMap.put("职位月薪", money);
				// 11.工作描述
				String miaoshu ="";
				if (workInfo.indexOf("工作描述") > -1) {
					miaoshu = workInfo.substring(workInfo.indexOf("工作描述") + 5,workInfo.length());
				} else if (workInfo.indexOf("职责和业绩") > -1) {
					miaoshu = workInfo.substring(workInfo.indexOf("职责和业绩") + 6,workInfo.length());
				} else if (workInfo.indexOf("工作职责") > -1) {
					miaoshu = workInfo.substring(workInfo.indexOf("工作职责") + 5,workInfo.length());
				} else if (workInfo.indexOf("职责描述") > -1) {
					miaoshu = workInfo.substring(workInfo.indexOf("职责描述") + 5,workInfo.length());
				}
				if(!miaoshu.equals("")){
					  //头部截取
					 if (miaoshu.indexOf(":") > -1) {
						 miaoshu = miaoshu.substring(0, miaoshu.indexOf(":"));
					 }
					 //尾部截取
					 if (miaoshu.indexOf("业绩描述") > -1) {
						 miaoshu = miaoshu.substring(0,miaoshu.indexOf("业绩描述"));
					  }
					 if (miaoshu.indexOf("职责和业绩") > -1) {
						 miaoshu = miaoshu.substring(0,miaoshu.indexOf("职责和业绩"));
					  }
					 if (miaoshu.indexOf("业绩或表现") > -1) {
						 miaoshu = miaoshu.substring(0,miaoshu.indexOf("业绩或表现"));
					  }
					 if (miaoshu.lastIndexOf("。") > -1) {
						 miaoshu = miaoshu.substring(0,miaoshu.lastIndexOf("。")+1);
					 }
				}
				//排版
				miaoshu=miaoshu.replaceAll("(:|：)","");
				miaoshu = miaoshu.replaceAll(reg, "").equals("")?"":miaoshu;
				if(!miaoshu.equals(""))
				miaoshu = miaoshu.trim().replaceAll("\\s{2,}", "<br/>");
				linkedHashMap.put("工作描述", miaoshu);
				// 12.业绩描述
				String yeji = "";
				if (workInfo.indexOf("工作业绩") > -1) {
					yeji = workInfo.substring(workInfo.indexOf("工作业绩") + 5,workInfo.length());
				} else if (workInfo.indexOf("业绩描述") > -1) {
					yeji = workInfo.substring(workInfo.indexOf("业绩描述") + 5,workInfo.length());
				} else if (workInfo.indexOf("业绩或表现") > -1) {
					yeji = workInfo.substring(workInfo.indexOf("业绩或表现") + 6,workInfo.length());
				} else if (workInfo.indexOf("职责和业绩") > -1) {
					yeji = workInfo.substring(workInfo.indexOf("职责和业绩") + 6,workInfo.length());
				}
				//没有类似标题
				yeji=yeji.equals("")?workInfo:yeji;
				//头部截取
				if(yeji.lastIndexOf(":")>-1){
					yeji=yeji.substring(yeji.lastIndexOf(":"),yeji.length());
				}
				if(yeji.indexOf("负责")>-1){
					yeji=yeji.substring(yeji.indexOf("负责"),yeji.length());
				}if(yeji.indexOf("元/月")>-1){
					yeji=yeji.substring(yeji.indexOf("元/月"),yeji.length());
				}
				//尾部截取
				if(yeji.lastIndexOf("。")>-1){
					yeji=yeji.substring(0,yeji.lastIndexOf("。")+1);
				}
				//排版
				yeji = yeji.replaceAll(reg, "").equals("")?"":yeji;
				if(!yeji.equals(""))
				yeji = yeji.trim().replaceAll("\\s{2,}", "<br/>");
				yeji.replaceAll(reg, "");
				linkedHashMap.put("业绩描述", yeji);
				List.addLast(linkedHashMap);
				i++;
			}
		}
		return List;
	}
	//专业技能
	public static LinkedList<Map<String, String>> proSkills(String nofe)
			throws Exception {
		String jiNeng = "";
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
		LinkedList<Map<String, String>> List = new LinkedList<Map<String, String>>();
		if (nofe.indexOf("专业技能") > -1) {
			nofe = nofe.substring(nofe.indexOf("专业技能") + 6, nofe.length());
			jiNeng = nofe.substring(0, nofe.indexOf("@") > -1 ? nofe.indexOf("@") : nofe.length());
		}
		linkedHashMap.put("技能", jiNeng.replaceAll(reg, ""));
		linkedHashMap.put("熟练程度", "熟练");
		linkedHashMap.put("使用时间", "3个月");
		List.addLast(linkedHashMap);
		return List;
	}

	// 项目经验
	public static LinkedList<Map<String, String>> prodc(String nofe)
			throws Exception {
		LinkedList<Map<String, String>> List = new LinkedList<Map<String, String>>();
		if (nofe.indexOf("@项目经历") > -1) {
			nofe = nofe.substring(nofe.indexOf("@项目经历") + 5, nofe.length());
			nofe = nofe.substring(0, nofe.indexOf("@"));
			// 按时间段进行分割
			String procReg = getRutunTimeReg();
			Pattern pt = Pattern.compile(procReg);
			Matcher mt = pt.matcher(nofe);
			int i = 0;
			while (mt.find()) {
				LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
				String str[] = nofe.split(procReg);
				// 对时间进行同一整理
				String time = mt.group();
				time = getTime(time);
				if (time.indexOf("-") > -1) {
					String startYear = "";
					if (time.split("-")[0].split("年").length == 2) {
						startYear = time.split("-")[0].split("年")[0]
								.replaceAll("年", "");
					}
					String startMonth = "";
					if (time.split("-")[0].split("年").length == 2) {
						startMonth = time.split("-")[0].split("年")[1]
								.replaceAll("月", "");
					}
					String endYear = "";
					if (time.split("-")[1].split("年").length == 2) {
						endYear = time.split("-")[1].split("年")[0].replaceAll(
								"年", "");
					}
					String endMonth = "";
					if (time.split("-")[1].split("年").length == 2) {
						endMonth = time.split("-")[1].split("年")[1].replaceAll(
								"月", "");
					}
					linkedHashMap.put("开始年", startYear.replaceAll(reg, ""));
					linkedHashMap.put("开始月", startMonth.replaceAll(reg, ""));
					linkedHashMap.put("结束年", endYear.replaceAll(reg, ""));
					linkedHashMap.put("结束月", endMonth.replaceAll(reg, ""));
				}
				String meg = "";
				if (i + 1 < str.length) {
					meg = str[i + 1];
				} else {
					meg = str[i];
				}
				i++;
				meg = meg.trim();
				if (!meg.replaceAll(reg, "").equals("")) {
					String projecName = meg.split("\\s{1,}")[0];
					// 特殊情况，不存在的时候
					if (projecName.indexOf("描述:") > -1|| projecName.indexOf("。") > -1) {
						projecName = "";
					}
					projecName = projecName.replaceAll(":", "");
					if (projecName.length() > 30) {
						//截取防止超出长度
						projecName = projecName.substring(0, 28);
					}
					linkedHashMap.put("项目名称", projecName.trim());
					
					String companyXz = "";
					if (meg.indexOf("项目描述:") > -1) {
						companyXz = meg.substring(meg.indexOf("项目描述:") + 5, meg.length());
					} else if (meg.indexOf("项目概述:") > -1) {
						companyXz = meg.substring(meg.indexOf("项目概述:") + 5, meg.length());
					}
					
					//防止后面有项目职责
					companyXz = companyXz.substring(0, companyXz.indexOf("职责描述:") > -1?companyXz.indexOf("职责描述:"):companyXz.length());
					companyXz = companyXz.substring(0, companyXz.indexOf("责任描述:") > -1?companyXz.indexOf("责任描述:"):companyXz.length());
					companyXz = companyXz.substring(0, companyXz.indexOf("项目职责:") > -1?companyXz.indexOf("项目职责:"):companyXz.length());
					//对描述进行统一排版
					String companyXzPai = "";
					if (companyXz.indexOf("1")>-1 && companyXz.indexOf("2")>-1&&companyXz.indexOf("。") > -1) {
						for (int j = 0; j < companyXz.split("。").length; j++) {
							companyXzPai += "<br/>" + companyXz.split("。")[j];
						}
					}
					companyXzPai = companyXzPai.equals("") ? companyXz: companyXzPai;
					companyXzPai = companyXzPai.replaceAll(reg, "");
					linkedHashMap.put("项目描述", companyXzPai);
					//项目职责	
					String zhize ="";
					if (meg.indexOf("项目职责:") > -1) {
						zhize = meg.substring(meg.indexOf("项目职责:")+5, meg.length());
					} else if (meg.indexOf("责任描述:") > -1) {
						zhize = meg.substring(meg.indexOf("责任描述:")+5, meg.length());
					}
					
					zhize = zhize.substring(0, zhize.lastIndexOf("。") > -1?zhize.lastIndexOf("。")+1:zhize.length());
					zhize = zhize.replaceAll(reg, "");
					linkedHashMap.put("项目职责", zhize);
					List.add(linkedHashMap);
					linkedHashMap = null;
				}
			}
		}
		return List;
	}

	// 证书
	public static LinkedList<Map<String, String>> letter(String nofe)
			throws Exception {
		LinkedList<Map<String, String>> List = new LinkedList<Map<String, String>>();
		nofe = nofe.replaceAll("证" + reg + "书", "证书");
		if (nofe.indexOf("@证书") > -1) {
			nofe = nofe.substring(nofe.indexOf("@证书") + 3, nofe.length());
			String zhengs = nofe.substring(0, nofe.indexOf("@"));

			if (!zhengs.equals("")) {
				if (zhengs.indexOf("证书名称:") > -1) {
					LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
					zhengs = zhengs.substring(zhengs.indexOf("证书名称:") + 5,zhengs.length());
					zhengs = zhengs.split("\\s{1,}")[0];
					linkedHashMap.put("证书名称", zhengs);
					linkedHashMap.put("获取年", "");
					linkedHashMap.put("获取月", "");
					linkedHashMap.put("证书说明", "");
					List.add(linkedHashMap);
					linkedHashMap = null;
				} else {
					// 按时间段进行分割
					String streg = "[0-9]{4}\\s{0,}[年|\\-|/|.]\\s{0,}[0-9]{1,3}\\s{0,}";
					Pattern pt = Pattern.compile(streg);
					Matcher mt = pt.matcher(zhengs);
					int i = 0;
					while (mt.find()) {
						LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
						// 对时间进行同一整理
						String time = mt.group();
						time = getTime(time);
						String startYear = "";
						String starMonth = "";
						String zname = "";
						String znofe = "";
						if (time.indexOf("年") > -1) {
							if (time.split("年").length == 2) {
								startYear = time.split("年")[0];
								starMonth = time.split("年")[1];
							}
						}
						String str[] = zhengs.split(streg);
						String meg = "";
						if (i + 1 < str.length) {
							meg = str[i + 1];// 要加一才行
						} else {
							meg = str[i];
						}
						i++;
						nofe = nofe.replaceAll(streg, "");
						nofe = nofe.replaceAll(":", "");
						// 获取证书名称
						linkedHashMap.put("获取年", startYear);
						linkedHashMap.put("获取月", starMonth);
						if (meg.indexOf(" ") > -1) {
							zname = meg.split("\\s{1,}")[0];
							if (meg.split("\\s{1,}").length == 2) {
								znofe = meg.split("\\s{1,}")[1];
							}
						}
						zname = zname.replaceAll(reg, "");
						if (zname.length() > 30) {
							zname = zname.substring(0, 28);// 截取防止超出长度
						}
						if (znofe.length() > 30) {
							znofe = znofe.substring(0, 28);// 截取防止超出长度
						}
						zname = zname.replaceAll(":", "");

						linkedHashMap.put("证书名称", zname);
						linkedHashMap.put("证书说明", znofe);
						// 存工作经历
						List.add(linkedHashMap);
						linkedHashMap = null;
					}
				}
			}
		}
		return List;
	}
}
