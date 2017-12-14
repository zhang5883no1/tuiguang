package com.xidu.statistic.entity;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import com.xidu.statistic.util.FileUpdateUtil;

public class CodesPool {

    private static CodesPool pool=null;
    
	private PropertiesConfig properties=new PropertiesConfig();

    //待添加微信号列表
    private static LinkedList<String> codes=new LinkedList<String>();
    //微信号对应页面列表
    private static HashMap<String,String> codesPaths=new HashMap<String,String>();
    //进行中微信状态列表
    //0 已过期  1进行中
    private static HashMap<String,Integer> codestatus=new HashMap<String,Integer>();
    
    public static CodesPool getInstance(){
    	if(pool==null){
    		pool=new CodesPool();
    	}
    	return pool;
    }
	
    //初始化微信号列表
    private void setCodes(){
    	System.out.println(properties.getCodelist());
    	String[] ss=properties.getCodelist().split(",");
    	for(String s:ss){
    		codes.add(s);
    	}
    }
    
    public void validCodes(){
    	setCodes();
    }
    //获取微信号所放路径
    public String getPath(String code){
    	return codesPaths.get(code);
    }
    
    //设置微信号所在路径
    public void setPath(String code,String path){
    	codesPaths.put(code, path);
    }
    
    
    //上线下一个微信号
    public void tolive(String oldcode){
    	if(codes.size()<=0){
    		setCodes();
    	}
    	//获取第一个
    	String newcode=codes.getFirst();
    	updateCode(oldcode,newcode);
    	//移除第一个
    	codes.removeFirst();
    	updateStatus(newcode,1);
    	CountsPool.getInstance().initCounts(newcode);
    }
    
    //更新微信号
	public void updateCode(String oldcode,String newcode){
    	//获取老微信号页面地址
    	String path=getPath(oldcode);
    	System.out.println("old:"+oldcode+" ,path:"+path);
    	//获取地址，修改页面
    	String newpath=properties.getBasepath()+"/"+path+"/index.html";
		try {
			new FileUpdateUtil().autoReplace(newpath, newpath, oldcode, newcode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("文件替换完成，插入新队列,微信号:"+newcode+" ,path:"+path);
		//新添加的微信号加入缓存
    	//setPath(newcode, path);
    }
    
    public void updateStatus(String code,Integer status){
    	codestatus.put(code, status);
    }
    
    public HashMap<String,Integer> getCodestatus(){
    	return this.codestatus;
    }

	public void setStatus(String code,String url) {
		// TODO Auto-generated method stub
		if(codestatus.get(code)==null){
			System.out.println("微信号不存在，添加微信号 :"+code+" ,url:"+url);
			updateStatus(code, 1);
			codesPaths.put(code, url);
		}
	}
    
	
	public LinkedList<String> getCodes(){
		return codes;
	}
	
}
