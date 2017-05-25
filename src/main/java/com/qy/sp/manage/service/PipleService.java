package com.qy.sp.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qy.sp.manage.common.utils.DateTimeUtils;
import com.qy.sp.manage.common.utils.Global;
import com.qy.sp.manage.dao.TBlobContentDao;
import com.qy.sp.manage.dao.TChannelPipleDao;
import com.qy.sp.manage.dao.TChannelProvinceLimitDao;
import com.qy.sp.manage.dao.TDicDao;
import com.qy.sp.manage.dao.TPipleDao;
import com.qy.sp.manage.dao.TPipleMobileLimitDao;
import com.qy.sp.manage.dao.TPipleProductDao;
import com.qy.sp.manage.dao.TPipleProvinceDao;
import com.qy.sp.manage.dao.TProductDao;
import com.qy.sp.manage.dao.TProvinceDao;
import com.qy.sp.manage.dao.TUserPipleDao;
import com.qy.sp.manage.dao.TUserRoleDao;
import com.qy.sp.manage.dao.base.RedisDao;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.TBlobContent;
import com.qy.sp.manage.dto.TChannelPiple;
import com.qy.sp.manage.dto.TChannelPipleKey;
import com.qy.sp.manage.dto.TChannelProvinceLimit;
import com.qy.sp.manage.dto.TChannelProvinceLimitKey;
import com.qy.sp.manage.dto.TPiple;
import com.qy.sp.manage.dto.TPipleMobileLimit;
import com.qy.sp.manage.dto.TPipleProduct;
import com.qy.sp.manage.dto.TPipleProvince;
import com.qy.sp.manage.dto.TProduct;
import com.qy.sp.manage.dto.TProvince;
import com.qy.sp.manage.dto.TUserPipleKey;
import com.qy.sp.manage.dto.TUserRole;
import com.qy.sp.manage.dto.UserSession;

@Service
public class PipleService{
	@Resource
	private TPipleDao tPipleDao;
	@Resource
	private TBlobContentDao tBlobContentDao;
	@Resource
	private TPipleProductDao tPipleProductDao;
	@Resource
	private TChannelPipleDao tChannelPipleDao;
	@Resource
	private TPipleProvinceDao tPipleProvinceDao;
	@Resource
	private TProvinceDao tProvinceDao;
	@Resource
	private TProductDao tProductDao;
	@Resource
	private TDicDao tDicDao;
	@Resource
	private TUserPipleDao tUserPipleDao;
	@Resource
	private TUserRoleDao tUserRoleDao;
	@Resource
	private TChannelProvinceLimitDao tChannelProvinceLimitDao;
	@Resource
	private TPipleMobileLimitDao tPipleMobileLimitDao;
	@Resource
	private RedisDao redisDao;
	
	public List<TPiple> getPipleList(TPiple piple,Page page,UserSession uSession) throws Exception{
		return tPipleDao.getPipleList(piple, page.getStartItems(), page.getPageSize(),this.getPipleIds(uSession.getUserId()));
	}
	
	public int getPipleItems(TPiple piple,UserSession uSession){
		return tPipleDao.getPipleItems(piple,this.getPipleIds(uSession.getUserId()));
	};
	
	public List<TPiple> getPiplesByUserId(String userId){
		List<TUserRole> userRoles = tUserRoleDao.loadRoleByUserId(userId);
		boolean allPiple = false;
		for (TUserRole ur : userRoles) {
			if(ur.getRoleId().equals(Global.Roles_Fixed.PIPLE_ALL)){
				allPiple = true;
			}
		}
		List<TPiple> piples = null;
		if(allPiple){
			piples = tPipleDao.getAllPiples();
		}else{
			piples = tPipleDao.getPiplesByUserId(userId);
		}
		return piples;
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
		}
		return pipleIds;
	}
	
	public List<TPiple> getAllPiples() throws Exception{
		return tPipleDao.getAllPiples();
	}
	
	public void updatePiple(TPiple piple,List<TBlobContent> bContents,UserSession uSession) throws Exception{
		TPiple oPiple = tPipleDao.selectByPrimaryKey(piple.getPipleId());
		if(oPiple==null){// 新增
			for(TBlobContent bc:bContents){
				tBlobContentDao.insert(bc);
			}
			piple.setPipleNumber(this.getPipleNumber(piple.getCalcType()));
			tPipleDao.insert(piple);
			// 初始化产品关系
			List<TProduct> products = tProductDao.getProductList();
			List<TPipleProduct> pProList = new ArrayList<TPipleProduct>();
			for(TProduct p:products){
				TPipleProduct pPro = new TPipleProduct();
				pPro.setPipleId(piple.getPipleId());
				pPro.setProductId(p.getProductId());
				pPro.setPipleProductCode(null); // 默认为空
				pPro.setOpStatus(Global.OpStatus.OFF); // 默认关闭
				pProList.add(pPro);
			}
			tPipleProductDao.insertBatch(pProList);
			// 初始化省份关系
			List<TProvince> provinces = tProvinceDao.getAll();
			List<TPipleProvince> ppList = new ArrayList<TPipleProvince>();
			for (TProvince p : provinces) {
				if(p.getProvinceId()!=0){
					TPipleProvince pp = new TPipleProvince();
					pp.setPipleId(piple.getPipleId());
					pp.setProvinceId(p.getProvinceId());
					pp.setPriority(0); // 优先级默认为0
					pp.setOpStatus(Global.OpStatus.OFF);  // 默认关闭
					ppList.add(pp);
				}
			}
			tPipleProvinceDao.insertBatch(ppList);
			// 添加创建者通道权限
			TUserPipleKey upKey = new TUserPipleKey();
			upKey.setPipleId(piple.getPipleId());
			upKey.setUserId(uSession.getUserId());
			tUserPipleDao.insert(upKey);
			// 添加用户日月限
			TPipleMobileLimit pipleMobileLimit = new TPipleMobileLimit();
			pipleMobileLimit.setPipleId(piple.getPipleId());
			tPipleMobileLimitDao.insert(pipleMobileLimit);
			
		}else{ // 修改
			if(bContents.size()!=0){
				if(piple.getPipleDoc()!=null){
					tBlobContentDao.deleteByPrimaryKey(oPiple.getPipleDoc());
				}
				if(piple.getChannelDoc()!=null){
					tBlobContentDao.deleteByPrimaryKey(oPiple.getChannelDoc());
				}
				if(piple.getPluginId()!=null){
					tBlobContentDao.deleteByPrimaryKey(oPiple.getPluginId());
				}
				if(piple.getTestPluginId()!=null){
					tBlobContentDao.deleteByPrimaryKey(oPiple.getTestPluginId());
				}
				for(TBlobContent bc:bContents){
					tBlobContentDao.insert(bc);
				}
			}
			if(piple.getPipleNumber()==null || "".equals(piple.getPipleNumber())){
				piple.setPipleNumber(this.getPipleNumber(piple.getCalcType()));
			}
			piple.setModTime(DateTimeUtils.getCurrentTime());
			tPipleDao.updateByPrimaryKeySelective(piple);
		}
	}
	
	public TPiple getPipleByPipleId(String pipleId) throws Exception{
		return tPipleDao.selectByPrimaryKey(pipleId);
	}
	
	public TPiple getPipleByPipleName(String pipleName) throws Exception{
		return tPipleDao.selectByPipleName(pipleName);
	}
	
	public List<TPipleProduct> getPipleProList(String pipleId) throws Exception{
		return tPipleProductDao.getPipleProList(pipleId);
	}
	
	public List<TChannelPiple> getChannelPipleList(String pipleId) throws Exception{
		return tChannelPipleDao.getListByPipleId(pipleId); 
	}
	
	public TChannelPiple getChannelPipleByKey(TChannelPipleKey key) throws Exception{
		return tChannelPipleDao.selectByPrimaryKey(key);
	}
	
	public void deleteChannelPipleByKey(TChannelPipleKey key) throws Exception{
		tChannelPipleDao.deleteByPrimaryKey(key);
	}
	
	public void updateChannelPiple(TChannelPiple channelPiple) throws Exception{
		TChannelPipleKey key = new TChannelPipleKey();
		key.setChannelId(channelPiple.getChannelId());
		key.setPipleId(channelPiple.getPipleId());
		TChannelPiple cPiple = tChannelPipleDao.selectByPrimaryKey(key);
		if(cPiple==null){
			tChannelPipleDao.insert(channelPiple);
		}else{
			tChannelPipleDao.updateByPrimaryKeySelective(channelPiple);
		}
	}
	
	public void updatePiplePros(List<TPipleProduct> pipleProducts) throws Exception{
		tPipleProductDao.updateBatch(pipleProducts);
	}
	
	public List<TPipleProvince> getPipleProvinceList(String pipleId) throws Exception{
		return tPipleProvinceDao.getPipleProvinceList(pipleId);
	}
	
	public void updatePipleProvince(String pipleId,List<TPipleProvince> pipleProvinces) throws Exception{
//		tPipleProvinceDao.deleteByPipleId(pipleId);
//		if(pipleProvinces.size()!=0){
//			tPipleProvinceDao.insertBatch(pipleProvinces);
//		}
		tPipleProvinceDao.updateBatch(pipleProvinces);
	}
	
	public String getPipleNumber(int calcType){
		String numTitle = "P";
		String numType = "";
		int seq = tDicDao.getSeqNextvalByType(Global.SeqType.PIPLE_NUMBER_KEY);
		if(calcType==Global.PipleCalcType.WEEK){
			numType = "W";
		}else if(calcType==Global.PipleCalcType.C_MONTH || calcType==Global.PipleCalcType.CC_MONTH){
			numType = "M";
		}else if(calcType==Global.PipleCalcType.ARRIVAL){
			numType = "A";
		}else if(calcType==Global.PipleCalcType.SEEBILL){
			numType = "S";
		}
		String pipleNum = numTitle+numType+seq;
		return pipleNum;
	}
	
	public List<TChannelProvinceLimit> getListByPipleIdAndChannelId(TChannelProvinceLimitKey key){
		return tChannelProvinceLimitDao.getListByPipleIdAndChannelId(key);
	}
	
	public void updateChannelProvinceLimits(List<TChannelProvinceLimit> data,boolean isExists){
		if(isExists){
			tChannelProvinceLimitDao.updateBatch(data);
		}else{
			tChannelProvinceLimitDao.insertBatch(data);
		}
	}
	
	public TPipleMobileLimit getPipleMobileLimit(String pipleId){
		return tPipleMobileLimitDao.selectByPrimaryKey(pipleId);
	}
	
	public void updatePipleMobileLimit(TPipleMobileLimit pipleMobileLimit){
		TPipleMobileLimit pml = tPipleMobileLimitDao.selectByPrimaryKey(pipleMobileLimit.getPipleId());
		if(pml==null){
			tPipleMobileLimitDao.insert(pipleMobileLimit);
		}else{
			tPipleMobileLimitDao.updateByPrimaryKey(pipleMobileLimit);
		}
	}
	
}
