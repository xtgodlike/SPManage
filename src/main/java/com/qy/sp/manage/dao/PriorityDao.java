package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.impl.PriorityDataDao;
import com.qy.sp.manage.dto.PiplePriority;


@Component 
public class PriorityDao {
	@Resource
	private PriorityDataDao priorityDataDao;
	
		public List<PiplePriority> getPiplePriorityList(PiplePriority piplePriority){
			return priorityDataDao.getPiplePriorityList(piplePriority);
		}
		
}
