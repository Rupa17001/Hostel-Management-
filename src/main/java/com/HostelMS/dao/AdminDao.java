package com.HostelMS.dao;

import java.util.List;

import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.Room;
import com.HostelMS.model.User;

public interface AdminDao {
	public List<Room>  viewAllRoom();
	public List<User>  viewAllUser();
	public int createRoom(Room r1) throws GlobalException;
	public int allotRoom(int uId,int rId);
	public int deleteUser(int uId);
	public List<User>  usersInARoom(int rId);
	public int addInDueAmountint (int uId, int amount);
	public int paidDueAmount (int uId, int amount);
	public User viewGivenUserProfile(int uId);
}
