package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TSdkPhone;

@Component @MyBatisRepository
public interface SdkPhoneDao {
    int deleteByPrimaryKey(String phoneId);

    int insert(TSdkPhone record);

    int insertSelective(TSdkPhone record);

    TSdkPhone selectByPrimaryKey(String phoneId);

    int updateByPrimaryKeySelective(TSdkPhone record);

    int updateByPrimaryKey(TSdkPhone record);
    
    List<String> getPhoneIdByIMEIs(List<String> imeis);
}