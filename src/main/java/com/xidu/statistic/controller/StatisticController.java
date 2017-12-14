package com.xidu.statistic.controller;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xidu.statistic.entity.PropertiesConfig;
import com.xidu.statistic.entity.Statistic;
import com.xidu.statistic.service.impl.StatisticServiceImpl;
import com.xidu.statistic.util.IPUtil;

@RestController
@RequestMapping("stat")
public class StatisticController {

	 private final Logger logger = Logger.getLogger(getClass());
	    @Autowired
	    private StatisticServiceImpl statisticService;
	    
	    /**
	     * 
	     * @param ua
	     * @param brower
	     * @param statid
	     * @param wxcode
	     * @param action 0加载成功  1已复制
	     * @param url
	     */
	    @RequestMapping(value = "/s" ,method = RequestMethod.GET)
	    public void save(HttpServletRequest request,
	    		@RequestParam(value = "u", defaultValue = "") String ua
	    		,@RequestParam(value = "b", defaultValue = "") String brower
	    		,@RequestParam(value = "s", defaultValue = "") String statid
	    		,@RequestParam(value = "c", defaultValue = "") String wxcode
	    		,@RequestParam(value = "a", defaultValue = "") String action
	    		,@RequestParam(value = "l", defaultValue = "") String url) {
	    	String referer = request.getHeader("referer");  
	    	if(new PropertiesConfig().getCountsFlag()==0){
	    		return;
	    	}
	    	// 如果是直接输入的地址，或者不是从本网站访问的重定向到本网站的首页  
	        //if (referer == null || !referer.startsWith("http://uc")) { 
	        	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        	Date date=new Date();
	        	long timestamp=date.getTime();
	        	String time=sf.format(date);
	        	String ip=new IPUtil().getRemortIP(request);
	        	statisticService.save(new Statistic(ip,ua,brower,statid,wxcode,timestamp,time,action,url));
	        	logger.info("action:"+action+" ,url:"+url+" ,ip:"+ip+" ,statid:"+statid);
	        //}
	    }
	    
//	    @RequestMapping(value="/i" ,method = RequestMethod.GET)
//		@ResponseBody 
//		public String wxCount(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException{
//			String type=request.getParameter("type");
//			String ip=new IPUtil().getRemortIP(request);
//			String countnum=getCount(ip,type);
//			JSONObject json=new JSONObject();
//			json.accumulate("counts", countnum);
//			String responseText = json.toString();
//			String callback=request.getParameter("callback");
//			if(ValidateUtil.isEmpty(callback)){
//				return callback+"("+responseText+")";
//			}else{
//				return responseText;
//			}
//		}
//		
//		private String getCount(String ip,String type){
//			String re=RedisUtil.getString(type+"."+ip);
//			if(re==null||"".equals(re)){
//				re=RedisUtil.getString(type);
//				if(re==null||"".equals(re)){
//					re="1";
//				}else{
//					if(Integer.valueOf(re)>1000000){
//						re="1";
//					}
//				}
//				re=(Integer.valueOf(re)+1)+"";
//				RedisUtil.setString(type, re);
//				RedisUtil.setString(type+"."+ip, re,5*60);
//			}
//			return re;
//		}
		
	    
}
