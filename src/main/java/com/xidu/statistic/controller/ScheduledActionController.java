package com.xidu.statistic.controller;

import java.io.IOException;
import java.util.LinkedList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xidu.statistic.entity.ActionInfo;
import com.xidu.statistic.entity.ActionPool;

@Controller
@RequestMapping("schedule")
public class ScheduledActionController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String init(ModelMap map) throws IOException{
		LinkedList<ActionInfo> actionlist=ActionPool.getInstance().getList();
//		actionlist.add(new ActionInfo("2017-08-22", "4", "sx", "1"));
//		actionlist.add(new ActionInfo("2017-08-23", "3", "sx", "2"));
//		actionlist.add(new ActionInfo("2017-08-24", "2", "sx", "3"));
		map.addAttribute("actions", actionlist);
		return "schedulelist";
	}
	
	//修改url下的微信号
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	@ResponseBody
	public String updateCodes(@RequestParam(value = "a", defaultValue = "") String actions) throws IOException{
		LinkedList<ActionInfo> list =new LinkedList<ActionInfo>();
		System.out.println(actions);
		String[] as=actions.split("◇");
		for(String s:as){
			String[] ss=s.split("◆");
			String ymd=ss[0];
			String hour=ss[1];
			String doing=ss[2];
			String detail=ss[3];
			list.add(new ActionInfo(ymd, hour, doing, detail));
		}
		ActionPool.getInstance().setList(list);
		return "success";
	}
}
