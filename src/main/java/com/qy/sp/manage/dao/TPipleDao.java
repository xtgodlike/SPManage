package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.PipleDao;
import com.qy.sp.manage.dto.TPiple;

@Component
public class TPipleDao extends BaseDao{
	public static final String KEY_CACHE_TPIPLE = "KEY_CACHE_TPIPLE";
	@Resource
	private PipleDao pipleDao;
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TPIPLE)){
			TimerTask.putClearDailyKey(KEY_CACHE_TPIPLE);
		}
	}
	
    public int deleteByPrimaryKey(String pipleId){
    	redisDao.remove(KEY_CACHE_TPIPLE, pipleId);
    	return pipleDao.deleteByPrimaryKey(pipleId);
    }

	public int insert(TPiple record){
		return pipleDao.insert(record);
	}

	public int insertSelective(TPiple record){
		return pipleDao.insertSelective(record);
	}

	public TPiple selectByPrimaryKey(String pipleId){
		return pipleDao.selectByPrimaryKey(pipleId);
	}

	public int updateByPrimaryKeySelective(TPiple record){
		redisDao.remove(KEY_CACHE_TPIPLE, record.getPipleId());
		return pipleDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TPiple record){
		redisDao.remove(KEY_CACHE_TPIPLE, record.getPipleId());
		return pipleDao.updateByPrimaryKey(record);
	}

    public TPiple selectByPipleName(String pipleName){
    	return pipleDao.selectByPipleName(pipleName);
    }
    
   public List<TPiple> getPipleList(@Param("piple") TPiple piple,@Param("start") int start,@Param("end") int end,@Param("pipleIds") List<String> pipleIds){
	   return pipleDao.getPipleList(piple, start, end, pipleIds);
   }
    
   public int getPipleItems(@Param("piple") TPiple piple,@Param("pipleIds") List<String> pipleIds){
	   return pipleDao.getPipleItems(piple, pipleIds);
   }
    
  public List<TPiple> getAllPiples(){
	  return pipleDao.getAllPiples();
  }
    
  public List<TPiple> getPiplesByUserId(String userId){
	  return pipleDao.getPiplesByUserId(userId);
  }
}