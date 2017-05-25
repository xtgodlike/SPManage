package com.qy.sp.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qy.sp.manage.dao.TSdkPhoneDao;
import com.qy.sp.manage.dao.TSdkconfigCrossDao;
import com.qy.sp.manage.dao.TSdkconfigDao;
import com.qy.sp.manage.dao.TSdkconfigGlobalDao;
import com.qy.sp.manage.dao.TSdkconfigMobileBaseDao;
import com.qy.sp.manage.dao.TSdkconfigPhoneDao;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.TSdkconfig;
import com.qy.sp.manage.dto.TSdkconfigCross;
import com.qy.sp.manage.dto.TSdkconfigCrossKey;
import com.qy.sp.manage.dto.TSdkconfigGlobal;
import com.qy.sp.manage.dto.TSdkconfigMobileBase;
import com.qy.sp.manage.dto.TSdkconfigPhone;

@Service
public class SdkConfigService {
	
	@Resource
	private TSdkconfigDao tSdkconfigDao;
	@Resource
	private TSdkPhoneDao tSdkPhoneDao;
	@Resource
	private TSdkconfigCrossDao tSdkconfigCrossDao;
	@Resource
	private TSdkconfigGlobalDao tSdkconfigGlobalDao;
	@Resource
	private TSdkconfigPhoneDao tSdkconfigPhoneDao;
	@Resource
	private TSdkconfigMobileBaseDao tSdkconfigMobileBaseDao;
	
	public List<TSdkconfig> getSdkConfigList(TSdkconfig sdkconfig,Page page){
		return tSdkconfigDao.getSdkConfigList(sdkconfig, page.getStartItems(), page.getPageSize());
	}
	
	public int getSdkConfigItems(TSdkconfig sdkconfig) {
		return tSdkconfigDao.getSdkConfigItems(sdkconfig);
	}
	
	public TSdkconfig getSdkConfig(String configId){
		return tSdkconfigDao.selectByPrimaryKey(configId);
	}
	
	public void updateSdkConfig(TSdkconfig sdkconfig) throws Exception{
		TSdkconfig sc = tSdkconfigDao.selectByPrimaryKey(sdkconfig.getConfigId());
		if(sc==null){// 新增
			tSdkconfigDao.insert(sdkconfig);
		}else{// 修改
			tSdkconfigDao.updateByPrimaryKey(sdkconfig);
		}
		// 全局配置值不为空，更新全局配置表
		if(!"".equals(sdkconfig.getGlobalConfigValue()) && sdkconfig.getGlobalConfigValue()!=null){
			TSdkconfigGlobal scGlobal = new TSdkconfigGlobal();
			scGlobal.setConfigId(sdkconfig.getConfigId());
			scGlobal.setConfigValue(sdkconfig.getGlobalConfigValue());
			tSdkconfigGlobalDao.deleteByPrimaryKey(sdkconfig.getConfigId());
			tSdkconfigGlobalDao.insert(scGlobal);
		}
	}
	
	public void delSdkConfig(String configId) throws Exception{
		tSdkconfigDao.deleteByPrimaryKey(configId);
		tSdkconfigGlobalDao.deleteByPrimaryKey(configId);
		tSdkconfigPhoneDao.deleteByConfigId(configId);
		tSdkconfigCrossDao.deleteByConfigId(configId);
	}
	
	public List<TSdkconfigCross> getSdkConfigCrossList(TSdkconfigCross sdkconfigCross,Page page){
		return tSdkconfigCrossDao.getSdkConfigCrossList(sdkconfigCross, page.getStartItems(), page.getPageSize());
	}
	
	public int getSdkConfigCrossItems(TSdkconfigCross sdkconfigCross) {
		return tSdkconfigCrossDao.getSdkConfigCrossItems(sdkconfigCross);
	}
	
	public TSdkconfigCross geTSdkconfigCross(TSdkconfigCrossKey key){
		return tSdkconfigCrossDao.selectByPrimaryKey(key);
	}
	public List<TSdkconfigCross> geTSdkconfigChannelAppPiple(TSdkconfigCrossKey key){
		return tSdkconfigCrossDao.selectSelective(key);
	}
	public String updateSdkConfigCross(TSdkconfigCross scc) throws Exception{
		try {
			if("".equals(scc.getAppId()) && "".equals(scc.getChannelId()) && "".equals(scc.getProvinceId()) && "".equals(scc.getPipleId())){ // key选择都为全部
				return "ALL";
			}
			TSdkconfigCrossKey key = new TSdkconfigCrossKey();
			key.setAppId(scc.getAppId());
			key.setChannelId(scc.getChannelId());
			key.setProvinceId(scc.getProvinceId());
			key.setPipleId(scc.getPipleId());
			key.setConfigId(scc.getConfigId());
			TSdkconfigCross nScc = tSdkconfigCrossDao.getByPrimaryKeyForCheck(key);
			if(nScc!=null){ // 已存在包含选择范围的记录
				if(nScc.getAppId().equals(scc.getAppId()) && 
				nScc.getChannelId().equals(scc.getChannelId()) && 
				nScc.getProvinceId().equals(scc.getProvinceId()) &&
				nScc.getPipleId().equals(scc.getPipleId())){ // 如果该次选择的key和查询出的所有key相同，则为同一条配置，执行修改操作
					tSdkconfigCrossDao.updateByPrimaryKey(scc);
					return "UPDATE";
				}else{// 如果该次选择的key和查询出的key有不同。说明数据库中已存在包含该次选择范围的配置项
					return "EXIST";
				}
			}else {
				tSdkconfigCrossDao.insert(scc);
				return "INSERT";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
	
	public void deleteSdkConfigCross(TSdkconfigCrossKey key){
		tSdkconfigCrossDao.deleteByPrimaryKey(key);
	}
	
	public void updateSdkConfigPhones(List<String> imeiList,String configId,String configValue){
		List<String> phoneIds = tSdkPhoneDao.getPhoneIdByIMEIs(imeiList);
		if(phoneIds.size()!=0){
			List<TSdkconfigPhone> scPhones = new ArrayList<TSdkconfigPhone>();
			for (String phoneId : phoneIds) {
				TSdkconfigPhone sPhone = new TSdkconfigPhone();
				sPhone.setConfigId(configId);
				sPhone.setConfigValue(configValue);
				sPhone.setPhoneId(phoneId);
				scPhones.add(sPhone);
			}
			if(phoneIds.size()!=0){
				
			}
			tSdkconfigPhoneDao.deleteBatch(phoneIds, configId);
			tSdkconfigPhoneDao.insertBatch(scPhones);
		}
	}
	
	public void delSdkConfigPhones(List<String> imeiList,String configId){
		List<String> phoneIds = tSdkPhoneDao.getPhoneIdByIMEIs(imeiList);
		if(phoneIds.size()!=0){
			tSdkconfigPhoneDao.deleteBatch(phoneIds, configId);
		}
	}
	
	public List<TSdkconfigMobileBase> getMobileBaseSDkConfig(String appId){
		TSdkconfigMobileBase tSdkconfigMobileBase = new TSdkconfigMobileBase();
		tSdkconfigMobileBase.setAppId(appId);
		return tSdkconfigMobileBaseDao.selectSelective(tSdkconfigMobileBase);
	}
	public String addMobileBaseSDkConfig(TSdkconfigMobileBase tSdkconfigMobileBase){
		TSdkconfigMobileBase temp = tSdkconfigMobileBaseDao.selectByPrimaryKey(tSdkconfigMobileBase);
		if(temp != null){
			return "EXIST";
		}else{
			tSdkconfigMobileBaseDao.insert(tSdkconfigMobileBase);
		}
		return "INSERT";
	}
	public String updateMobileBaseSDkConfig(TSdkconfigMobileBase tSdkconfigMobileBase){
		TSdkconfigMobileBase temp = tSdkconfigMobileBaseDao.selectByPrimaryKey(tSdkconfigMobileBase);
		if(temp != null){
			tSdkconfigMobileBaseDao.updateByPrimaryKeySelective(tSdkconfigMobileBase);
			return "UPDATE";
		}
		return "FAIL";
	}
	public String deleteMobileBaseSDkConfig(TSdkconfigMobileBase tSdkconfigMobileBase){
		tSdkconfigMobileBaseDao.deleteByPrimaryKey(tSdkconfigMobileBase);
		return "DELETE";
	}
}
