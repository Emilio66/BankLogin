package org.warrior.service;

import org.warrior.bean.User;

/**
 * 访问用户信息接口
 * @author zhaozy
 *
 */
public interface UserService {
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @param ip
	 * @return
	 */
	public User login(String username,String password,String ip);
	
	/**
	 *  用户名是否存在
	 * @param name
	 * @return
	 */
	public boolean isExist(String name);
	
	/**
	 *  插入新用户，密码会加密之后再存入数据库
	 * @param name
	 * @param password
	 */
	public void insertUser(String name,String password);
}
