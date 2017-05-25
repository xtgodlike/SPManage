package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TCp;

@Component @MyBatisRepository
public interface CpDao {
    int deleteByPrimaryKey(String cpId);

    int insert(TCp record);

    int insertSelective(TCp record);

    TCp selectByPrimaryKey(String cpId);

    int updateByPrimaryKeySelective(TCp record);

    int updateByPrimaryKey(TCp record);
    
    List<TCp> getAll();
    
    List<TCp> getCpList(@Param("cp") TCp cp,@Param("start") int start,@Param("end") int end);
    
    int getCpItems(TCp cp);
    
    TCp selectByFullName(String fullName);
}