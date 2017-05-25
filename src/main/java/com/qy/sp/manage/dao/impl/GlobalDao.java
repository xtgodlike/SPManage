package com.qy.sp.manage.dao.impl;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TGlobal;

@Component @MyBatisRepository
public interface GlobalDao {
    int deleteByPrimaryKey(Integer globalId);

    int insert(TGlobal record);

    int insertSelective(TGlobal record);

    TGlobal selectByPrimaryKey(Integer globalId);

    int updateByPrimaryKeySelective(TGlobal record);

    int updateByPrimaryKey(TGlobal record);
}