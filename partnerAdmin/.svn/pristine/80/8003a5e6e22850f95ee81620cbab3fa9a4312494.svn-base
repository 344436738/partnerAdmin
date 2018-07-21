package com.business.util.resume;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hangye {
	// IT互联网
	public static String it = "网络游戏,电子/微电子,计算机软件,互联网/电子商务,IT服务/系统集成,电信,通信/设备/运营/增值,计算机硬件/网络设备";
	// 房地产
	public static String fangdiChan = "房地产服务,物业管理/商业中心,房地产开发/建筑与工程,家居/室内设计/装潢";
	// 金融
	public static String jinRong = "会计/审计/财务,银行,基金/证券/期货/投资,保险";
	// 快消
	public static String kuaiXiao = "快速消费品,化妆品/保健品,食品/饮料/酒水";
	// 零售
	public static String lingShou = "批发/零售,国际贸易,零售";
	// 制造
	public static String zhiZao = "耐用消费品/服饰/纺织/家具,机械制造/机电/重工,奢侈品/收藏品/工艺品/玩具";
	// 广告传媒
	public static String chanmei = "广告/会展/公关,影视/艺术/文化传播,媒体/出版/文化传播,印刷/包装/造纸,原材料及加工,能源/电力/石油/水利,化工,采掘/冶炼,新能源";
	// 医药
	public static String yiyao = "制药/生物工程,医疗/保健/美容/卫生服务,医疗设备/器械";
	// 其它
	public static String qita = "贸易/进出口,教育/培训/科研/院校,环保,家电业,政府/非营利机构";
	// 服务业
	public static String fuwuye = "中介服务,生活服务,娱乐/运动/休闲,交通/运输/物流,旅游/酒店/餐饮服务,专业咨询/财会/法律,外包服务,检测/认证/学术/科研,咨询/管理产业/法律/财会";

	public static void main(String[] args) {
		String test="	2010/08 -- 至今:HAXinternational | 猎头顾问/助理	咨询/管理产业/法律/财会 | 合资 | 规模:20人以下 | 4001-6000元/月	";
		System.out.println(getHangye(test,false));
		
	}
	
	public static String getHangye(String nofe,Boolean isture) {
		String hangye = "";
		String hangyeList = it + "," + fangdiChan + "," + jinRong + ","
				+ kuaiXiao + "," + lingShou + "," + zhiZao + "," + chanmei
				+ "," + yiyao + "," + qita + "," + fuwuye;
		hangyeList = hangyeList.replaceAll("/", ",");
		hangyeList = hangyeList.replaceAll(",", "|");
		Pattern ps = Pattern.compile("(" + hangyeList + ")");
		Matcher ms = ps.matcher(nofe);
		if (ms.find()) {
			String posin = ms.group();
			String s[] = nofe.split("\\s{1,}");
			for (int i = 0; i < s.length; i++) {
				if (s[i].indexOf(posin) > -1) {
					if(isture){
						hangye=posin;
					}else{
						//特殊情况,中间没有空格
						if(s[i].indexOf("。")>-1 || s[i].length()>30 || s[i].indexOf(":")>-1 || s[i].indexOf("：")>-1){
							hangye=posin;
						}else{
							hangye = s[i];
						}
					}
					break;
				}
			}
		}
		return hangye;
	}
}
