package com.qy.sp.manage.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.impl.StaSdkDailyDao;
import com.qy.sp.manage.dto.StaSdkDailySearchParam;
import com.qy.sp.manage.dto.TStaSdkDaily;
import com.qy.sp.manage.dto.TStaSdkDailyKey;

@Component @MyBatisRepository
public class TStaSdkDailyDao{
	
	@Resource
	private StaSdkDailyDao staSdkDailyDao;
	
	public int deleteByPrimaryKey(TStaSdkDailyKey key){
		return staSdkDailyDao.deleteByPrimaryKey(key);
	}

    public int insert(TStaSdkDaily record){
    	return staSdkDailyDao.insert(record);
    }

    public int insertSelective(TStaSdkDaily record){
    	return staSdkDailyDao.insertSelective(record);
    }

    public TStaSdkDaily selectByPrimaryKey(TStaSdkDailyKey key){
    	return staSdkDailyDao.selectByPrimaryKey(key);
    }

    public int updateByPrimaryKeySelective(TStaSdkDaily record){
    	return staSdkDailyDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(TStaSdkDaily record){
    	return staSdkDailyDao.updateByPrimaryKey(record);
    }
    
    public  int insertBatch(List<TStaSdkDaily> sdkDailies){
    	return staSdkDailyDao.insertBatch(sdkDailies);
    }

    public List<TStaSdkDaily> getStaSdkDailyList(StaSdkDailySearchParam param){
    	return staSdkDailyDao.getStaSdkDailyList(param);
    }
    
    public int deleteByDate(Date date){
    	return staSdkDailyDao.deleteByDate(date);
    }
}