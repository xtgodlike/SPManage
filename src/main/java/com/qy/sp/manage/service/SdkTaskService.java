package com.qy.sp.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qy.sp.manage.dao.TBlobContentDao;
import com.qy.sp.manage.dao.TSdkPhoneDao;
import com.qy.sp.manage.dao.TSdktaskCrossDao;
import com.qy.sp.manage.dao.TSdktaskDao;
import com.qy.sp.manage.dao.TSdktaskGlobalDao;
import com.qy.sp.manage.dao.TSdktaskPhoneDao;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.TBlobContent;
import com.qy.sp.manage.dto.TSdktask;
import com.qy.sp.manage.dto.TSdktaskCross;
import com.qy.sp.manage.dto.TSdktaskCrossKey;
import com.qy.sp.manage.dto.TSdktaskGlobal;
import com.qy.sp.manage.dto.TSdktaskGlobalKey;
import com.qy.sp.manage.dto.TSdktaskPhone;

@Service
public class SdkTaskService {

	@Resource
	private TSdktaskDao tSdktaskDao;
	@Resource
	private TSdktaskCrossDao tSdktaskCrossDao;
	@Resource
	private TSdktaskGlobalDao tSdktaskGlobalDao;
	@Resource
	private TSdktaskPhoneDao tSdktaskPhoneDao;
	@Resource
	private TBlobContentDao tBlobContentDao;
	@Resource
	private TSdkPhoneDao tSdkPhoneDao;
	
	public List<TSdktask> getSdkTaskList(TSdktask sdktask,Page page){
		return tSdktaskDao.getSdkTaskList(sdktask,page.getStartItems(), page.getPageSize());
	}
	
	public int getSdkTaskItems(TSdktask sdktask){
		return tSdktaskDao.getSdkTaskItems(sdktask);
	}
	
	public TSdktask getSdkTask(String taskId){
		return tSdktaskDao.selectByPrimaryKey(taskId);
	}
	
	public TSdktask getSdkTaskByName(String taskName){
		return tSdktaskDao.selectByTaskName(taskName);
	}
	
	public void updateSdkTask(TSdktask sdktask,TBlobContent bContent) throws Exception{
		TSdktask oSdktask = tSdktaskDao.selectByPrimaryKey(sdktask.getTaskId());
		if(oSdktask==null){// 新增
			tBlobContentDao.insert(bContent);
			tSdktaskDao.insert(sdktask);
			// 初始化产品关系
		}else{ // 修改
			if(bContent!=null && sdktask.getTaskPlugin()!=null){
					tBlobContentDao.deleteByPrimaryKey(oSdktask.getTaskPlugin());
					tBlobContentDao.insert(bContent);
			}
			tSdktaskDao.updateByPrimaryKey(sdktask);
		}
	}
	
	public void deleteSdkTask(String taskId){
		tSdktaskDao.deleteByPrimaryKey(taskId);
	}
	
	public List<TSdktaskCross> getSdkTaskCrossList(TSdktaskCross sdktaskCross,Page page){
		return tSdktaskCrossDao.getSdkTaskCrossList(sdktaskCross,page.getStartItems(), page.getPageSize());
	}
	
	public int getSdkTaskCrossItems(TSdktaskCross sdktaskCross){
		return tSdktaskCrossDao.getSdkTaskCrossItems(sdktaskCross);
	}
	
	public TSdktaskCross selectByPrimaryKey(TSdktaskCrossKey key){
		return tSdktaskCrossDao.selectByPrimaryKey(key);
	}
	
	public String updateSdkTaskCross(TSdktaskCross stc) throws Exception{
		try {
			if("".equals(stc.getAppId()) && "".equals(stc.getChannelId()) && "".equals(stc.getProvinceId()) && "".equals(stc.getTaskStep())){ // key选择都为全部
				return "ALL";
			}
			TSdktaskCrossKey key = new TSdktaskCrossKey();
			key.setAppId(stc.getAppId());
			key.setChannelId(stc.getChannelId());
			key.setProvinceId(stc.getProvinceId());
			key.setTaskId(stc.getTaskId());
			key.setTaskStep(stc.getTaskStep());
			List<TSdktaskCross> ostcs = tSdktaskCrossDao.getByPrimaryKeyForCheck(key);
			if(ostcs.size()!=0){
				if(ostcs.size()==1){ // 已存在包含选择范围的记录
					TSdktaskCross ostc = ostcs.get(0);
					if(ostc.getAppId().equals(stc.getAppId()) && ostc.getChannelId().equals(stc.getChannelId()) 
						&& ostc.getProvinceId().equals(stc.getProvinceId()) && ostc.getTaskStep().equals(stc.getTaskStep())){ 
						// 情况1：该次选择的key和查询出的所有key相同则为同一条配置，则修改
						tSdktaskCrossDao.updateByPrimaryKey(stc);
						return "UPDATE";
					}else if(ostc.getTaskExecute()==0 && stc.getTaskExecute()==1){ 
						// 情况2：该次选择的key和查询出的key有不同。说明数据库中已存在包含该次选择范围的配置项且为不执行状态。则不能再添加执行配置
						return "UNEXECUTE";
					}else if(ostc.getTaskExecute()==1 && stc.getTaskExecute()==0){
						// 情况3：该次选择的key和查询出的key有不同。说明数据库中已存在包含该次选择范围的配置项且为执行状态。则可以添加不执行配置
						tSdktaskCrossDao.insert(stc);
						return "INSERT";
					}else{
						// 情况3：该次选择的key和查询出的key有不同。说明数据库中已存在包含该次选择范围的配置项。不能存在执行状态一样的情况。操作报错。
						return "EXIST";
					}
				}else{
					// 情况4：该次选择的key查询出2条记录，则已存在包含该次选择范围的两种执行状态的配置项。操作报错。
					return "EXIST";
				}
			}else {
				// 情况5：该次选择的key在系统中不存在，则新增
				tSdktaskCrossDao.insert(stc);
				return "INSERT";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
	
	public void deleteSdkTaskCross(TSdktaskCrossKey key){
		tSdktaskCrossDao.deleteByPrimaryKey(key);
	}
	
	public List<TSdktaskGlobal> getSdkTaskGlobalList(TSdktaskGlobal sdktaskGlobal,Page page){
		return tSdktaskGlobalDao.getSdkTaskGlobalList(sdktaskGlobal,page.getStartItems(), page.getPageSize());
	}
	
	public int getSdkTaskGlobalItems(TSdktaskGlobal sdktaskGlobal){
		return tSdktaskGlobalDao.getSdkTaskGlobalItems(sdktaskGlobal);
	}
	
	public TSdktaskGlobal getSdktaskGlobal(String taskId){
		return tSdktaskGlobalDao.selectByTaskId(taskId);
	}
	
	public void updateSdkTaskGlobal(TSdktaskGlobal sdktaskGlobal) throws Exception{
		TSdktaskGlobalKey key = new TSdktaskGlobalKey();
		key.setTaskId(sdktaskGlobal.getTaskId());
		key.setTaskStep(sdktaskGlobal.getTaskStep());
		TSdktaskGlobal oGlobal = tSdktaskGlobalDao.selectByPrimaryKey(key);
		if(oGlobal==null){// 新增
			tSdktaskGlobalDao.insert(sdktaskGlobal);
		}else{ // 修改
			tSdktaskGlobalDao.updateByPrimaryKey(sdktaskGlobal);
		}
	}
	
	public void deleteSdkTaskGlobal(TSdktaskGlobalKey key){
		tSdktaskGlobalDao.deleteByPrimaryKey(key);
	}
	
	public void updateSdkTaskPhones(List<String> imeiList,String taskId,String taskStep,Integer taskExecute){
		List<String> phoneIds = tSdkPhoneDao.getPhoneIdByIMEIs(imeiList);
		if(phoneIds.size()!=0){
			List<TSdktaskPhone> stPhones = new ArrayList<TSdktaskPhone>();
			for (String phoneId : phoneIds) {
				TSdktaskPhone sPhone = new TSdktaskPhone();
				sPhone.setTaskId(taskId);
				sPhone.setTaskStep(taskStep);
				sPhone.setPhoneId(phoneId);
				sPhone.setTaskExecute(taskExecute);
				stPhones.add(sPhone);
			}
			tSdktaskPhoneDao.deleteBatch(phoneIds, taskId);
			tSdktaskPhoneDao.insertBatch(stPhones);
		}
	}
	
	public void delSdkTaskPhones(List<String> imeiList,String taskId){
		List<String> phoneIds = tSdkPhoneDao.getPhoneIdByIMEIs(imeiList);
		if(phoneIds.size()!=0){
			tSdktaskPhoneDao.deleteBatch(phoneIds, taskId);
		}
		
	}
}
