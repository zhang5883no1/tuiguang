package com.xidu.statistic.entity;

public class FileDto {

	private String name;
	private int type;
	private String path;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	/** 
	*  
	*  
	* @param name
	* @param type
	* @param path 
	*/ 
	
	public FileDto(String name, int type, String path) {
		super();
		this.name = name;
		this.type = type;
		this.path = path;
	}
	/** 
	*  
	*   
	*/ 
	
	public FileDto() {
		super();
	}
	
}
