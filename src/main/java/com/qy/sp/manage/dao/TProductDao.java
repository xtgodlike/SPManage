package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qy.sp.manage.controller.TimerTask;
import com.qy.sp.manage.dao.base.BaseDao;
import com.qy.sp.manage.dao.impl.ProductDao;
import com.qy.sp.manage.dto.TProduct;

@Component
public class TProductDao extends BaseDao{
	public static final String KEY_CACHE_TPRODUCT = "KEY_CACHE_TPRODUCT";
	@Resource
	private ProductDao productDao;
	
	@PostConstruct
	public void init(){
		if(!TimerTask.containsClearDailyKey(KEY_CACHE_TPRODUCT)){
			TimerTask.putClearDailyKey(KEY_CACHE_TPRODUCT);
		}
	}
	
	public int deleteByPrimaryKey(String productId){
		return productDao.deleteByPrimaryKey(productId);
	}

   public int insert(TProduct record){
	   return productDao.insert(record);
   }

   public int insertSelective(TProduct record){
	   return productDao.insertSelective(record);
   }

   public TProduct selectByPrimaryKey(String productId){
	   return productDao.selectByPrimaryKey(productId);
   }

  public int updateByPrimaryKeySelective(TProduct record){
	  redisDao.remove(KEY_CACHE_TPRODUCT, record.getProductCode());
	  return productDao.updateByPrimaryKeySelective(record);
  }

  public int updateByPrimaryKey(TProduct record){
	  redisDao.remove(KEY_CACHE_TPRODUCT, record.getProductCode());
	  return productDao.updateByPrimaryKey(record);
  }
    
   public List<TProduct> getProductList(){
	   return productDao.getProductList();
   }
}