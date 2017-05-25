package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TContract;

@Component @MyBatisRepository
public interface ContractDao {
    int deleteByPrimaryKey(String contractId);

    int insert(TContract record);

    int insertSelective(TContract record);

    TContract selectByPrimaryKey(String contractId);

    int updateByPrimaryKeySelective(TContract record);

    int updateByPrimaryKey(TContract record);
    
    List<TContract> getContractListByPipId(String pipleId);
    
    List<TContract> getContractListByChannelId(String channelId);
    
    int updateStatusByPipleId(TContract contract);
    
    int updateStatusByChannelId(TContract contract);
    
}
