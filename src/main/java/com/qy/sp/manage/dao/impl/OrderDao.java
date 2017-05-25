package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TOrder;
import com.qy.sp.manage.dto.TOrderExt;
import com.qy.sp.manage.dto.TSdkOperation;

@Component @MyBatisRepository
public interface OrderDao {

	List<TOrder> getOrderList(@Param("order")TOrder order,@Param("start") int start,@Param("end") int end,@Param("pipleIds") List<String> pipleIds,@Param("channelIds") List<String> channelIds);
	
	int getOrderItems(@Param("order")TOrder order,@Param("pipleIds") List<String> pipleIds,@Param("channelIds") List<String> channelIds);
	
	TOrder getOrderInfo(String orderId);
	
	List<TOrderExt> getOrderExtList(String orderId);

	List<TSdkOperation> getOperateInfosByFlowId(String flowId);
	
	List<TOrder> getAllOrderList(@Param("order")TOrder order,@Param("pipleIds") List<String> pipleIds,@Param("channelIds") List<String> channelIds);
}