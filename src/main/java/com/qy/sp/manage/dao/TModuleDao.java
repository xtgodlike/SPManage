package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.impl.ModuleDao;
import com.qy.sp.manage.dto.TModule;

@Component 
public class TModuleDao{
	@Resource
	private ModuleDao moduleDao;
	public int deleteByPrimaryKey(String moduleId){
		return moduleDao.deleteByPrimaryKey(moduleId);
	}
    
	public int deleteModuleByParentId(String parentId){
		return moduleDao.deleteModuleByParentId(parentId);
	}
    
	public int insert(TModule record){
		return moduleDao.insert(record);
	}

	public int insertSelective(TModule record){
		return moduleDao.insertSelective(record);
	}

	public TModule selectByPrimaryKey(String moduleId){
		return moduleDao.selectByPrimaryKey(moduleId);
	}

	public int updateByPrimaryKeySelective(TModule record){
		return moduleDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TModule record){
		return moduleDao.updateByPrimaryKey(record);
	}
    
	public List<TModule> loadModuleLimit(@Param("module") TModule module,@Param("start") int start,@Param("end") int end){
		return moduleDao.loadModuleLimit(module, start, end);
	}
    
	public int loadItems(TModule module) {
		return moduleDao.loadItems(module);
	}
    
	public List<TModule> getModuleByUserId(String userId){
		return moduleDao.getModuleByUserId(userId);
	}
}
