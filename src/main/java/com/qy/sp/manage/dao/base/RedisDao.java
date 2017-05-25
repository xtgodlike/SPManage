package com.qy.sp.manage.dao.base;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisDao extends AbstractDao{

	private static Logger logger = LoggerFactory.getLogger(RedisDao.class);
	@Resource
	protected RedisTemplate<String, String> redisTemplate;
	
	public void put(final String mapKey,final String key,final String value){
		try{
			HashOperations<String,String,String> hashOPeration =  redisTemplate.opsForHash();
			hashOPeration.put(mapKey, key, value);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("redisError:"+e.toString());
		}
	}
	public String get(final String mapKey,final String key){
		try{
			HashOperations<String,String,String> hashOPeration =  redisTemplate.opsForHash();
			return hashOPeration.get(mapKey, key);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("redisError:"+e.toString());
		}
		return null;
	}
	public void remove(String mapKey,String key){
		try{
			HashOperations<String,String,String> hashOPeration =  redisTemplate.opsForHash();
			hashOPeration.delete(mapKey, key);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("redisError:"+e.toString());
		}
	}
	public void clear(String mapKey){
		try{
			redisTemplate.delete(mapKey);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("redisError:"+e.toString());
		}
	}
}
