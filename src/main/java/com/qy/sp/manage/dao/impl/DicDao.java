package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TDic;

@Component @MyBatisRepository
public interface DicDao {
    int deleteByPrimaryKey(Integer dicId);

    int insert(TDic record);

    int insertSelective(TDic record);

    TDic selectByPrimaryKey(Integer dicId);

    int updateByPrimaryKeySelective(TDic record);

    int updateByPrimaryKey(TDic record);
    
    List<TDic> selectByDTypeId(Integer dtypeId);
    
    int getSeqCurrvalByType(String seqType);
    
    int getSeqNextvalByType(String seqType);
}