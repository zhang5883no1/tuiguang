package com.xidu.statistic.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xidu.statistic.entity.FileDto;

@Controller
@RequestMapping("ff")
public class FileController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String init(ModelMap map, @RequestParam(value = "url", defaultValue = "") String url) throws IOException {
		if (url.equals("")) {
			url = "D://";
		}
		System.out.println(url);
		List<FileDto> list = getFileDirectoryList(url);
		map.addAttribute("paths", list);
		map.addAttribute("curl", url);
		return "cc";
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "path", defaultValue = "") String filePath,
			@RequestParam(value = "name", defaultValue = "") String fileName) {
		FileInputStream fileInputStream = null;
		ServletOutputStream servletOutputStream = null;

		try {
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(fileName.getBytes("UTF-8"), "UTF-8"));
			// 将本地文件装载到内存
			fileInputStream = new FileInputStream(filePath);
			// 实例化输出流
			servletOutputStream = response.getOutputStream();
			byte[] buff = new byte[2048];
			int bytesRead;
			// 每次尝试读取buff.length长字节，直到读完、bytesRead为-1
			while ((bytesRead = fileInputStream.read(buff, 0, buff.length)) != -1) {
				// 每次写bytesRead长字节
				servletOutputStream.write(buff, 0, bytesRead);
			}
			// 刷新缓冲区
			servletOutputStream.flush();
		} catch (IOException e) {

		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {

				}
			}
			if (servletOutputStream != null) {
				try {
					servletOutputStream.close();
				} catch (IOException e) {

				}
			}
		}
	}

	public List<FileDto> getFileDirectoryList(String path) {
		List<FileDto> list = new ArrayList<FileDto>();
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
		for (int i = array.length - 1; i >= 0; i--) {
			FileDto dto = new FileDto();
			dto.setName(array[i].getName());
			dto.setPath(URLEncoder.encode(URLEncoder.encode(array[i].getPath())));
			if (array[i].isDirectory()) {
				dto.setType(1);
			} else {
				dto.setType(0);
			}
			list.add(dto);
		}
		return list;
	}

	public void remove(String path) {
		File file = new File(path);
		file.delete();
	}

}
