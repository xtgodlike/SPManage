package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TPiple;

@Component @MyBatisRepository
public interface PipleDao {
    int deleteByPrimaryKey(String pipleId);

	int insert(TPiple record);

	int insertSelective(TPiple record);

	TPiple selectByPrimaryKey(String pipleId);

	int updateByPrimaryKeySelective(TPiple record);

	int updateByPrimaryKey(TPiple record);

    TPiple selectByPipleName(String pipleName);
    
    List<TPiple> getPipleList(@Param("piple") TPiple piple,@Param("start") int start,@Param("end") int end,@Param("pipleIds") List<String> pipleIds);
    
    int getPipleItems(@Param("piple") TPiple piple,@Param("pipleIds") List<String> pipleIds);
    
    List<TPiple> getAllPiples();
    
    List<TPiple> getPiplesByUserId(String userId);
    
}