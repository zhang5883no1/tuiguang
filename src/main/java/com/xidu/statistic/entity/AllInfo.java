package com.xidu.statistic.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class AllInfo {
	private String waitString;
	private String dieString;
	private String liveString;
	

	public String getWaitString() {
		return waitString;
	}


	public void setWaitString(String waitString) {
		this.waitString = waitString;
	}


	public String getDieString() {
		return dieString;
	}


	public void setDieString(String dieString) {
		this.dieString = dieString;
	}


	public String getLiveString() {
		return liveString;
	}


	public void setLiveString(String liveString) {
		this.liveString = liveString;
	}


	public void init(){
		String wait="待投放微信号:<br/>";
		String die="已结束投放微信号:<br/>";
		String live="正在投放微信号:<br/>";
		
		//等待投放的微信号列表
//		LinkedList<String> waitCodes=CodesPool.getInstance().getCodes();
//		if(waitCodes==null||waitCodes.size()==0){
//			CodesPool.getInstance().validCodes();
//		}
		
		//已投放的微信号列表
		HashMap<String,Integer> codes=CodesPool.getInstance().getCodestatus();
	
//		for(String s:waitCodes){
//			wait+=s+"<br/>";
//		}
		
		Iterator<String> it=codes.keySet().iterator();
		while(it.hasNext()){
			String code=it.next();
			if(codes.get(code)==0){
				die+=CodesPool.getInstance().getPath(code)+" , 微信号: , "+code+" ,复制数量:"+CountsPool.getInstance().getCount(code)+"<br/>";
			}
			if(codes.get(code)==1){
				live+=CodesPool.getInstance().getPath(code)+" , 微信号: , "+code+" ,复制数量:"+CountsPool.getInstance().getCount(code)+"<br/>";
			}
		}
		
		this.dieString=die;
		this.liveString=live;
		this.waitString=wait;
	}
}
