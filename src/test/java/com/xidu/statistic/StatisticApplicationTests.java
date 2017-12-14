package com.xidu.statistic;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xidu.statistic.dao.OldStatisticRepository;
import com.xidu.statistic.dao.StatisticRepository;
import com.xidu.statistic.entity.OldStatistic;
import com.xidu.statistic.entity.Statistic;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticApplicationTests {

	@Autowired
	private StatisticRepository sr;
	@Autowired
	private OldStatisticRepository osr;
	
	@Test
	public void tt(){
		System.out.println("test success");
	}
	
	public void add() {
		for(int i=0;i<100000;i++){
			Statistic s=new Statistic();
			s.setIp("1");
			s.setWxcode(i+"");
			sr.save(s);
		}
	}
	
	public void count(){
		System.out.println(sr.countByWxcode("1").size());
	}

	public void validCount(){
		System.out.println(sr.validCount("2", "1", "a").size());
	}
	
	public void ss(){
		Iterator<Statistic> sit=sr.findAll().iterator();
		List<OldStatistic> list=new LinkedList<OldStatistic>();
		while(sit.hasNext()){
			list.add(new OldStatistic(sit.next()));
		}
		osr.save(list);
		sr.deleteAll();
	}
}
