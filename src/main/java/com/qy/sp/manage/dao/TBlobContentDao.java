package com.qy.sp.manage.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.BlobContentDao;
import com.qy.sp.manage.dto.TBlobContent;

@Component
public class TBlobContentDao extends BaseDao{
	public static final String KEY_CACHE_TBLOBCONTENT= "KEY_CACHE_TBLOBCONTENT";
	@Resource
	private BlobContentDao blobContentDao;
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TBLOBCONTENT)){
			TimerTask.putClearDailyKey(KEY_CACHE_TBLOBCONTENT);
		}
	}
    public int deleteByPrimaryKey(String fileId){
         redisDao.remove(KEY_CACHE_TBLOBCONTENT, fileId);
    	return blobContentDao.deleteByPrimaryKey(fileId);
    }

    public int insert(TBlobContent record){
    	return blobContentDao.insert(record);
    }

    public int insertSelective(TBlobContent record){
    	return blobContentDao.insertSelective(record);
    }

    public TBlobContent selectByPrimaryKey(String fileId){
    	return blobContentDao.selectByPrimaryKey(fileId);
    }

    public int updateByPrimaryKeySelective(TBlobContent record){
    	 redisDao.remove(KEY_CACHE_TBLOBCONTENT, record.getFileId());
    	return blobContentDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKeyWithBLOBs(TBlobContent record){
    	 redisDao.remove(KEY_CACHE_TBLOBCONTENT, record.getFileId());
    	return blobContentDao.updateByPrimaryKeyWithBLOBs(record);
    }

    public int updateByPrimaryKey(TBlobContent record){
    	 redisDao.remove(KEY_CACHE_TBLOBCONTENT, record.getFileId());
    	return blobContentDao.updateByPrimaryKey(record);
    }
}