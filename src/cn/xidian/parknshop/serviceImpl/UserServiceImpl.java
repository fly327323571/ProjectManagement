package cn.xidian.parknshop.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.dao.UserDao;
import cn.xidian.parknshop.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Override
	public User findUserByName(String userName) {
		// TODO Auto-generated method stub
		return userDao.findUserByName(userName);
	}

	@Override
	public boolean checkUserNameExist(String userName) {
		// TODO Auto-generated method stub
		return userDao.checkUserNameExist(userName);
	}

	@Override
	public boolean LogIn(String userName, String secPassWord) {
		// TODO Auto-generated method stub
		return userDao.LogIn(userName, secPassWord);
	}

}
