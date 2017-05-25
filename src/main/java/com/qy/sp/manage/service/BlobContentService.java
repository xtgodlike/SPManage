package com.qy.sp.manage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qy.sp.manage.dao.TBlobContentDao;
import com.qy.sp.manage.dto.TBlobContent;

@Service
public class BlobContentService {
	
	@Resource
	private TBlobContentDao tBlobContentDao;
	
	public TBlobContent getBlobContentById(String fileId) throws Exception{
		return tBlobContentDao.selectByPrimaryKey(fileId);
	}
}
