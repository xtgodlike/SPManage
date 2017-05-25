package com.qy.sp.manage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qy.sp.manage.common.utils.KeyHelper;
import com.qy.sp.manage.dao.TCpDao;
import com.qy.sp.manage.dao.TSdkAppDao;
import com.qy.sp.manage.dao.TUserCPDao;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.TCp;
import com.qy.sp.manage.dto.TSdkApp;
import com.qy.sp.manage.dto.TUserCPKey;

@Service
public class CpService {
	@Resource
	private TCpDao tCpDao;
	@Resource
	private TUserCPDao tUserCPDao;
	@Resource
	private TSdkAppDao tSdkAppDao;
	
	public List<TCp> getAllCps(){
		return tCpDao.getAll();
	}
	
	public int getCpItems(TCp cp) throws Exception{
		return tCpDao.getCpItems(cp);
	}
	
	public List<TCp> getCpList(TCp cp,Page page) throws Exception{
		return tCpDao.getCpList(cp, page.getStartItems(), page.getPageSize());
	}
	
	public TCp getCpById(String cpId) throws Exception{
		return tCpDao.selectByPrimaryKey(cpId);
	}
	
	public TCp getCpByFullName(String fullName) throws Exception{
		return tCpDao.selectByFullName(fullName);
	}
	
	public void updateCp(TCp cp) throws Exception{
		if("".equals(cp.getCpId())){// 新增
			cp.setCpId(KeyHelper.createKey());
			tCpDao.insert(cp);
		}else{// 修改
			tCpDao.updateByPrimaryKeySelective(cp);
		}
	}
	public List<TUserCPKey> getUserCPs(String userId){
		return tUserCPDao.getUserCPs(userId);
	} 
	
	public List<TSdkApp> getUserApps(String userId){
		return tSdkAppDao.getUserApps(userId);
	}
}
