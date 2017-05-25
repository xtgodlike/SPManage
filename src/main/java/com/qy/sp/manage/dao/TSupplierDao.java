package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.impl.SupplierDao;
import com.qy.sp.manage.dto.TSupplier;

@Component
public class TSupplierDao{
	@Resource
	private SupplierDao supplierDao;
	
	public int deleteByPrimaryKey(String supplierId){
		return supplierDao.deleteByPrimaryKey(supplierId);
	}

	public int insert(TSupplier record){
		return supplierDao.insert(record);
	}

	public int insertSelective(TSupplier record){
		return supplierDao.insertSelective(record);
	}

	public TSupplier selectByPrimaryKey(String supplierId){
		return supplierDao.selectByPrimaryKey(supplierId);
	}

	public int updateByPrimaryKeySelective(TSupplier record){
		return supplierDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TSupplier record){
		return supplierDao.updateByPrimaryKey(record);
	}
    
	public List<TSupplier> getAllSuppliers(){
		return supplierDao.getAllSuppliers();
	}
    
	public List<TSupplier> getSupplierList(@Param("supplier") TSupplier supplier,@Param("start") int start,@Param("end") int end){
		return supplierDao.getSupplierList(supplier, start, end);
	}
    
	public int getSupplierItems(TSupplier supplier){
		return supplierDao.getSupplierItems(supplier);
	}
    
	public TSupplier selectByFullName(String fullName){
		return supplierDao.selectByFullName(fullName);
	}
}