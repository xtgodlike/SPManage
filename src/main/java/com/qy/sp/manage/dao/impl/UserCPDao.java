package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TUserCPKey;

@Component @MyBatisRepository
public interface UserCPDao {
    int deleteByPrimaryKey(TUserCPKey key);
    
    int deleteByUserId(String userId);

    int insert(TUserCPKey record);

    int insertSelective(TUserCPKey record);
    
    List<TUserCPKey> getUserCPs(String userId);
    
    List<String> getCPIdsByUserId(String userId);
}