package com.qy.sp.manage.hao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.qy.sp.manage.common.utils.HttpClientUtils;
import com.qy.sp.manage.dao.THostDao;
import com.qy.sp.manage.dao.TProvinceDao;
import com.qy.sp.manage.dto.THost;
import com.qy.sp.manage.dto.TLocation;
import com.qy.sp.manage.dto.TProvince;

@Repository
public class MobileSegmentHao {
	private Logger log = Logger.getLogger(MobileSegmentHao.class);
	@Resource
	private TProvinceDao tProvinceDao;
	@Resource
	private THostDao tHostDao;
	
	public TLocation getProvinceIdByMobile(String mobile){
		Map<String, String> seMap = new HashMap<String, String>();
		TLocation newLocation = new TLocation();
		seMap.put("tel", mobile);
//		String rs = RequestUtil.sendPost(apiPath, "tel="+mobile, "GB2312");
		String apiPath = "";
		try {
			String rs = HttpClientUtils.doPost(apiPath, seMap,HttpClientUtils.GBK) ;
			String jsonStr = rs.replaceAll("__GetZoneResult_ = ", "");
			JSONObject jsonObj = new JSONObject(jsonStr);
			String provinceName = null;
			String hostName = null;
			if(jsonObj.has("province")){
				provinceName = jsonObj.getString("province");
				TProvince province = this.tProvinceDao.selectByProvinceName(provinceName);
				log.info("PROVINCE_NAME:" + provinceName + ";");
				if(null == province){
					return null;
				}
				newLocation.setProvinceId(province.getProvinceId());
			}
			if(jsonObj.has("catName")){
				hostName = jsonObj.getString("catName");
				THost host = tHostDao.selectByName(hostName);
				newLocation.setHostId(host.getHostId());
			}
			return newLocation;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}

}
