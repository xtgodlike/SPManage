package com.qy.sp.manage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qy.sp.manage.common.utils.Global;
import com.qy.sp.manage.dao.TBlobContentDao;
import com.qy.sp.manage.dao.TDicDao;
import com.qy.sp.manage.dao.TSdkAppDao;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.TBlobContent;
import com.qy.sp.manage.dto.TSdkApp;

@Service
public class AppService {
	@Resource
	private TSdkAppDao tSdkAppDao;
	@Resource
	private TBlobContentDao tBlobContentDao;
	@Resource
	private TDicDao tDicDao;
	
	public List<TSdkApp> getAllApps(){
		return tSdkAppDao.getAll();
	}
	
	public int getAppItems(TSdkApp app) throws Exception{
		return tSdkAppDao.getAppItems(app);
	}
	
	public int getCpAppItems(TSdkApp app, String userId) throws Exception{
		return tSdkAppDao.getCpAppItems(app, userId);
	}
		
	public List<TSdkApp> getAppList(TSdkApp app,Page page) throws Exception{
		return tSdkAppDao.getAppList(app, page.getStartItems(), page.getPageSize());
	}
	
	public List<TSdkApp> getCpAppList(TSdkApp app,Page page, String userId) throws Exception{
		return tSdkAppDao.getCpAppList(app, page.getStartItems(), page.getPageSize(), userId);
	}
	
	public TSdkApp getApp(String appId){
		return tSdkAppDao.selectByPrimaryKey(appId);
	}
	
	public TSdkApp selectByAppName(String appName){
		return tSdkAppDao.selectByAppName(appName);
	}
	
	public TSdkApp selectByPacketName(String packageName){
		return tSdkAppDao.selectByPacketName(packageName);
	}
	
	public void updateApp(TSdkApp app,TBlobContent bContent) throws Exception{
		TSdkApp oApp = tSdkAppDao.selectByPrimaryKey(app.getAppId());
		if(oApp==null){// 新增
			if(bContent != null){
				tBlobContentDao.insert(bContent);
			}
			tSdkAppDao.insert(app);
			// 初始化产品关系
		}else{ // 修改
			if(bContent!=null && app.getApkId()!=null){
					tBlobContentDao.deleteByPrimaryKey(app.getApkId());
					tBlobContentDao.insert(bContent);
			}
			tSdkAppDao.updateByPrimaryKeyWithBLOBs(app);
		}
	}
	
	public String getAppId(){
		String appIdTitle = "a";
		int seq = tDicDao.getSeqNextvalByType(Global.SeqType.APP_KEY);
		String appId = appIdTitle+seq;
		return appId;
	}
	
}
