package com.qy.sp.manage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qy.sp.manage.common.utils.KeyHelper;
import com.qy.sp.manage.dao.TSupplierDao;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.TSupplier;

@Service
public class SupplierService{
	
	@Resource
	private TSupplierDao tSupplierDao;
	
	public List<TSupplier> getAllSuppliers() throws Exception{
		return tSupplierDao.getAllSuppliers();
	}
	
	public int getSupplierItems(TSupplier supplier) throws Exception{
		return tSupplierDao.getSupplierItems(supplier);
	}
	
	public List<TSupplier> getSupplierList(TSupplier supplier,Page page) throws Exception{
		return tSupplierDao.getSupplierList(supplier, page.getStartItems(), page.getPageSize());
	}
	
	public TSupplier getSupplierById(String supplierId) throws Exception{
		return tSupplierDao.selectByPrimaryKey(supplierId);
	}
	
	public TSupplier getSupplierByFullName(String fullName) throws Exception{
		return tSupplierDao.selectByFullName(fullName);
	}
	
	public void updateTSupplier(TSupplier supplier) throws Exception{
		if("".equals(supplier.getSupplierId())){// 新增
			supplier.setSupplierId(KeyHelper.createKey());
			tSupplierDao.insert(supplier);
		}else{// 修改
			tSupplierDao.updateByPrimaryKeySelective(supplier);
		}
	}
	
}
