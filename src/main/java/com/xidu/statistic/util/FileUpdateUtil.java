package com.xidu.statistic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.xidu.statistic.entity.PropertiesConfig;

public class FileUpdateUtil {
	private Logger logger = Logger.getLogger(getClass());

	// @SuppressWarnings("unused")
	// public void autoReplace(String filePath, String outPath,String
	// oldString,String repString)
	// throws IOException {
	// if(new PropertiesConfig().getFileUpdateFlag()==0){
	// return;
	// }
	// logger.info("开始修改文件,文件路径:"+filePath+" , 原字符:"+oldString+" ,
	// 新字符:"+repString);
	// File file = new File(filePath);
	// Long fileLength = file.length();
	// byte[] fileContext = new byte[fileLength.intValue()];
	// FileInputStream in = new FileInputStream(filePath);
	// in.read(fileContext);
	// in.close();
	// String str = new String(fileContext);
	//
	// str = new FileUpdateUtil().regular(str,oldString,repString);
	//
	// PrintWriter out = new PrintWriter(outPath);
	// out.write(str.toCharArray());
	// out.flush();
	// out.close();
	// }

	public void autoReplace(String filePath, String outPath, String oldString, String repString) throws IOException {
		if (new PropertiesConfig().getFileUpdateFlag() == 0) {
			return;
		}
		System.out.println("开始修改文件,文件路径:" + filePath + " , 原字符:" + oldString);
		File file = new File(filePath);
		Long fileLength = file.length();
		byte[] fileContext = new byte[fileLength.intValue()];
		FileInputStream in = new FileInputStream(filePath);
		in.read(fileContext);
		in.close();
		String str = new String(fileContext);

		str = new FileUpdateUtil().regular(str, ",\"" + oldString + "\",", ",");
		str = new FileUpdateUtil().regular(str, ",\"" + oldString + "\"", "");
		str = new FileUpdateUtil().regular(str, "\"" + oldString + "\",", "");

		PrintWriter out = new PrintWriter(outPath);
		out.write(str.toCharArray());
		out.flush();
		out.close();
	}

	private String regular(String orgString, String oldString, String repString) {
		return orgString.replace(oldString, repString);
	}

	public String getCodes(String filePath) throws IOException {
		File file = new File(filePath);
		Long fileLength = file.length();
		byte[] fileContext = new byte[fileLength.intValue()];
		FileInputStream in = new FileInputStream(filePath);
		in.read(fileContext);
		in.close();
		String str = new String(fileContext);
		String[] a = str.split("//start");
		String[] b = a[1].split("//end");
		return b[0];
	}

	public void updateCodes(String filePath, String codes) throws IOException {
		File file = new File(filePath);
		Long fileLength = file.length();
		byte[] fileContext = new byte[fileLength.intValue()];
		FileInputStream in = new FileInputStream(filePath);
		in.read(fileContext);
		in.close();
		String str = new String(fileContext);
		String[] a = str.split("//start");
		String[] b = a[1].split("//end");

		str = a[0] + "//start\r\n" + codes + "\r\n//end\r\n" + b[1];
		PrintWriter out = new PrintWriter(filePath);
		out.write(str.toCharArray());
		out.flush();
		out.close();
	}

	public List<String> getFileDirectoryList(String path) {
		List<String> list = new ArrayList<String>();
		File file = new File(path);
		File[] array = file.listFiles();
		Arrays.sort(array, new Comparator<File>() {
			public int compare(File f1, File f2) {
				long diff = f1.lastModified() - f2.lastModified();
				if (diff > 0)
					return 1;
				else if (diff == 0)
					return 0;
				else
					return -1;
			}
		});
//		for (int i = 0; i < array.length; i++) {
//			if (array[i].isDirectory()) {
//				list.add(array[i].getName());
//			}
//		}
		for (int i = array.length-1; i >= 0; i--) {
			if (array[i].isDirectory()) {
				list.add(array[i].getName());
			}
		}
		return list;
	}

	public List<String> getAllFile(String path) {
		List<String> list = new ArrayList<>();
		File file = new File(path);
		File[] array = file.listFiles();
		for (int i = 0; i < array.length; i++) {
			if (array[i].isFile()) {
				System.out.println("^^^^^" + array[i].getName());
				System.out.println("#####" + array[i]);
				System.out.println("*****" + array[i].getPath());
				list.add(array[i].getName());
			} else if (array[i].isDirectory()) {
				getAllFile(array[i].getPath());
			}
		}
		return list;
	}

	public static void main(String[] args) throws IOException {
		// new FileUpdateUtil().autoReplace(
		// "C:\\Users\\Administrator\\Desktop\\uc20.sunyidai.com\\index.html",
		// "C:\\Users\\Administrator\\Desktop\\test\\index.html","","");
		// new Test().regular("ttttt");
		new FileUpdateUtil().getFileDirectoryList("C:\\Users\\Administrator\\Desktop\\list");
	}
}
