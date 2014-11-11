package org.warrior.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.warrior.bean.User;
import org.warrior.dao.UserDao;
import org.warrior.service.UserService;
import org.warrior.util.SHA256;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	public void setUserDao(UserDao dao){
		userDao=dao;
	}
	
	@Override
	public User login(String username, String password, String ip) {
		if(username!=null && username.trim().length()>0 &&
				password!=null && password.trim().length()>0 &&
				ip!=null && ip.trim().length()>0){
			//SHA256 decrypt
			password=SHA256.encode(password);
			User user=userDao.loginValidate(username, password, ip);
			if(user!=null && user.getName()!=null)
				return user;
		}
		return null;
	}

	@Override
	public boolean isExist(String name) {
		return userDao.isExist(name);
	}

	@Override
	public void insertUser(String name, String password) {
		String digest=SHA256.encode(password);//加密之后在放进去
		userDao.insertUser(name, digest);
	}

}
