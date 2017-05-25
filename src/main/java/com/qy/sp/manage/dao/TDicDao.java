package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.impl.DicDao;
import com.qy.sp.manage.dto.TDic;

@Component
public class TDicDao{
	@Resource
	private DicDao dicDao;
	
    public int deleteByPrimaryKey(Integer dicId){
    	return dicDao.deleteByPrimaryKey(dicId);
    }

    public int insert(TDic record){
    	return dicDao.insert(record);
    }

    public int insertSelective(TDic record){
    	return dicDao.insertSelective(record);
    }

    public TDic selectByPrimaryKey(Integer dicId){
    	return dicDao.selectByPrimaryKey(dicId);
    }

    public int updateByPrimaryKeySelective(TDic record){
    	return dicDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(TDic record){
    	return dicDao.updateByPrimaryKey(record);
    }
    
    public List<TDic> selectByDTypeId(Integer dtypeId){
    	return dicDao.selectByDTypeId(dtypeId);
    }
    
    public int getSeqCurrvalByType(String seqType){
    	return dicDao.getSeqCurrvalByType(seqType);
    }
    
    public int getSeqNextvalByType(String seqType){
    	return dicDao.getSeqNextvalByType(seqType);
    }
}