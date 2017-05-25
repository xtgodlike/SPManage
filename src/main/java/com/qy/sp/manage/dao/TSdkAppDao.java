package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.impl.SdkAppDao;
import com.qy.sp.manage.dto.TSdkApp;

@Component
public class TSdkAppDao{
	
	@Resource
	private SdkAppDao sdkAppDao;
	
	public int deleteByPrimaryKey(String appId){
		return sdkAppDao.deleteByPrimaryKey(appId);
	}

	public int insert(TSdkApp record){
		return sdkAppDao.insert(record);
	}

	public int insertSelective(TSdkApp record){
		return sdkAppDao.insertSelective(record);
	}

	public TSdkApp selectByPrimaryKey(String appId){
		return sdkAppDao.selectByPrimaryKey(appId);
	}

	public int updateByPrimaryKeySelective(TSdkApp record){
		return sdkAppDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(TSdkApp record){
		return sdkAppDao.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(TSdkApp record){
		return sdkAppDao.updateByPrimaryKey(record);
	}
    
	public List<TSdkApp> getAll(){
		return sdkAppDao.getAll();
	}
    
	public List<TSdkApp> getAppList(@Param("app") TSdkApp app,@Param("start") int start,@Param("end") int end){
		return sdkAppDao.getAppList(app, start, end);
	}
	
	public List<TSdkApp> getCpAppList(@Param("app") TSdkApp app,@Param("start") int start,@Param("end") int end, @Param("userId") String userId){
		return sdkAppDao.getCpAppList(app, start, end, userId);
	}
    
	public int getAppItems(TSdkApp app){
		return sdkAppDao.getAppItems(app);
	}
	
	public int getCpAppItems(TSdkApp app, @Param("userId") String userId){
		return sdkAppDao.getCpAppItems(app, userId);
	}
    
	public TSdkApp selectByAppName(String appName){
		return sdkAppDao.selectByAppName(appName);
	}
	public List<TSdkApp> getUserApps(String userId){
		return sdkAppDao.getUserApps(userId);
	}
	
	public TSdkApp selectByPacketName(String packageName){
		return sdkAppDao.selectByPacketName(packageName);
	}
	
	public int updateFileNameByPacketName(TSdkApp app){
		return sdkAppDao.updateFileNameByPacketName(app);
	}
}