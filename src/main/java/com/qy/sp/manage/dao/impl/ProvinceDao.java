package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TProvince;

@Component @MyBatisRepository
public interface ProvinceDao {
    int deleteByPrimaryKey(Integer provinceId);

    int insert(TProvince record);

    int insertSelective(TProvince record);

    TProvince selectByPrimaryKey(Integer provinceId);
    
    TProvince selectByProvinceName(String provinceName);

    int updateByPrimaryKeySelective(TProvince record);

    int updateByPrimaryKey(TProvince record);
    
    List<TProvince> getProvinceList();
    
    List<TProvince> getAll();
}