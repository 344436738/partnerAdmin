package com.base.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;



public class FileUploadUtil {
	/**
	 * 上传文件
	 * @param request 
	 * @param file 文件，定义的MultipartFile
	 * @param filePath 存放路径，相对于项目的存放路径。 如"/video/" 就是存放在/houseweb/WebRoot/upload/video 目录下
	 * @param exsitPath  旧文件名，如果有请填写，将把旧文件删除，没有弄空即可。
	 * @return path 返回一个路径
	 * 
	 * 别忘了form 加个 enctype="multipart/form-data"
	 */
	public static String upload(HttpServletRequest request, MultipartFile file,
			 String filePath, String exsitPath) {
		String Path = "";
		StringBuffer dataPath = new StringBuffer();
		dataPath.append("upload");
		dataPath.append(filePath);
	
 
		// 得到文件对象
		if (file!=null&&!file.isEmpty()) {
			// 得到该文件的后缀，判断是否允许上传
			String fileName=file.getOriginalFilename();
			String ext = fileName.substring(fileName.lastIndexOf('.') + 1,
					fileName.length());

			// 系统路径
			String realBaseDir = request.getSession().getServletContext()
					.getRealPath("/" + dataPath.toString());
			// String realBaseDir = System.getProperty("bbs.applicationRoot")
			// + "/" + dataPath.toString();
			// 建立文件夹
			System.out.println(realBaseDir);
			File baseFile = new File(realBaseDir);

			if (!baseFile.exists()) {
				baseFile.mkdirs();
			}
			// 新文件名
			Random ran = new Random();
			long num = ran.nextLong();
			String fileRealName = DateUtil.format(new java.util.Date(),
					"yyyyMMddHHmmss") + num + "dr" + "." + ext;
			// 上传文件
			try {
				InputStream stream = file.getInputStream();// 把文件读入

				OutputStream bos = new FileOutputStream(realBaseDir + "/"
						+ fileRealName);
				// 建立一个上传文件的输出流,程序运行到这已经建立了一个空文件，打开的时候提示这个文件已经被另一个程序占用，只能打开只读副本。
				int bytesRead = 0;
				byte[] buffer = new byte[8192];
				while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
					bos.write(buffer, 0, bytesRead);// 将文件写入服务器
				}
				bos.close();
				//bos关闭后，文件已经内容并且打开不用提示文件已经被占用，直接可以打开。
				stream.close();
				dataPath.append(fileRealName);
				Path = dataPath.toString();
				// 判断图片的大小是否合适(先不判断)
				// Image img = ImageIO.read(new File(realBaseDir + "/"
				// + fileRealName));
				// int _w = img.getWidth(null);
				// int _h = img.getHeight(null);
				// if (_w < 180 || _h < 120) {
				// Path = "1";
				// File filev = new File(realBaseDir + "/" + fileRealName);
				// filev.delete();
				// // throw new
				// //
				// ManagerRuntimeException("您好，您上传的图片尺寸过小，请重新上传，图片尺寸不允许小于180*120");
				// }
				// 删除旧文件
				if (exsitPath != null && !"upload/user/defaultface.jpg".equals(exsitPath)) {
					String oldrealBaseDir = request.getSession()
							.getServletContext().getRealPath("/upload");
					File oldfile = new File(oldrealBaseDir + exsitPath);
					oldfile.delete();
				}

			} catch (Exception e) {
				Path = "error";
				//如果有异常，就删除这个文件
				File filev = new File(realBaseDir + "/" + fileRealName);
				filev.delete();
				e.printStackTrace();
			}
		}

		return Path;
	}
}
