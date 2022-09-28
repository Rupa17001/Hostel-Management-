//interface : abstract method for userDashBoard
package com.HostelMS.dao;

import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.User;

public interface UserDao {
	public User viewRoom(int uId);
	public int viewDuoAmount(int uId);
	public User viewProfile(int uId);
	public int  changePhoneNumber (int uId, String phone);
	public int changePassword(int uId, String oldPwd, String newPwd) throws GlobalException;
}
