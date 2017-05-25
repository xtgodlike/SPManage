package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.TModule;

@Component @MyBatisRepository
public interface ModuleDao {
    int deleteByPrimaryKey(String moduleId);
    
    int deleteModuleByParentId(String parentId);
    
    int insert(TModule record);

    int insertSelective(TModule record);

    TModule selectByPrimaryKey(String moduleId);

    int updateByPrimaryKeySelective(TModule record);

    int updateByPrimaryKey(TModule record);
    
    List<TModule> loadModuleLimit(@Param("module") TModule module,@Param("start") int start,@Param("end") int end);
    
    int loadItems(TModule module) ;
    
    List<TModule> getModuleByUserId(String userId);
}
