package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TSdkconfigMobileBase;

@Component @MyBatisRepository
public interface SdkconfigMobileBaseDao {
    int deleteByPrimaryKey(TSdkconfigMobileBase record);

    int insert(TSdkconfigMobileBase record);

    int insertSelective(TSdkconfigMobileBase record);

    TSdkconfigMobileBase selectByPrimaryKey(TSdkconfigMobileBase record);
    
    List<TSdkconfigMobileBase> selectSelective(TSdkconfigMobileBase record);

    int updateByPrimaryKeySelective(TSdkconfigMobileBase record);

    int updateByPrimaryKey(TSdkconfigMobileBase record);
}