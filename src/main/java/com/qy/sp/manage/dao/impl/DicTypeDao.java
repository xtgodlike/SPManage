package com.qy.sp.manage.dao.impl;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TDicType;

@Component @MyBatisRepository
public interface DicTypeDao {
    int deleteByPrimaryKey(Integer dtypeId);

    int insert(TDicType record);

    int insertSelective(TDicType record);

    TDicType selectByPrimaryKey(Integer dtypeId);

    int updateByPrimaryKeySelective(TDicType record);

    int updateByPrimaryKey(TDicType record);
}