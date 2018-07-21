package com.base.test;

import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.util.TimeZone;  

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;  
import org.apache.commons.net.ftp.FTPClientConfig;  
import org.apache.commons.net.ftp.FTPFile;  
import org.apache.commons.net.ftp.FTPReply;  
  
import org.apache.log4j.Logger;  
  
public class Ftp {  
    private FTPClient ftpClient;  
    private String strIp;  
    private int intPort;  
    private String user;  
    private String password;  
  
    private static Logger logger = Logger.getLogger(Ftp.class.getName());  
  
    /* * 
     * Ftp构造函数 
     */  
    public Ftp(String strIp, int intPort, String user, String Password) {  
        this.strIp = strIp;  
        this.intPort = intPort;  
        this.user = user;  
        this.password = Password;  
        this.ftpClient = new FTPClient();  
    }  
    /** 
     * @return 判断是否登入成功 
     * */  
    public boolean ftpLogin() {  
        boolean isLogin = false;  
        FTPClientConfig ftpClientConfig = new FTPClientConfig();  
        ftpClientConfig.setServerTimeZoneId(TimeZone.getDefault().getID());  
//        this.ftpClient.setControlEncoding("GBK");  
        this.ftpClient.configure(ftpClientConfig);  
        try {  
            if (this.intPort > 0) {  
                this.ftpClient.connect(this.strIp, this.intPort);  
            } else {  
                this.ftpClient.connect(this.strIp);  
            }  
            // FTP服务器连接回答  
            int reply = this.ftpClient.getReplyCode();  
            if (!FTPReply.isPositiveCompletion(reply)) {  
                this.ftpClient.disconnect();  
                logger.error("登录FTP服务失败！");  
                return isLogin;  
            }  
            this.ftpClient.setControlEncoding("UTF-8");
            this.ftpClient.login(this.user, this.password);  
            // 设置传输协议  
            this.ftpClient.enterLocalPassiveMode();  
            this.ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);  
            logger.info("恭喜" + this.user + "成功登陆FTP服务器");  
            isLogin = true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.error(this.user + "登录FTP服务失败！" + e.getMessage());  
        }  
        this.ftpClient.setBufferSize(1024 * 2);  
        this.ftpClient.setDataTimeout(30 * 1000);  
        return isLogin;  
    }  
    
    public boolean ftpLogin2() {  
        boolean isLogin = false;  
        try{
	        this.ftpClient.connect(this.strIp, 21);
		    this.ftpClient.setControlEncoding("UTF-8");
		    this.ftpClient.login(this.user, password);
		    this.ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            logger.info("恭喜" + this.user + "成功登陆FTP服务器");  
            isLogin = true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.error(this.user + "登录FTP服务失败！" + e.getMessage());  
        }  
        return isLogin;  
    }
  
    /** 
     * @退出关闭服务器链接 
     * */  
    public void ftpLogOut() {  
        if (null != this.ftpClient && this.ftpClient.isConnected()) {  
            try {  
                boolean reuslt = this.ftpClient.logout();// 退出FTP服务器  
                if (reuslt) {  
                    logger.info("成功退出服务器");  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
                logger.warn("退出FTP服务器异常！" + e.getMessage());  
            } finally {  
                try {  
                    this.ftpClient.disconnect();// 关闭FTP服务器的连接  
                } catch (IOException e) {  
                    e.printStackTrace();  
                    logger.warn("关闭FTP服务器的连接异常！");  
                }  
            }  
        }  
    }  
  
    /*** 
     * 上传Ftp文件 
     * @param localFile 当地文件 
     * @param romotUpLoadePath上传服务器路径 - 应该以/结束 
     * */  
    public boolean uploadFile(File localFile, String romotUpLoadePath) {  
        BufferedInputStream inStream = null;  
        boolean success = false;  
        try {  
            this.ftpClient.changeWorkingDirectory(romotUpLoadePath);// 改变工作路径  
            inStream = new BufferedInputStream(new FileInputStream(localFile));  
            logger.info(localFile.getName() + "开始上传.....");  
            success = this.ftpClient.storeFile(localFile.getName(), inStream);  
            if (success == true) {  
                logger.info(localFile.getName() + "上传成功");  
                return success;  
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
            logger.error(localFile + "未找到");  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (inStream != null) {  
                try {  
                    inStream.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return success;  
    }  
  
    /*** 
     * 下载文件 
     * @param remoteFileName   待下载文件名称 
     * @param localDires 下载到当地那个路径下 
     * @param remoteDownLoadPath remoteFileName所在的路径 
     * */  
  
    public boolean downloadFile(String remoteFileName, String localDires,  
            String remoteDownLoadPath) {  
        String strFilePath = localDires + remoteFileName;  
        BufferedOutputStream outStream = null;  
        boolean success = false;  
        try {  
            
            FTPFile[] fs = ftpClient.listFiles();  
            for (FTPFile ff : fs)  
            {
            	System.out.println(ff.getName());
            }
            
            this.ftpClient.changeWorkingDirectory(remoteDownLoadPath);  
            
            outStream = new BufferedOutputStream(new FileOutputStream(  
                    strFilePath));  
            logger.info(remoteFileName + "开始下载....");  
//            this.ftpClient.setRemoteVerificationEnabled(false);
            success = this.ftpClient.retrieveFile(remoteFileName, outStream);  
            if (success == true) {  
                logger.info(remoteFileName + "成功下载到" + strFilePath);  
                return success;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.error(remoteFileName + "下载失败");  
        } finally {  
            if (null != outStream) {  
                try {  
                    outStream.flush();  
                    outStream.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        if (success == false) {  
            logger.error(remoteFileName + "下载失败!!!");  
        }  
        return success;  
    }  
    
    /*** 
     * 下载文件 
     * @param remoteFileName   待下载文件名称 
     * @param localDires 下载到当地那个路径下 
     * @param remoteDownLoadPath remoteFileName所在的路径 
     * */ 
    public boolean downloadFile(String remoteFileName, HttpServletResponse response,  
            String remoteDownLoadPath) {  
        boolean success = false;  
        try {  
            this.ftpClient.changeWorkingDirectory(remoteDownLoadPath);  
            logger.info(remoteFileName + "开始下载....");  
            success = this.ftpClient.retrieveFile(remoteFileName, response.getOutputStream());  
            if (success == true) {  
                logger.info(remoteFileName + "成功下载到");
                response.getOutputStream().flush();
                return success;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.error(remoteFileName + "下载失败");  
        } finally {  
            
        }  
        if (success == false) {  
            logger.error(remoteFileName + "下载失败!!!");  
        }  
        return success;  
    }
  
    /*** 
     * @上传文件夹 
     * @param localDirectory 
     *            当地文件夹 
     * @param remoteDirectoryPath 
     *            Ftp 服务器路径 以目录"/"结束 
     * */  
    /*public boolean uploadDirectory(String localDirectory,  
            String remoteDirectoryPath) {  
        File src = new File(localDirectory);  
        try {  
            remoteDirectoryPath = remoteDirectoryPath + src.getName() + "/";  
            this.ftpClient.makeDirectory(remoteDirectoryPath);  
            // ftpClient.listDirectories();  
        } catch (IOException e) {  
            e.printStackTrace();  
            logger.info(remoteDirectoryPath + "目录创建失败");  
        }  
        File[] allFile = src.listFiles();  
        for (int currentFile = 0; currentFile < allFile.length; currentFile++) {  
            if (!allFile[currentFile].isDirectory()) {  
                String srcName = allFile[currentFile].getPath().toString();  
                uploadFile(new File(srcName), remoteDirectoryPath);  
            }  
        }  
        for (int currentFile = 0; currentFile < allFile.length; currentFile++) {  
            if (allFile[currentFile].isDirectory()) {  
                // 递归  
                uploadDirectory(allFile[currentFile].getPath().toString(),  
                        remoteDirectoryPath);  
            }  
        }  
        return true;  
    }*/ 
    
    public boolean uploadDirectory(String localDirectory,  
            String remoteDirectoryPath) { 
    	System.out.println("路径"+localDirectory);
        File src = new File(localDirectory);  
        try {  
            remoteDirectoryPath = File.separator+remoteDirectoryPath + src.getName() + File.separator;  
            this.ftpClient.makeDirectory(remoteDirectoryPath);  
            // ftpClient.listDirectories();  
        } catch (IOException e) {  
            e.printStackTrace();  
            logger.info(remoteDirectoryPath + "目录创建失败"); 
            return false;
        }  
        File[] allFile = src.listFiles();
        if(allFile!=null){
        	for (int currentFile = 0; currentFile < allFile.length; currentFile++) {  
        		if (!allFile[currentFile].isDirectory()) {  
        			String srcName = allFile[currentFile].getPath().toString();  
        			boolean flag = uploadFile(new File(srcName), remoteDirectoryPath);  
        			if(!flag){
        				return flag;
        			}
        		}  
        	}  
        	for (int currentFile = 0; currentFile < allFile.length; currentFile++) {  
        		if (allFile[currentFile].isDirectory()) {  
        			// 递归  
        			boolean flag = uploadDirectory(allFile[currentFile].getPath().toString(),  
        					remoteDirectoryPath); 
        			if(!flag){
        				return flag;
        			}
        		}  
        	}  
        }
        return true;  
    }
  
    /*** 
     * @下载文件夹 
     * @param localDirectoryPath本地地址 
     * @param remoteDirectory 远程文件夹 
     * */  
    public boolean downLoadDirectory(String localDirectoryPath,String remoteDirectory) {  
        try {  
            String fileName = new File(remoteDirectory).getName();  
            localDirectoryPath = localDirectoryPath + fileName + "//";  
            new File(localDirectoryPath).mkdirs();  
            FTPFile[] allFile = this.ftpClient.listFiles(remoteDirectory);  
            for (int currentFile = 0; currentFile < allFile.length; currentFile++) {  
                if (!allFile[currentFile].isDirectory()) {  
                    downloadFile(allFile[currentFile].getName(),localDirectoryPath, remoteDirectory);  
                }  
            }  
            for (int currentFile = 0; currentFile < allFile.length; currentFile++) {  
                if (allFile[currentFile].isDirectory()) {  
                    String strremoteDirectoryPath = remoteDirectory + File.separator+ allFile[currentFile].getName();  
                    downLoadDirectory(localDirectoryPath,strremoteDirectoryPath);  
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
            logger.info("下载文件夹失败");  
            return false;  
        }  
        return true;  
    }  
    // FtpClient的Set 和 Get 函数  
    public FTPClient getFtpClient() {  
        return ftpClient;  
    }  
    public void setFtpClient(FTPClient ftpClient) {  
        this.ftpClient = ftpClient;  
    }  
      
    public static void main(String[] args) throws IOException {  
        Ftp ftp=new Ftp("182.18.25.154",10021,"minxinuser","minxinftp2015");
//    	Ftp ftp=new Ftp("10.255.9.2",48796,"minxinuser","minxinftp@2015");
        ftp.ftpLogin(); 
        ftp.downloadFile("1.txt", "d://", "/");
        //上传文件夹  
//        ftp.uploadDirectory("D:\\9addfaaf-8604-4c07-82cf-6f9c896e83e7", "");  
        //下载文件夹  
//        ftp.downLoadDirectory("d://tmp//", "/home/data/DataProtemp");  
        /*ftp.ftpLogin2();
        ftp.downLoadDirectory("d:/", "/4f8661e0-2794-4728-9012-1ea6c9e8854a");*/
        ftp.ftpLogOut(); 
    	
    	/*FTPClient ftpClient = new FTPClient();
        String hostName = "111.207.246.47";
        String userName = "mx";
        String password = "123456";
        String remoteDir = "/4a8134f4-cdaa-402c-86e6-3d419edf696c";
        try {
          ftpClient.connect(hostName, 21);
          ftpClient.setControlEncoding("UTF-8");
          ftpClient.login(userName, password);
          ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
          FTPFile[] files = ftpClient.listFiles(remoteDir);
          for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getName());
          }
          File file = new File("d://index.jsp");
          FileOutputStream fos = new FileOutputStream(file);
          ftpClient.retrieveFile(remoteDir + "/index.jsp", fos);
          fos.close();
        } catch (Exception e) {
          e.printStackTrace();
        }*/
    }  
}  