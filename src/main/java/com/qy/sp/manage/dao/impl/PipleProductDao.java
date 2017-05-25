package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TChannelPiple;
import com.qy.sp.manage.dto.TPipleProduct;
import com.qy.sp.manage.dto.TPipleProductKey;

@Component @MyBatisRepository
public interface PipleProductDao {
    int deleteByPrimaryKey(TPipleProductKey key);

    int insert(TPipleProduct record);

    int insertSelective(TPipleProduct record);

    TPipleProduct selectByPrimaryKey(TPipleProductKey key);

    int updateByPrimaryKeySelective(TPipleProduct record);

    int updateByPrimaryKey(TPipleProduct record);
    
    List<TPipleProduct> getPipleProList(String pipleId);
    
    List<TChannelPiple> getChannelPipleList(String pipleId);
    
    int insertBatch(List<TPipleProduct> pipleProducts);
    
    int updateBatch(List<TPipleProduct> pipleProducts);
}