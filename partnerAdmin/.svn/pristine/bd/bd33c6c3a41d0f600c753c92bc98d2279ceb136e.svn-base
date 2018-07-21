package com.base.action;

import java.io.File;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/siteNewsModel")
public class SiteNewsModelAction extends BaseAction{
	
	private static String ENCODE = "GBK";
	/**
	 * 查询所有模版文件及文件夹
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/showModel")
	public ModelAndView list(HttpServletRequest request) throws Exception{
		Map<String, Object> context = getRootMap();		
		String path=Thread.currentThread().getContextClassLoader().getResource("").toString();  
		path=path.replace('/', '\\'); // 将/换成\  
		path=path.replace("file:", ""); //去掉file:  
		path=path.replace("classes\\", ""); //去掉class\  
		path=path.substring(1); //去掉第一个\,如 \D:\JavaWeb...  
		path+="cmsTemplate";
		File file = new File(path);
		fileList(file,0);
		return forword("siteNews/model", context);	
    }
	
	/**
	 * 
	 * @param file
	 * @param node 
	 */
	public void fileList(File file,int node){
		node++;
		File[] files = file.listFiles();
		if(files!=null){
			for (File f : files) {
				for(int i=0;i<node;i++){
					if(i==node-1){
						System.out.print("-");
					}else{
						System.out.print(" ");
					}
				}
				System.out.println(f.getName());
				fileList(f,node);
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/readFile")
	public ModelAndView hello(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();  
        mv.setViewName("h");
		return mv;
	}	
}
