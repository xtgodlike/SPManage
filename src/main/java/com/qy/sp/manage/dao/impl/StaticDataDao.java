package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.sta.RunDailyEntity;
import com.qy.sp.manage.dto.sta.StaticEntity;
import com.qy.sp.manage.dto.sta.StatisticSalesEntity;
import com.qy.sp.manage.entity.param.StaticCommonQueryParam;

@Component @MyBatisRepository
public interface StaticDataDao {
	
	public List<StaticEntity> selectByAdmin(@Param("param") StaticCommonQueryParam param,@Param("pipleIds") List<String> pipleIds,@Param("channelIds") List<String> channelIds);
	
	public List<StaticEntity> getRunDataForProvince(@Param("param") StaticCommonQueryParam param,@Param("pipleIds") List<String> pipleIds,@Param("channelIds") List<String> channelIds);
	
	public List<StaticEntity> selectByNormal(@Param("param") StaticCommonQueryParam param, @Param("pipleIds") List<String> pipleIds,@Param("channelIds") List<String> channelIds);

	public List<StatisticSalesEntity> selectSalesDailyByAdmin(@Param("param") StaticCommonQueryParam param,@Param("pipleIds") List<String> pipleIds,@Param("channelIds") List<String> channelIds);
	
	public List<RunDailyEntity> getDayRunData(@Param("param") StaticCommonQueryParam param,@Param("pipleIds") List<String> pipleIds,@Param("channelIds") List<String> channelIds);
}
