package com.qy.sp.manage.dao.impl;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TBlobContent;

@Component @MyBatisRepository
public interface BlobContentDao {
    int deleteByPrimaryKey(String fileId);

    int insert(TBlobContent record);

    int insertSelective(TBlobContent record);

    TBlobContent selectByPrimaryKey(String fileId);

    int updateByPrimaryKeySelective(TBlobContent record);

    int updateByPrimaryKeyWithBLOBs(TBlobContent record);

    int updateByPrimaryKey(TBlobContent record);
}