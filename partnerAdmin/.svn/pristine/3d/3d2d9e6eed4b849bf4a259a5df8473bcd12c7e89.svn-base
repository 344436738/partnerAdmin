package com.business.util.resume;

import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName: PropertiesUtility
 * @Description: 读取properties配置文件
 * 
 */
public class PropertiesUtility {
	private static PropertiesUtility instance = null;
	private Properties prop = new Properties();
	private InputStream is = null;

	private PropertiesUtility() {
	}

	public static synchronized PropertiesUtility getInstance() {
		if (instance == null) {
			instance = new PropertiesUtility();
		}
		return instance;
	}

	public String findFileValue(String file, String key) {
		try {
			is = this.getClass().getClassLoader().getResourceAsStream(file);
			prop.load(is);
			if (prop.containsKey(key)) {
				file = prop.getProperty(key);
			}
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}
}
