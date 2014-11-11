package org.warrior.bean;

import java.io.Serializable;

public class User implements Serializable
{

	private static final long	serialVersionUID	= 15493198966L;
	private int id;
	private String name;
	private String password;
	
	public User (String username,String password){
		this.name=username;
		this.password=password;
	}
	
	
	@Override
	public String toString()
	{
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ "]";
	}

	public User(){}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	
	

}
