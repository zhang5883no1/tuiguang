package com.xidu.statistic.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xidu.statistic.entity.CodePath;
import com.xidu.statistic.service.StatisticService;
import com.xidu.statistic.util.FileUpdateUtil;

@Controller
@RequestMapping("cf")
public class CodesFileController {
	@Autowired
	private StatisticService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String init(ModelMap map) throws IOException{
		List<CodePath> list=service.getAllCodePath();
//		List<String> paths=new ArrayList<String>();
//		for(CodePath cp:list){
//			List<String> p=new FileUpdateUtil().getFileDirectoryList(cp.getPath());
//			for(String s:p){
//				paths.add(cp.getName()+"："+s);
//			}
//		}
		map.addAttribute("paths", list);
		return "codesfile";
	}
	
	//根据url获取当前在线的微信号
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public String getList(@RequestParam(value = "url", defaultValue = "") String url) throws IOException{
		String p=url+"/index.html";
		String r=new FileUpdateUtil().getCodes(p);
		String[] ss=r.replace("var accs=[\"", "").replace("\"];", "").trim().split("\",\"");
		String b="";
		boolean f=false;
		for(String s:ss){
			if(f){
				b+=",";
			}
			f=true;
			b+=s;
		}
		return b;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public void handleFileUpload(HttpServletRequest request,HttpServletResponse response) throws IOException {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		String url=request.getParameter("opt");
		String[] names=request.getParameterValues("filename");
		for(int i=0;i<names.length;i++){
			System.out.println(names[i]);
		}
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
				try {
					String uploadFilePath = file.getOriginalFilename();
					System.out.println("uploadFlePath:" + uploadFilePath);
					// 截取上传文件的文件名
					String uploadFileName = uploadFilePath.substring(uploadFilePath.lastIndexOf('\\') + 1,
							uploadFilePath.indexOf('.'));
					System.out.println("multiReq.getFile()" + uploadFileName);
					// 截取上传文件的后缀
					String uploadFileSuffix = uploadFilePath.substring(uploadFilePath.indexOf('.') + 1,
							uploadFilePath.length());
					System.out.println("uploadFileSuffix:" + uploadFileSuffix);
					System.out.println(url+"/wxpic/"+names[i]+".jpg");
					stream = new BufferedOutputStream(new FileOutputStream(
							new File(url+"/wxpic/"+names[i]+".jpg")));
					byte[] bytes = file.getBytes();
					stream.write(bytes, 0, bytes.length);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (stream != null) {
							stream.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("上传文件为空");
			}
		}
		System.out.println("文件接受成功了");
		String begin="var accs=[";
		String end="];";
		boolean f=false;
		for(String s:names){
			if(!"".equals(s)){
				if(f){
					begin+=",";
				}
				f=true;
				begin+="\""+s+"\"";
			}
		}
		String p=url+"/index.html";
		new FileUpdateUtil().updateCodes(p, begin+end);
		response.sendRedirect("/cf/");
	}
	
	
}
