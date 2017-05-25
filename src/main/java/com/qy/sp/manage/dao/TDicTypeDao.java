package com.qy.sp.manage.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.impl.DicTypeDao;
import com.qy.sp.manage.dto.TDicType;

@Component
public class TDicTypeDao{
	@Resource
	private DicTypeDao dicTypeDao;
	
    public int deleteByPrimaryKey(Integer dtypeId){
    	return dicTypeDao.deleteByPrimaryKey(dtypeId);
    }

    public int insert(TDicType record){
    	return dicTypeDao.insert(record);
    }

    public int insertSelective(TDicType record){
    	return dicTypeDao.insertSelective(record);
    }

    public TDicType selectByPrimaryKey(Integer dtypeId){
    	return dicTypeDao.selectByPrimaryKey(dtypeId);
    }

    public int updateByPrimaryKeySelective(TDicType record){
    	return dicTypeDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(TDicType record){
    	return dicTypeDao.updateByPrimaryKey(record);
    }
}