package com.qy.sp.manage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qy.sp.manage.dao.TDicDao;
import com.qy.sp.manage.dao.THostDao;
import com.qy.sp.manage.dao.TProvinceDao;
import com.qy.sp.manage.dto.TDic;
import com.qy.sp.manage.dto.THost;
import com.qy.sp.manage.dto.TProvince;

@Service
public class DicService {

	@Resource
	private TDicDao tDicDao;
	
	@Resource
	private THostDao tHostDao;
	@Resource
	private TProvinceDao tProvinceDao;
	
	public List<TDic> getDicsForDTypeId(Integer dtypeId){
		return tDicDao.selectByDTypeId(dtypeId);
	}
	
	public List<THost> getAllHosts(){
		return tHostDao.getAll();
	}
	
	public List<TProvince> getAllProvinces(){
		return tProvinceDao.getAll();
	}
}
