package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TProduct;

@Component @MyBatisRepository
public interface ProductDao {
    int deleteByPrimaryKey(String productId);

    int insert(TProduct record);

    int insertSelective(TProduct record);

    TProduct selectByPrimaryKey(String productId);

    int updateByPrimaryKeySelective(TProduct record);

    int updateByPrimaryKey(TProduct record);
    
    List<TProduct> getProductList();
}