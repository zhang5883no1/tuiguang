package com.xidu.statistic.service;

import java.util.List;

import com.xidu.statistic.entity.CodePath;
import com.xidu.statistic.entity.Statistic;

public interface StatisticService {

	public void save(Statistic statistic);
	
	public List<Statistic> getList(String wxcode);
	
	public List<CodePath> getAllCodePath();
}
