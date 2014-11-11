package org.warrior.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class LoginLog implements Serializable
{

	private static final long	serialVersionUID	= 65365421L;
	private int id;
	private int user_id;
	private String ip;
	private Timestamp time;

	public LoginLog(){}
	
	public LoginLog(int user_id,String ip){
		this.user_id=user_id;
		this.ip=ip;
		this.time=new Timestamp(System.currentTimeMillis());//当前时间
	}
	public LoginLog(int user_id,String ip,Timestamp time){
		this.user_id=user_id;
		this.ip=ip;
		this.time=time;
	}

	@Override
	public String toString()
	{
		return "LoginLog [id=" + id + ", user_id=" + user_id + ", ip=" + ip
				+ ", time=" + time + "]";
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getUser_id()
	{
		return user_id;
	}

	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public Timestamp getTime()
	{
		return time;
	}

	public void setTime(Timestamp time)
	{
		this.time = time;
	}
}
