package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.THost;

@Component @MyBatisRepository
public interface HostDao {
    int deleteByPrimaryKey(Integer hostId);

    int insert(THost record);

    int insertSelective(THost record);

    THost selectByPrimaryKey(Integer hostId);
    
    THost selectByName(String hostName);

    int updateByPrimaryKeySelective(THost record);

    int updateByPrimaryKey(THost record);
    
    List<THost> getAll();
}