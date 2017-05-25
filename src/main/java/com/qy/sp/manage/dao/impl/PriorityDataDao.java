package com.qy.sp.manage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.MyBatisRepository;
import com.qy.sp.manage.dto.PiplePriority;


@Component @MyBatisRepository
public interface PriorityDataDao {

		public List<PiplePriority> getPiplePriorityList(PiplePriority piplePriority);
		
}
