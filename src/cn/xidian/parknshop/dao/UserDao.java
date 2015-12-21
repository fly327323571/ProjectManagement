package cn.xidian.parknshop.dao;

import java.util.List;

import cn.xidian.parknshop.beans.User;

public interface UserDao {

	User findUserByName(String userName);
	
	boolean checkUserNameExist(String userName);
	
	String LogIn(String userName,String secPassWord);
	
	List<User> findUserByNickName(String nickName);
}
