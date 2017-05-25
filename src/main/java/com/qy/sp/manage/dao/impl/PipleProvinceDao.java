package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TPipleProvince;
import com.qy.sp.manage.dto.TPipleProvinceKey;

@Component @MyBatisRepository
public interface PipleProvinceDao {
    int deleteByPrimaryKey(TPipleProvinceKey key);
    
    int deleteByPipleId(String pipleId);

    int insert(TPipleProvince record);

    int insertSelective(TPipleProvince record);

    TPipleProvince selectByPrimaryKey(TPipleProvinceKey key);

    int updateByPrimaryKeySelective(TPipleProvince record);

    int updateByPrimaryKey(TPipleProvince record);
    
    int insertBatch(List<TPipleProvince> pipleProvinces);
    
    int updateBatch(List<TPipleProvince> pipleProvinces);
    
    List<TPipleProvince> getPipleProvinceList(String pipleId);
}