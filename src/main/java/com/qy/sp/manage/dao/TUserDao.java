package com.qy.sp.manage.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qy.sp.manage.dao.impl.UserDao;
import com.qy.sp.manage.dto.TUser;

@Component
public class TUserDao {
	@Resource
	private UserDao userDao;
	
	public int deleteByPrimaryKey(String userId){
		return userDao.deleteByPrimaryKey(userId);
	}

	public int insert(TUser record){
		return userDao.insert(record);
	}

	public int insertSelective(TUser record){
		return userDao.insertSelective(record);
	}

	public TUser selectByPrimaryKey(String userId){
		return userDao.selectByPrimaryKey(userId);
	}

	public int updateByPrimaryKeySelective(TUser record){
		return userDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TUser record){
		return userDao.updateByPrimaryKey(record);
	}
    
	public List<TUser> getUserList(@Param("user") TUser user,@Param("start") int start,@Param("end") int end){
		return userDao.getUserList(user, start, end);
	}
    
	public int getUserItems(TUser user){
		return userDao.getUserItems(user);
	}
    
	public TUser loadUserByNameAndPwd(@Param("userName") String userName,@Param("password") String password){
		return userDao.loadUserByNameAndPwd(userName, password);
	}
    
	public int loadItemsByUserAccount(String userAccount){
		return userDao.loadItemsByUserAccount(userAccount);
	}
    
	public List<TUser> loadAll(){
		return userDao.loadAll();
	}
    
	public List<TUser> loadLeaders(){
		return userDao.loadLeaders();
	}
}