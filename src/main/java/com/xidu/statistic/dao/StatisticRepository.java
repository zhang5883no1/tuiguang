package com.xidu.statistic.dao;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.xidu.statistic.entity.Statistic;

public interface StatisticRepository extends CrudRepository<Statistic, Long> {

    List<Statistic> findByWxcode(String wxcode);
    
    @Query("select ip from Statistic s where s.wxcode=:wxcode group by s.ip")
    List<Statistic> countByWxcode(@Param("wxcode") String wxcode);
    
    
    @Query("from Statistic s where s.statid=:statid and s.wxcode=:wxcode and s.url=:url and s.action=1")
    List<Statistic> validCount(@Param("statid") String statid,@Param("wxcode") String wxcode,@Param("url") String url);
}
