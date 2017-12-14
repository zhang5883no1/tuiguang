package com.xidu.statistic.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xidu.statistic.entity.PropertiesConfig;
import com.xidu.statistic.util.FileUpdateUtil;

@Controller
@RequestMapping("codes")
public class CodesController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String init(ModelMap map) throws IOException{
		List<String> paths=new FileUpdateUtil().getFileDirectoryList(new PropertiesConfig().getBasepath());
		map.addAttribute("paths", paths);
		return "codeslist";
	}
	
	//根据url获取当前在线的微信号
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public String getList(@RequestParam(value = "url", defaultValue = "") String url) throws IOException{
		String p=new PropertiesConfig().getBasepath()+"/"+url+"/index.html";
		
		File fileToChange = new File(new PropertiesConfig().getBasepath()+"/"+url+"/");
		fileToChange.setLastModified(System.currentTimeMillis());
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

	//修改url下的微信号
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	@ResponseBody
	public String updateCodes(@RequestParam(value = "code", defaultValue = "") String codes,@RequestParam(value = "url", defaultValue = "") String url) throws IOException{
		String[] ss =codes.split(",");
		String begin="var accs=[";
		String end="];";
		boolean f=false;
		for(String s:ss){
			if(f){
				begin+=",";
			}
			f=true;
			begin+="\""+s+"\"";
		}
		String p=new PropertiesConfig().getBasepath()+"/"+url+"/index.html";
		new FileUpdateUtil().updateCodes(p, begin+end);
		
		return "success";
	}
	
	
	
}
