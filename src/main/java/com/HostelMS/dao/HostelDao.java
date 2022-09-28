package com.HostelMS.dao;

import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.User;

public interface HostelDao {
	public int registration (User u1)throws GlobalException;// takes User object as input  
	public User login(String userName,String userPassword) throws GlobalException;// input are two string variables and output is User(info that method returns)
}
