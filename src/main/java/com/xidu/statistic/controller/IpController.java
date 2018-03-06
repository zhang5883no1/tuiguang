package com.xidu.statistic.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xidu.statistic.util.HttpUtil;
import com.xidu.statistic.util.IPUtil;


@RestController
@RequestMapping("ip")
public class IpController {
	 private final Logger logger = Logger.getLogger(getClass());
	 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getArea(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Access-Control-Allow-Origin", "*");
		String ip=request.getParameter("ip");
		String rip=new IPUtil().getRemortIP2(request);
		String s="";
		if("".equals(ip)){
			s=HttpUtil.httpGet("http://ip.taobao.com/service/getIpInfo.php?ip="+rip);
		}else{
			s=HttpUtil.httpGet("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
		}
		try{
			String s1=s.substring(s.indexOf("\"city\":\"")+8);
			String r=s1.substring(0, s1.indexOf("\","));
			logger.info("pid:"+ip+" ,rip:"+rip+" ,city:"+r);
			return r;
		}catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}
}
