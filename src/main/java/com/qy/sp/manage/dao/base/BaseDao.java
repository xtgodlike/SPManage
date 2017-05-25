package com.qy.sp.manage.dao.base;

import javax.annotation.Resource;

public class BaseDao {
	@Resource
	protected MongodbDao mongodbDao;
	
	@Resource
	protected RedisDao redisDao;
	
}
