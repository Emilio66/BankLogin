package org.warrior.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import org.warrior.bean.User;
import org.warrior.util.SHA256;

/**
 * 对用户信息的数据库访问操作进行底层封装
 * @author zhaozy
 *
 */
@Repository
public class UserDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate template){
		jdbcTemplate=template;
	}
	
	/**
	 * 对用户登陆进行验证
	 * @param username
	 * @param password
	 * @param ip
	 * @return
	 */
	public User loginValidate(String username,String password,final String ip){
		final User user=new User();
		//对密码进行SHA256加密之后再比较
		String sql="select * from user where name=? and password=? ";
		
		//查询此用户的用户名密码是否正确
		jdbcTemplate.query(sql, new Object[]{username,password}, 
				new RowCallbackHandler(){	
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						int id=rs.getInt("id");
						user.setId(id);
						user.setName(rs.getString("name"));
						
						System.out.println(user);
						if(user!=null && ip!=null){
							//插入日志
							String queryLogSql="select count(*) from login_log where user_id="+id;
							int hasLogin=jdbcTemplate.queryForInt(queryLogSql);
							if(hasLogin>0){
								String updateLogSql="update login_log set login_ip='"+ip
										+"' where user_id="+id;
								jdbcTemplate.update(updateLogSql);
							}else{
								String insertLogSql="insert into login_log (user_id,login_ip)" +
										" values("+id+",'"+ip+"')";
								jdbcTemplate.update(insertLogSql);
							}
						}
					}
			});		
		return user;
	}
	
	/**
	 * 返回用户名是否存在，注册时会用到
	 * @param username
	 * @return
	 */
	public boolean isExist(String username){
		String sql=" select count(*) from user where name='"+username+"' ";
		int hasUser=jdbcTemplate.queryForInt(sql);
		if(hasUser>0){
			return true;
		}else {
			return false;
		}	
	}
	
	/**
	 * 注册一个用户
	 * @param username
	 * @param password
	 */
	public void insertUser(String username,String password){		
		String sql="insert into user(name,password) values('"+username+"','"+password+"')";
		jdbcTemplate.update(sql);	
	}
}
