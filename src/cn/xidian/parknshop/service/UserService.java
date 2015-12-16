package cn.xidian.parknshop.service;

import cn.xidian.parknshop.beans.User;

public interface UserService {

	User findUserByName(String userName);
	
	boolean checkUserNameExist(String userName);
	
	boolean LogIn(String userName,String secPassWord);
	
}
