package com.qy.sp.manage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qy.sp.manage.dao.PriorityDao;
import com.qy.sp.manage.dao.THostDao;
import com.qy.sp.manage.dao.TPipleProvinceDao;
import com.qy.sp.manage.dao.TProvinceDao;
import com.qy.sp.manage.dto.PiplePriority;
import com.qy.sp.manage.dto.THost;
import com.qy.sp.manage.dto.TPipleProvince;
import com.qy.sp.manage.dto.TProvince;

@Service
public class PriorityService {
   
	@Resource
	 private TProvinceDao tProvinceDao; 
	@Resource
	private THostDao tHostDao;
	@Resource
	private PriorityDao priorityDao;
	@Resource
	private TPipleProvinceDao tPipleProvinceDao;
	 
	 public List<TProvince> getTProvinceList(){
		 return tProvinceDao.getProvinceList();
	 }
	 
	 public THost getHostById(Integer hostId){
		 return tHostDao.selectByPrimaryKey(hostId);
	 }
	 
	 public TProvince getProvinceById(Integer provinceId){
		 return tProvinceDao.selectByPrimaryKey(provinceId);
	 }
	 
	 public List<PiplePriority> getPiplePriorityList(PiplePriority piplePriority) {
		 return priorityDao.getPiplePriorityList(piplePriority);
	 }
	 
	 public void updatePiplePrioritys(List<TPipleProvince> pipleProvinces) throws Exception{
		 tPipleProvinceDao.updateBatch(pipleProvinces);
	 }
}
