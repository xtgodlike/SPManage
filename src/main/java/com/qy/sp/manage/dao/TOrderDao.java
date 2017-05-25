package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.impl.OrderDao;
import com.qy.sp.manage.dto.TOrder;
import com.qy.sp.manage.dto.TOrderExt;
import com.qy.sp.manage.dto.TSdkOperation;

@Component
public class TOrderDao {
	@Resource
	private OrderDao orderDao;
	
	public List<TOrder> getOrderList(TOrder order,int start,int end,List<String> pipleIds,List<String> channelIds){
		return orderDao.getOrderList(order, start, end,pipleIds,channelIds);
	}
	
	public int getOrderItems(TOrder order,List<String> pipleIds,List<String> channelIds){
		return orderDao.getOrderItems(order,pipleIds,channelIds);
	}
	
	public TOrder getOrderInfo(String orderId){
		return orderDao.getOrderInfo(orderId);
	}
	
	public List<TOrderExt> getOrderExtList(String orderId){
		return orderDao.getOrderExtList(orderId);
	}

	public List<TSdkOperation> getOperateInfosByFlowId(String flowId){
		return orderDao.getOperateInfosByFlowId(flowId);
	}
	
	public List<TOrder> getAllOrderList(TOrder order,List<String> pipleIds,List<String> channelIds){
		return orderDao.getAllOrderList(order, pipleIds, channelIds);
	}

}