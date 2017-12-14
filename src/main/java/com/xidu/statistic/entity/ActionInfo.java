package com.xidu.statistic.entity;

public class ActionInfo {

	private String ymd;
	
	private String hour;
	
	private String doing;
	
	private String detail;

	public ActionInfo(String ymd, String hour, String doing, String detail) {
		super();
		this.ymd = ymd;
		this.hour = hour;
		this.doing = doing;
		this.detail = detail;
	}

	public String getYmd() {
		return ymd;
	}

	public void setYmd(String ymd) {
		this.ymd = ymd;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getDoing() {
		return doing;
	}

	public void setDoing(String doing) {
		this.doing = doing;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
