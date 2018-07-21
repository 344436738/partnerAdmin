package com.base.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;

/**
 * Java自带的API对FTP的操作
 * 
 * @Title:Ftp.java
 * @author: 周玲斌
 */
public class ftp2 {
	private String localfilename;
	private String remotefilename;
	private FtpClient ftpClient;

	public void connectServer(String ip, int port, String user,
			String password, String path) {
		try {
			/* ******连接服务器的两种方法****** */
			// 第一种方法
			 ftpClient = new FtpClient();
			 ftpClient.openServer(ip, port);
			// 第二种方法
//			ftpClient = new FtpClient(ip);

			ftpClient.login(user, password);
			// 设置成2进制传输
			ftpClient.binary();
			System.out.println("login success!");
			if (path.length() != 0) {
				// 把远程系统上的目录切换到参数path所指定的目录
				ftpClient.cd(path);
			}
			ftpClient.binary();
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 关闭连接
	 * 
	 * @author 周玲斌
	 * @date 2012-7-11
	 */
	public void closeConnect() {
		try {
			ftpClient.closeServer();
			System.out.println("disconnect success");
		} catch (IOException ex) {
			System.out.println("not disconnect");
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param localFile
	 *            本地文件
	 * @param remoteFile
	 *            远程文件
	 * @author 周玲斌
	 * @date 2012-7-11
	 */
	public void upload(String localFile, String remoteFile) {
		this.localfilename = localFile;
		this.remotefilename = remoteFile;
		TelnetOutputStream os = null;
		FileInputStream is = null;
		try {
			// 将远程文件加入输出流中
			os = ftpClient.put(this.remotefilename);
			// 获取本地文件的输入流
			File file_in = new File(this.localfilename);
			is = new FileInputStream(file_in);
			// 创建一个缓冲区
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
			}
			System.out.println("upload success");
		} catch (IOException ex) {
			System.out.println("not upload");
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (os != null) {
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param remoteFile
	 *            远程文件路径(服务器端)
	 * @param localFile
	 *            本地文件路径(客户端)
	 * @author 周玲斌
	 * @date 2012-7-11
	 */
	public void download(String remoteFile, String localFile) {
		TelnetInputStream is = null;
		FileOutputStream os = null;
		try {
			// 获取远程机器上的文件filename，借助TelnetInputStream把该文件传送到本地。
			is = ftpClient.get(remoteFile);
			File file_in = new File(localFile);
			os = new FileOutputStream(file_in);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
			}
			System.out.println("download success");
		} catch (IOException ex) {
			System.out.println("not download");
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (os != null) {
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String agrs[]) {

		String filepath[] = { "/daa2bd2b-816a-4668-b21d-8e995821afaf_carLeft.jpeg", "/1.txt" };
		String localfilepath[] = { "d:\\tmp\\1.jpeg", "d:\\tmp\\2.txt" };

		ftp2 fu = new ftp2();
		/*
		 * 使用默认的端口号、用户名、密码以及根目录连接FTP服务器
		 */
		fu.connectServer("182.18.25.154", 10021, "minxinuser", "minxinftp2015", "/");
//		   Ftp ftp=new Ftp("182.18.25.154",10021,"minxinuser","minxinftp2015");

		// 下载
		for (int i = 0; i < filepath.length; i++) {
			fu.download(filepath[i], localfilepath[i]);
		}

//		String localfile = "E:\\号码.txt";
//		String remotefile = "/temp/哈哈.txt";
//		// 上传
//		fu.upload(localfile, remotefile);
		fu.closeConnect();
	}
}