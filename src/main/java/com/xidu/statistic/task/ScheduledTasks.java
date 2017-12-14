package com.xidu.statistic.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xidu.statistic.dao.OldStatisticRepository;
import com.xidu.statistic.dao.StatisticRepository;
import com.xidu.statistic.entity.ActionInfo;
import com.xidu.statistic.entity.ActionPool;
import com.xidu.statistic.entity.CodesPool;
import com.xidu.statistic.entity.CountsPool;
import com.xidu.statistic.entity.OldStatistic;
import com.xidu.statistic.entity.PropertiesConfig;
import com.xidu.statistic.entity.Statistic;
import com.xidu.statistic.util.CopyDirectory;

@Component
public class ScheduledTasks {
//	private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("HH:mm:ss");

	@Resource
	private StatisticRepository statisticRepository;
	@Resource
	private OldStatisticRepository oldStatisticRepository;

	private PropertiesConfig properties = new PropertiesConfig();

	/**
	 * @Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
	 * @Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
	 * @Scheduled(initialDelay=1000, fixedRate=5000)
	 *                               ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
	 * @Scheduled(cron="1/5 * * * * *") ：通过cron表达式定义规则
	 */

	// 每分钟执行一次
	//@Scheduled(fixedRate = 1 * 5 * 1000)
	public void checkCodes() {
		// System.out.println("现在时间：" + dateFormat.format(new Date()));
		// 获取所有已上线微信号列表
		HashMap<String, Integer> statusMap = CodesPool.getInstance().getCodestatus();
		Iterator<String> sit = statusMap.keySet().iterator();
		while (sit.hasNext()) {
			String key = sit.next();
			// System.out.println("code : "+key+" ,status
			// :"+statusMap.get(key)+" ; ");
			// 如果此微信号再线上
			if (statusMap.get(key) == 1) {
				int count = CountsPool.getInstance().getCount(key);
				// 如果复制次数大于规定值
				if (count >= properties.getUpdateCount()) {
					// 微信号下线
					CodesPool.getInstance().updateStatus(key, 0);
					// 新微信号替换旧微信号
					CodesPool.getInstance().tolive(key);
				}
			}
		}
	}
	
//	 每分钟执行一次
	@Scheduled(fixedRate = 1 * 5 * 1000)
	public void removeCode() {
		// 获取所有已上线微信号列表
		HashMap<String, Integer> statusMap = CodesPool.getInstance().getCodestatus();
		Iterator<String> sit = statusMap.keySet().iterator();
		while (sit.hasNext()) {
			String key = sit.next();
			// System.out.println("code : "+key+" ,status
			// :"+statusMap.get(key)+" ; ");
			// 如果此微信号再线上
			if (statusMap.get(key) == 1) {
				int count = CountsPool.getInstance().getCount(key);
				// 如果复制次数大于规定值
				if (count >= properties.getUpdateCount()) {
					// 微信号下线
					CodesPool.getInstance().updateStatus(key, 0);
					
//					String path=CodesPool.getInstance().getPath(key);
					CodesPool.getInstance().updateCode(key,"");
					//
				}
			}
		}
	}
		
	
	
	@Scheduled(cron="0 0 5 * * *")
	public void flushDB(){
		Iterator<Statistic> sit=statisticRepository.findAll().iterator();
		List<OldStatistic> list=new LinkedList<OldStatistic>();
		while(sit.hasNext()){
			list.add(new OldStatistic(sit.next()));
		}
		oldStatisticRepository.save(list);
		statisticRepository.deleteAll();
	}

	
	// 每分钟执行一次
	//定时任务处理
	@Scheduled(fixedRate = 1 * 5 * 1000)
	public void doScheduled() {
		Date date=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
//		int hour=date.getHours();
		int hour=Calendar.HOUR;
		String ymd=sf.format(date);
		LinkedList<ActionInfo> list=ActionPool.getInstance().getList();
		LinkedList<ActionInfo> l2=new LinkedList<ActionInfo>();
		for(ActionInfo info:list){
			if(ymd.equals(info.getYmd())){
				if(hour==Integer.valueOf(info.getHour())){
					String doing=info.getDoing();
					String[] detail=info.getDetail().split("-");
					String srcpath=properties.getBasepath()+"/"+info.getDetail();
					String targetpath=properties.getBasepath()+"/"+detail[0];
					System.out.println(srcpath);
					System.out.println(targetpath);
					CopyDirectory.copyAllFile(srcpath, targetpath);
					l2.add(new ActionInfo("", "", "", ""));
					continue;
				}
			}
			l2.add(info);
		}
		ActionPool.getInstance().setList(l2);
	}
}
