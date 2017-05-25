package com.qy.sp.manage.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qy.sp.manage.common.utils.CommonServiceUtil;
import com.qy.sp.manage.dao.base.RedisDao;

@Service
public class CommonService{

	@Resource
	protected RedisDao redisDao;
	
	@PostConstruct
	public void init() {
		CommonServiceUtil.setCommonService(this);
	}

	public RedisDao getRedisDao() {
		return redisDao;
	}
}
