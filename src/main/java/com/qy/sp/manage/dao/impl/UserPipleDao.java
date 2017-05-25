package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TUserPipleKey;

@Component @MyBatisRepository
public interface UserPipleDao {
    int deleteByPrimaryKey(TUserPipleKey key);
    
    int deleteByUserId(String userId);

    int insert(TUserPipleKey record);

    int insertSelective(TUserPipleKey record);
    
    List<TUserPipleKey> getUserPiples(String userId);
    
    List<String> getPipleIdsByUserId(String userId);
}