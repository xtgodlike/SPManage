package com.qy.sp.manage.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.impl.SdkOperStaDao;
import com.qy.sp.manage.dto.TSdkOperSta;
import com.qy.sp.manage.dto.TSdkOperStaKey;
import com.qy.sp.manage.dto.TStaSdkDaily;

@Component
public class TSdkOperStaDao {
	
	@Resource
	private SdkOperStaDao sdkOperStaDao;
	
    public int deleteByPrimaryKey(TSdkOperStaKey key){
    	return sdkOperStaDao.deleteByPrimaryKey(key);
    }

    public int insert(TSdkOperSta record){
    	return sdkOperStaDao.insert(record);
    }

    public int insertSelective(TSdkOperSta record){
    	return sdkOperStaDao.insertSelective(record);
    }

    public TSdkOperSta selectByPrimaryKey(TSdkOperStaKey key){
    	return sdkOperStaDao.selectByPrimaryKey(key);
    }

    public int updateByPrimaryKeySelective(TSdkOperSta record){
    	return sdkOperStaDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(TSdkOperSta record){
    	return sdkOperStaDao.updateByPrimaryKey(record);
    }
    
    public int insertBatch(List<TSdkOperSta> sdkOperStas){
    	return sdkOperStaDao.insertBatch(sdkOperStas);
    }
    
    public  List<TStaSdkDaily> getStaSdkDailyData(String dateStr){
    	return sdkOperStaDao.getStaSdkDailyData(dateStr);
    }
    
    public  int deleteByDate(Date date){
    	return sdkOperStaDao.deleteByDate(date);
    }
}