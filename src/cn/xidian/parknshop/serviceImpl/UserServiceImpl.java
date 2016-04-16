package cn.xidian.parknshop.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.dao.UserDao;
import cn.xidian.parknshop.dao.UserRepository;
import cn.xidian.parknshop.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name="userDao")
	private UserDao userDao;
	
//	@Resource 暂时不使用JPA 避免扫描注入 
	private UserRepository userRepository;
	
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
	public String LogIn(String userName, String secPassWord) {
		// TODO Auto-generated method stub
		return userDao.LogIn(userName, secPassWord);
	}
	
	@Override
	public List<User> findUserByNickName(String nickName) {
		// TODO Auto-generated method stub
		return userDao.findUserByNickName(nickName);
	}

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}


}
