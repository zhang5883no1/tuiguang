package com.xidu.statistic.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Statistic implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	
	private String ip;

	private String ua;

	private String brower;

	private String statid;

	private String wxcode;

	private long timestamp;

	private String time;

	private String action;

	private String url;

	public Statistic() {
		super();
	}

	public Statistic(long id, String ip, String ua, String brower, String statid, String wxcode, long timestamp,
			String time, String action, String url) {
		this.id = id;
		this.ip = ip;
		this.ua = ua;
		this.brower = brower;
		this.statid = statid;
		this.wxcode = wxcode;
		this.timestamp = timestamp;
		this.time = time;
		this.action = action;
		this.url = url;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Statistic(String ip, String ua, String brower, String statid, String wxcode, long timestamp, String time,
			String action, String url) {
		super();
		this.ip = ip;
		this.ua = ua;
		this.brower = brower;
		this.statid = statid;
		this.wxcode = wxcode;
		this.timestamp = timestamp;
		this.time = time;
		this.action = action;
		this.url = url;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getBrower() {
		return brower;
	}

	public void setBrower(String brower) {
		this.brower = brower;
	}

	public String getStatid() {
		return statid;
	}

	public void setStatid(String statid) {
		this.statid = statid;
	}

	public String getWxcode() {
		return wxcode;
	}

	public void setWxcode(String wxcode) {
		this.wxcode = wxcode;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPath(){
		return this.url.replace("/", "-");
	}
}
