package com.qy.sp.manage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qy.sp.manage.common.utils.Global;
import com.qy.sp.manage.dao.TOrderDao;
import com.qy.sp.manage.dao.TPipleDao;
import com.qy.sp.manage.dao.TUserChannelDao;
import com.qy.sp.manage.dao.TUserPipleDao;
import com.qy.sp.manage.dao.TUserRoleDao;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.TOrder;
import com.qy.sp.manage.dto.TOrderExt;
import com.qy.sp.manage.dto.TPiple;
import com.qy.sp.manage.dto.TSdkOperation;
import com.qy.sp.manage.dto.TUserRole;

@Service
public class ControlService{
	
	@Resource
	private TOrderDao tOrderDao;
	@Resource
	private TUserRoleDao tUserRoleDao;
	@Resource
	private TPipleDao tPipleDao;
	@Resource
	private TUserPipleDao tUserPipleDao;
	@Resource
	private TUserChannelDao tUserChannelDao;
	
	public List<TOrder> getOrderList(TOrder order,Page page,String userId){
		return tOrderDao.getOrderList(order, page.getStartItems(), page.getPageSize(),this.getPipleIds(userId),this.getChannelIds(userId));
	}
	
	public List<TOrder> getAllOrderList(TOrder order,String userId){
		return tOrderDao.getAllOrderList(order, this.getPipleIds(userId),this.getChannelIds(userId));
	}
	
	public int getOrderItems(TOrder order,String userId){
		return tOrderDao.getOrderItems(order,this.getPipleIds(userId),this.getChannelIds(userId));
	}
	
	public TOrder getOrderInfo(String orderId){
		return tOrderDao.getOrderInfo(orderId);
	}
	
	public List<TOrderExt> getOrderExtList(String orderId){
		return tOrderDao.getOrderExtList(orderId);
	}
	
	public List<TSdkOperation> getOperatesByFlowId(String flowId){
		return tOrderDao.getOperateInfosByFlowId(flowId);
	}
	
	public List<String> getPipleIds(String userId){
		List<TUserRole> userRoles = tUserRoleDao.loadRoleByUserId(userId);
		boolean allPiple = false;
		for (TUserRole ur : userRoles) {
			if(ur.getRoleId().equals(Global.Roles_Fixed.PIPLE_ALL)){
				allPiple = true;
			}
		}
		List<String> pipleIds = null;
		if(!allPiple){
			pipleIds = tUserPipleDao.getPipleIdsByUserId(userId);
			if(pipleIds.size()==0) 
				pipleIds = null;
		}
		return pipleIds;
	}
	
	public List<String> getChannelIds(String userId){
		List<TUserRole> userRoles = tUserRoleDao.loadRoleByUserId(userId);
		boolean allChannel = false;
		for (TUserRole ur : userRoles) {
			if(ur.getRoleId().equals(Global.Roles_Fixed.CHANNEL_ALL)){
				allChannel = true;
			}
		}
		List<String> channelIds = null;
		if(!allChannel){
			channelIds = tUserChannelDao.getChannelIdsByUserId(userId);
			if(channelIds.size()==0) 
				channelIds = null;
		}
		return channelIds;
	}
	
}
