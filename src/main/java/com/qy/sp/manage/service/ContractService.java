package com.qy.sp.manage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qy.sp.manage.common.utils.Global;
import com.qy.sp.manage.common.utils.KeyHelper;
import com.qy.sp.manage.dao.TBlobContentDao;
import com.qy.sp.manage.dao.TChannelDao;
import com.qy.sp.manage.dao.TContractDao;
import com.qy.sp.manage.dao.TPipleDao;
import com.qy.sp.manage.dto.TBlobContent;
import com.qy.sp.manage.dto.TChannel;
import com.qy.sp.manage.dto.TContract;
import com.qy.sp.manage.dto.TPiple;

@Service
public class ContractService{
	
	@Resource
	private TContractDao tContractDao;
	
	@Resource
	private TPipleDao tPipleDao;
	
	@Resource
	private TChannelDao tChannelDao;
	
	@Resource
	private TBlobContentDao tBlobContentDao;

	public List<TContract> getContractListByPipId(String pipleId) throws Exception{
		return tContractDao.getContractListByPipId(pipleId);
	}
	
	public List<TContract> getContractListByChannelId(String channelId) throws Exception{
		return tContractDao.getContractListByChannelId(channelId);
	}
	
	public TContract getContractById(String contractId) throws Exception{
		return tContractDao.selectByPrimaryKey(contractId);
	}
	
	public void updatePipleContract(TPiple piple,TContract contract,TBlobContent blobContent) throws Exception{
		// 合同状态为有效，则置其他合同为无效 并更新通道表contractId
		if(contract.getOpStatus()==Global.OpStatus.ON){
			TContract keyc = new TContract();
			keyc.setPipleId(piple.getPipleId());
			keyc.setOpStatus(Global.OpStatus.OFF);
			tContractDao.updateStatusByPipleId(keyc);
			tPipleDao.updateByPrimaryKeySelective(piple);
		}
		if(contract.getContractId()==null || "".equals(contract.getContractId())){ // 新增
			tBlobContentDao.insert(blobContent);
			contract.setContractId(KeyHelper.createKey());
			tContractDao.insert(contract);
		}else { // 更新
			TContract oc = tContractDao.selectByPrimaryKey(contract.getContractId());
			tBlobContentDao.deleteByPrimaryKey(oc.getFileId());
			tBlobContentDao.insert(blobContent);
			tContractDao.updateByPrimaryKeySelective(contract);
		}
	}
	
	public void updateChannelContract(TChannel channel,TContract contract,TBlobContent blobContent) throws Exception{
		// 合同状态为有效，则置其他合同为无效 并更新通道表contractId
		if(contract.getOpStatus()==Global.OpStatus.ON){
			TContract keyc = new TContract();
			keyc.setChannelId(channel.getChannelId());
			keyc.setOpStatus(Global.OpStatus.OFF);
			tContractDao.updateStatusByChannelId(keyc);
			tChannelDao.updateByPrimaryKeySelective(channel);
		}
		if(contract.getContractId()==null || "".equals(contract.getContractId())){ // 新增
			tBlobContentDao.insert(blobContent);
			contract.setContractId(KeyHelper.createKey());
			tContractDao.insert(contract);
		}else { // 更新
			TContract oc = tContractDao.selectByPrimaryKey(contract.getContractId());
			tBlobContentDao.deleteByPrimaryKey(oc.getFileId());
			tBlobContentDao.insert(blobContent);
			tContractDao.updateByPrimaryKeySelective(contract);
		}
	}
	
}
