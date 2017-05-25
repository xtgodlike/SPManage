package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.impl.StaticDataDao;
import com.qy.sp.manage.dto.sta.RunDailyEntity;
import com.qy.sp.manage.dto.sta.StaticEntity;
import com.qy.sp.manage.dto.sta.StatisticSalesEntity;
import com.qy.sp.manage.entity.param.StaticCommonQueryParam;

@Component 
public class StaticDao {
	@Resource
	private StaticDataDao staticDataDao;
	public List<StaticEntity> selectByAdmin(@Param("param") StaticCommonQueryParam param,@Param("pipleIds") List<String> pipleIds,@Param("channelIds") List<String> channelIds){
		return staticDataDao.selectByAdmin(param, pipleIds, channelIds);
	}
	
	public List<StaticEntity> getRunDataForProvince(@Param("param") StaticCommonQueryParam param,@Param("pipleIds") List<String> pipleIds,@Param("channelIds") List<String> channelIds){
		return staticDataDao.getRunDataForProvince(param, pipleIds, channelIds);
	}
	
	public List<StaticEntity> selectByNormal(@Param("param") StaticCommonQueryParam param, @Param("pipleIds") List<String> pipleIds,@Param("channelIds") List<String> channelIds){
		return staticDataDao.selectByNormal(param, pipleIds, channelIds);
	}

	public List<StatisticSalesEntity> selectSalesDailyByAdmin(@Param("param") StaticCommonQueryParam param,@Param("pipleIds") List<String> pipleIds,@Param("channelIds") List<String> channelIds){
		return staticDataDao.selectSalesDailyByAdmin(param, pipleIds, channelIds);
	}
	
	public List<RunDailyEntity> getDayRunData(@Param("param") StaticCommonQueryParam param,@Param("pipleIds") List<String> pipleIds,@Param("channelIds") List<String> channelIds){
		return staticDataDao.getDayRunData(param, pipleIds, channelIds);
	}
}
