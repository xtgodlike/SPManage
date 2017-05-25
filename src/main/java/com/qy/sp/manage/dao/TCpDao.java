package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.impl.CpDao;
import com.qy.sp.manage.dto.TCp;

@Component
public class TCpDao{
	
	@Resource
	private CpDao cpDao;
	
	public int deleteByPrimaryKey(String cpId){
		return cpDao.deleteByPrimaryKey(cpId);
	}

	public int insert(TCp record){
		return cpDao.insert(record);
	}

	public int insertSelective(TCp record){
		return cpDao.insertSelective(record);
	}

	public TCp selectByPrimaryKey(String cpId){
		return cpDao.selectByPrimaryKey(cpId);
	}

	public int updateByPrimaryKeySelective(TCp record){
		return cpDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TCp record){
		return cpDao.updateByPrimaryKey(record);
	}
    
	public List<TCp> getAll(){
		return cpDao.getAll();
	}
    
	public List<TCp> getCpList(@Param("cp") TCp cp,@Param("start") int start,@Param("end") int end){
		return cpDao.getCpList(cp, start, end);
	}
    
	public int getCpItems(TCp cp){
		return cpDao.getCpItems(cp);
	}
    
	public TCp selectByFullName(String fullName){
		return cpDao.selectByFullName(fullName);
	}
}