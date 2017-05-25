package com.qy.sp.manage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qy.sp.manage.dto.TLocation;
import com.qy.sp.manage.hao.MobileSegmentHao;

@Service
public class MobileSegmentService {
	private static List<String> mobiles = new ArrayList<String>() ;
	
	@Resource
	private MobileSegmentHao mobileSegmentHao;
	
	
	
	public void setMobileSegmentHao(MobileSegmentHao mobileSegmentHao) {
		this.mobileSegmentHao = mobileSegmentHao;
	}

	public static synchronized void addMobile(String mobile){
		mobiles.add(mobile);
	}
	
	public static synchronized void addMobile(List<String> mobile){
		mobiles.addAll(mobile);
	}
	
	public boolean addMobileSegment(){
		List<String> temp = mobiles;
		mobiles = new ArrayList<String>();
		Map<String, Integer> temp2 = new HashMap<String, Integer>();
		try {
			for(String mobile : temp){
				String seg = mobile.substring(0,7);
				if(null == temp2.get(seg)){
					TLocation location = this.mobileSegmentHao.getProvinceIdByMobile(mobile);
					temp2.put(seg, location.getProvinceId());
					//TODO:DAO SAVE Segment:Provinceid
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean addMobileSegmentWithOrders(){
		List<String> mobile = new ArrayList<String>();
		//TODO:
		
		
		MobileSegmentService.addMobile(mobile);
		return this.addMobileSegment();	 
	}
	
	public String getProvinceIdByMobile(String mobile, boolean bMust){
		//从数据库号段表 获取分省ID,
//		String provinceId =
		//如号码不存在，
			//判断bMust,如果bMust是false，则返回0即可。
			//如果bMust是true，则调用HAO获取分省。
		return "0";
	}
}
