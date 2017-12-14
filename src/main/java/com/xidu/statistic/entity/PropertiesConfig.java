package com.xidu.statistic.entity;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfig {
	private static String active="";
	
	public String getCodelist() {
		return readData("com.xidu.wx.codes");
	}

	public String getBasepath() {
		return readData("com.xidu.file.basepath");
	}

	public int getUpdateCount() {
		return Integer.valueOf(readData("com.xidu.wx.updateCount"));
	}

	public int getCountsFlag() {
		return Integer.valueOf(readData("com.xidu.wx.counts.flag"));
	}
	
	public int getFileUpdateFlag() {
		return Integer.valueOf(readData("com.xidu.file.update.flag"));
	}
	private String readData(String key) {
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(PropertiesConfig.class.getResourceAsStream("/application-"+getActive()+".properties"));
			props.load(in);
			in.close();
			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getActive(){
		if(null!=active&&!"".equals(active)){
			return active;
		}
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(PropertiesConfig.class.getResourceAsStream("/application.properties"));
			props.load(in);
			in.close();
			String value = props.getProperty("spring.profiles.active");
			active=value;
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
