package com.qy.sp.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qy.sp.manage.dao.TMobileBlacklistDao;
import com.qy.sp.manage.dao.base.RedisDao;
import com.qy.sp.manage.dto.TMobileBlacklist;

@Service
public class LimitService {
	@Resource
	private TMobileBlacklistDao tMobileBlacklistDao;
	@Resource
	private RedisDao redisDao;
	
	public String updateMoblieBlackList(List<String> mobiles,String opType){
		if("ADD".equals(opType)){//添加
			List<TMobileBlacklist> mbLists = new ArrayList<TMobileBlacklist>();
			for (String mobile : mobiles) {
				TMobileBlacklist mb = new TMobileBlacklist();
				mb.setMobile(mobile);
				mb.setBatchId("1001"); // 默认
				mbLists.add(mb);
			}
			tMobileBlacklistDao.deleteBatch(mobiles);
			tMobileBlacklistDao.insertBatch(mbLists);
			return "ADD";
		}else{// 移除
			tMobileBlacklistDao.deleteBatch(mobiles);
			return "DEL";
		}
	}
	
	public void deleteMoblieBlackList(List<String> mobiles){
		tMobileBlacklistDao.deleteBatch(mobiles);
	}
}
