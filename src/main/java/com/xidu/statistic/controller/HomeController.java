package com.xidu.statistic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xidu.statistic.entity.AllInfo;

@Controller
public class HomeController {

	@RequestMapping(value = "/allinfo", method = RequestMethod.GET)
	public String allinfo(ModelMap map) {
		AllInfo info = new AllInfo();
		info.init();
		map.addAttribute("infos", info);
		return "info";
	}

	@RequestMapping("/")
	public String index(ModelMap map) {
		map.addAttribute("host", "http://www.baidu.com");
		return "index";
	}
}
