package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TSupplier;

@Component @MyBatisRepository
public interface SupplierDao {
    int deleteByPrimaryKey(String supplierId);

    int insert(TSupplier record);

    int insertSelective(TSupplier record);

    TSupplier selectByPrimaryKey(String supplierId);

    int updateByPrimaryKeySelective(TSupplier record);

    int updateByPrimaryKey(TSupplier record);
    
    List<TSupplier> getAllSuppliers();
    
    List<TSupplier> getSupplierList(@Param("supplier") TSupplier supplier,@Param("start") int start,@Param("end") int end);
    
    int getSupplierItems(TSupplier supplier);
    
    TSupplier selectByFullName(String fullName);
}