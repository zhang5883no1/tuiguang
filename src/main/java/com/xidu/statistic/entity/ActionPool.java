package com.xidu.statistic.entity;

import java.util.LinkedList;

public class ActionPool {

	private static ActionPool pool=null;
	
	private static LinkedList<ActionInfo> list=new LinkedList<ActionInfo>();
	
	public static ActionPool getInstance(){
		if(pool == null){
			pool =new ActionPool();
			for(int i=0;i<10;i++){
				list.add(new ActionInfo("", "", "", ""));
			}
		}
		return pool;
	}

	
	public LinkedList<ActionInfo> getList() {
		return list;
	}

	public void setList(LinkedList<ActionInfo> list) {
		ActionPool.list = list;
	}
	
	public void remove(ActionInfo info){
		list.remove(info);
		list.add(new ActionInfo("", "", "", ""));
	}
	
}
