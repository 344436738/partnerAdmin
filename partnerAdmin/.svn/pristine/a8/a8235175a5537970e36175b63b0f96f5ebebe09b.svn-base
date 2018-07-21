package com.base.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.base.bean.SiteMain;
import com.base.bean.SysMenu;
import com.base.bean.SysMenuBtn;
import com.base.service.SiteMainService;
import com.base.service.SysMenuBtnService;
import com.base.service.SysMenuService;

/**
 * <br>
 * <b>功能：</b>详细的功能描述<br>
 * <b>作者：</b><br>
 * <b>日期：</b> 2013-03-01 <br>
 * <b>更新者：</b><br>
 * <b>日期：</b> <br>
 * <b>更新内容：</b><br>
 */
public class CreateJava {
	private static ResourceBundle res = ResourceBundle
			.getBundle("DataSourceConfig");
	private static String url = res.getString("gpt.url");
	private static String username = res.getString("gpt.username");
	private static String passWord = res.getString("gpt.password");
	
	// 项目根路径
	private static String rootPath = getRootPath();// "F:\\openwork\\open\\";

	public static void main(String[] args) {
		CreateBean createBean = new CreateBean();
		createBean.setMysqlInfo(url, username, passWord);
		
		/** 此处修改成你的 表名 和 中文注释 ***/
		String tableName = "st_resume"; //表名称  ----------这里需要改
		String codeName = "简历表";// 中文注释 当然你用英文也是可以的 ----------这里需要改
		String tableSchema="partner";//一般为指定库，为空字符串也可以，如果需要生成其他库的表，则写其他库的schema
		 
		String className = createBean.getTablesNameToClassName(tableName);
		String lowerName = className.substring(0, 1).toLowerCase()
				+ className.substring(1, className.length());
		List<String> queryParams=new ArrayList();
//		queryParams.add("title:标题");  //页面查询参数  ----------这里需要改
//		queryParams.add("content:内容");//页面查询参数 ----------这里需要改
//		queryParams.add("creatTime:你好");
		// 根路径
		String srcPath = rootPath + "src\\";
		// 包路径
		String pckPath = rootPath + "src\\com\\business\\";	
		// 页面路径，放到WEB-INF下面是为了不让手动输入路径访问jsp页面，起到安全作用
		String webPath = rootPath + "WebRoot\\";

		// java,xml文件名称
		String modelPath = "model\\" + className + "Model.java";
		String beanPath = "bean\\" + className + ".java";
		String mapperPath = "mapper\\" + className + "Mapper.java";
		String servicePath = "service\\" + className + "Service.java";
		String controllerPath = "action\\" + className + "Action.java";
		String sqlMapperPath = "mybatis\\" + className + "Mapper.xml";
		// jsp页面路径
		// String pageEditPath = lowerName+"\\"+lowerName+"Edit.jsp";
	    String pageListPath ="view\\" + lowerName+"\\"+lowerName+"List.jsp";
	    
	    String pageJsPath ="js\\" + lowerName+"\\"+lowerName+".js";

		VelocityContext context = new VelocityContext();
		context.put("className", className); //
		context.put("lowerName", lowerName);
		context.put("codeName", codeName);
		context.put("tableName", tableName);

		/****************************** 生成bean字段 *********************************/
		try {
			context.put("feilds", createBean.getBeanFeilds(tableName,tableSchema)); // 生成bean
		} catch (Exception e) {
			e.printStackTrace();
		}

		/******************************* 生成sql语句 **********************************/
		try {
			Map<String, Object> sqlMap = createBean.getAutoCreateSql(tableName,tableSchema);
			context.put("columnDatas", createBean.getColumnDatas(tableName,tableSchema)); // 生成bean
			context.put("SQL", sqlMap);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		//		
		// -------------------生成文件代码---------------------/
		CommonPageParser
				.WriterPage(context, "TempBean.java", pckPath, beanPath); // 生成实体Bean
		CommonPageParser.WriterPage(context, "TempModel.java", pckPath,
				modelPath); // 生成Model
		CommonPageParser.WriterPage(context, "TempMapper.java", pckPath,
				mapperPath); // 生成MybatisMapper接口 相当于Dao
		CommonPageParser.WriterPage(context, "TempService.java", pckPath,
				servicePath);// 生成Service
		CommonPageParser.WriterPage(context, "TempMapper.xml", pckPath,
				sqlMapperPath);// 生成Mybatis xml配置文件
//		CommonPageParser.WriterPage(context, "TempController.java", pckPath,
//				controllerPath);// 生成Controller 相当于接口
		// 生JSP页面，如果不需要可以注释掉 
		 context.put("basePath", "${msUrl}");
		 
		 context.put("queryParams", createBean.getQueryParams(queryParams));
		 
		 
//		 CommonPageParser.WriterPage(context, "TempList.jsp",webPath,
//		 pageListPath);//生成Controller 相当于接口
//		 
//		 CommonPageParser.WriterPage(context, "TempJs.js",webPath,
//				 pageJsPath);//生成Controller 相当于接口
		 
		 
		 
		// CommonPageParser.WriterPage(context, "TempEdit.jsp",webPath,
		// pageEditPath);//生成Controller 相当于接口

//		try {
//			//最后增加到Menu表当中
//			List<SysMenuBtn> btnList=new ArrayList();
//			SysMenuBtn smb=new SysMenuBtn();
//			smb.setBtnName("添加");
//			smb.setBtnType("add");
//			smb.setActionUrls("save.do");
//			SysMenuBtn smb2=new SysMenuBtn();
//			smb2.setBtnName("修改");
//			smb2.setBtnType("edit");
//			smb2.setActionUrls("getId.do|save.do");
//			SysMenuBtn smb3=new SysMenuBtn();
//			smb3.setBtnName("删除");
//			smb3.setBtnType("remove");
//			smb3.setActionUrls("delete.do");
//			btnList.add(smb);
//			btnList.add(smb2);
//			btnList.add(smb3);
//			SysMenu sm=new SysMenu();
//			sm.setName(codeName);
//			sm.setBtns(btnList);
//			sm.setUrl("/"+lowerName+"/list.shtml");
//			sm.setRank(99);
//			sm.setActions("dataList.do");
//			sm.setParentId(1);//暂时放在系统管理里边
//			sm.setDeleted(0);
//			ApplicationContext appContent = new ClassPathXmlApplicationContext(
//					new String[] { "spring-*.xml" });
//
//			SysMenuService<SysMenu> sysMenuService = (SysMenuService) appContent
//					.getBean("sysMenuService");
//			sysMenuService.add(sm);
//			System.out.println("成功添加到菜单表中，ID是"+sm.getId());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		
		/************************* 修改xml文件 *****************************/
		WolfXmlUtil xml = new WolfXmlUtil();
		Map<String, String> attrMap = new HashMap<String, String>();
		try {
			/** 引入到mybatis-config.xml 配置文件 */
			attrMap.clear();
			attrMap.put("resource", "com/business/mybatis/" + className
					+ "Mapper.xml");
			xml.getAddNode(rootPath + "conf/mybatis-config.xml",
					"/configuration/mappers", "mapper", attrMap, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 获取项目的路径
	 * 
	 * @return
	 */
	public static String getRootPath() {
		String rootPath = "";
		try {
			File file = new File(CommonPageParser.class.getResource("/")
					.getFile());
			rootPath = file.getParentFile().getParentFile().getParent() + "\\";
			rootPath = java.net.URLDecoder.decode(rootPath, "utf-8");
			System.out.println(rootPath);
			return rootPath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rootPath;
	}
	 
	
}
