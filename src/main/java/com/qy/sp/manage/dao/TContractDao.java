package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.impl.ContractDao;
import com.qy.sp.manage.dto.TContract;

@Component
public class TContractDao{
	
	@Resource
	private ContractDao contractDao;
	
	public int deleteByPrimaryKey(String contractId){
		return contractDao.deleteByPrimaryKey(contractId);
	}

	public int insert(TContract record){
		return contractDao.insert(record);
	}

	public int insertSelective(TContract record){
		return contractDao.insertSelective(record);
	}

	public TContract selectByPrimaryKey(String contractId){
		return contractDao.selectByPrimaryKey(contractId);
	}

	public int updateByPrimaryKeySelective(TContract record){
		return contractDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TContract record){
		return contractDao.updateByPrimaryKey(record);
	}
    
	public List<TContract> getContractListByPipId(String pipleId){
		return contractDao.getContractListByPipId(pipleId);
	}
    
	public List<TContract> getContractListByChannelId(String channelId){
		return contractDao.getContractListByChannelId(channelId);
	}
    
	public int updateStatusByPipleId(TContract contract){
		return contractDao.updateStatusByPipleId(contract);
	}
    
	public int updateStatusByChannelId(TContract contract){
		return contractDao.updateStatusByChannelId(contract);
	}
    
}
