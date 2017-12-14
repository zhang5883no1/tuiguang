package com.xidu.statistic.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.xidu.statistic.dao.CodePathRepository;
import com.xidu.statistic.dao.StatisticRepository;
import com.xidu.statistic.entity.CodePath;
import com.xidu.statistic.entity.CodesPool;
import com.xidu.statistic.entity.CountsPool;
import com.xidu.statistic.entity.Statistic;
import com.xidu.statistic.service.StatisticService;

@Service
public class StatisticServiceImpl implements StatisticService{
	
	@Resource
	private StatisticRepository statisticRepository;
	@Resource
	private CodePathRepository codePathRepository;

	@Override
	public void save(Statistic statistic) {
		// TODO Auto-generated method stub
		statisticRepository.save(statistic);
		validCounts(statistic);
	}

	@Override
	public List<Statistic> getList(String wxcode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Async
	private void validCounts(Statistic statistic){
		if("1".equals(statistic.getAction())){
			int counts=statisticRepository.validCount(statistic.getStatid(), statistic.getWxcode(), statistic.getUrl()).size();
			if(counts<2){
				CountsPool.getInstance().addCounts(statistic.getWxcode(),statistic.getUrl());
				CodesPool.getInstance().setPath(statistic.getWxcode(), statistic.getPath());
			}
		}
	}

	@Override
	public List<CodePath> getAllCodePath() {
		// TODO Auto-generated method stub
		Iterator<CodePath> it= codePathRepository.findAll().iterator();
		List<CodePath> list=new ArrayList<CodePath>();
		while(it.hasNext()){
			list.add(it.next());
		}
		return list;
	}
}
