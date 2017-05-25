package com.qy.sp.manage.dao.impl;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TPipleMobileLimit;

@Component @MyBatisRepository
public interface PipleMobileLimitDao {
    int deleteByPrimaryKey(String pipleId);

    int insert(TPipleMobileLimit record);

    int insertSelective(TPipleMobileLimit record);

    TPipleMobileLimit selectByPrimaryKey(String pipleId);

    int updateByPrimaryKeySelective(TPipleMobileLimit record);

    int updateByPrimaryKey(TPipleMobileLimit record);
}