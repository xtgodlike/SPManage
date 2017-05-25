package com.qy.sp.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qy.sp.manage.common.utils.Global;
import com.qy.sp.manage.common.utils.KeyHelper;
import com.qy.sp.manage.dao.TChannelDao;
import com.qy.sp.manage.dao.TChannelPipleDao;
import com.qy.sp.manage.dao.TChannelProductDao;
import com.qy.sp.manage.dao.TDicDao;
import com.qy.sp.manage.dao.TProductDao;
import com.qy.sp.manage.dao.TUserChannelDao;
import com.qy.sp.manage.dao.TUserRoleDao;
import com.qy.sp.manage.dto.Page;
import com.qy.sp.manage.dto.TChannel;
import com.qy.sp.manage.dto.TChannelPiple;
import com.qy.sp.manage.dto.TChannelProduct;
import com.qy.sp.manage.dto.TProduct;
import com.qy.sp.manage.dto.TUserChannelKey;
import com.qy.sp.manage.dto.TUserRole;
import com.qy.sp.manage.dto.UserSession;

@Service
public class ChannelService{
	
	@Resource
	private TChannelDao tChannelDao;
	
	@Resource
	private TProductDao tProductDao;
	
	@Resource
	private TChannelProductDao tChannelProductDao;
	
	@Resource
	private TChannelPipleDao tChannelPipleDao;
	
	@Resource 
	private TUserChannelDao tUserChannelDao;
	@Resource
	private TUserRoleDao tUserRoleDao;
	@Resource
	private TDicDao tDicDao;

	public List<TChannel> getAllChannels() throws Exception{
		return tChannelDao.getAllChannels();
	}
	
	public TChannel getChannelById(String channelId) throws Exception{
		return tChannelDao.selectByPrimaryKey(channelId);
	}
	
	public TChannel getChannelByFullName(String fullName) throws Exception{
		return tChannelDao.selectByFullName(fullName);
	}
	
	public int getChannelItems(TChannel channel,UserSession uSession) throws Exception{
		return tChannelDao.getChannelItems(channel,this.getChannelIds(uSession.getUserId()));
	}
	
	public List<TChannel> getChannelList(TChannel channel,Page page,UserSession uSession) throws Exception{
		return tChannelDao.getChannelList(channel, page.getStartItems(), page.getPageSize(),this.getChannelIds(uSession.getUserId()));
	}
	
	public List<TChannel> getChannelsByUserId(String userId){
		List<TUserRole> userRoles = tUserRoleDao.loadRoleByUserId(userId);
		boolean allChannel = false;
		for (TUserRole ur : userRoles) {
			if(ur.getRoleId().equals(Global.Roles_Fixed.CHANNEL_ALL)){
				allChannel = true;
			}
		}
		List<TChannel> channels = null;
		if(allChannel){
			channels = tChannelDao.getAllChannels();
		}else{
			channels = tChannelDao.getChannelsByUserId(userId);
		}
		return channels;
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
		}
		return channelIds;
	}
	
	public void updateChannel(TChannel channel,UserSession uSession) throws Exception{
		if("".equals(channel.getChannelId())){// 新增
			channel.setChannelId(KeyHelper.createKey());
			channel.setApiKey(getChannelApiKey());
			tChannelDao.insert(channel);
			// 初始化产品关系
			List<TProduct> products = tProductDao.getProductList();
			List<TChannelProduct> cProList = new ArrayList<TChannelProduct>();
			for(TProduct p:products){
				TChannelProduct cp = new TChannelProduct();
				cp.setChannelId(channel.getChannelId());
				cp.setProductId(p.getProductId());
				cp.setOpStatus(Global.OpStatus.ON); // 默认开通
				cProList.add(cp);
			}
			tChannelProductDao.insertBatch(cProList);
			// 添加创建者渠道权限
			TUserChannelKey ucKey = new TUserChannelKey();
			ucKey.setChannelId(channel.getChannelId());
			ucKey.setUserId(uSession.getUserId());
			tUserChannelDao.insert(ucKey);
		}else{// 修改
			tChannelDao.updateByPrimaryKeySelective(channel);
		}
	}
	
	public List<TChannelPiple> getChannelPipleList(String channelId)throws Exception{
		return tChannelPipleDao.getListByChannelId(channelId);
	}
	
	public String getChannelApiKey(){
		int seq = tDicDao.getSeqNextvalByType(Global.SeqType.API_KEY);
		return seq+"";
	}
	
}
