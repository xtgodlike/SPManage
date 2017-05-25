package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TUser;

@Component @MyBatisRepository
public interface UserDao {
    int deleteByPrimaryKey(String userId);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);
    
    List<TUser> getUserList(@Param("user") TUser user,@Param("start") int start,@Param("end") int end);
    
    int getUserItems(TUser user);
    
    TUser loadUserByNameAndPwd(@Param("userName") String userName,@Param("password") String password);
    
    int loadItemsByUserAccount(String userAccount);
    
    List<TUser> loadAll();
    
    List<TUser> loadLeaders();
}