package com.qy.sp.manage.dao.impl;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TLocation;
import com.qy.sp.manage.dto.TLocationKey;

@Component @MyBatisRepository
public interface LocationDao {
    int deleteByPrimaryKey(TLocationKey key);

    int insert(TLocation record);

    int insertSelective(TLocation record);

    TLocation selectByPrimaryKey(TLocationKey key);

    int updateByPrimaryKeySelective(TLocation record);

    int updateByPrimaryKey(TLocation record);
}