package com.xidu.statistic.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CountsPool {
	
    private static CountsPool instance=null;
    
    private Map<String,Integer> counts=new HashMap<String,Integer>();
    
    private CountsPool(){
    	
    }
    
	public static CountsPool getInstance(){
		if(instance==null){
			instance=new CountsPool();
		}
		return instance;
	}
	
	public void initCounts(String code){
		if(counts.get(code)==null){
			counts.put(code, 0);
		}
	}
	
	public void addCounts(String code,String url){
		if(counts.get(code)==null){
			counts.put(code, 1);
			CodesPool.getInstance().setStatus(code,url);
		}else{
			counts.put(code, counts.get(code)+1);
		}
	}
	
	public Integer getCount(String code){
		return counts.get(code);
	}
}
