package com.HostelMS.service;



import com.HostelMS.exception.GlobalException;


public interface AdminDashboard {
	public void dashboard() throws GlobalException;
	public void  viewAllRoom();
	public void  viewAllUser();
	public void createRoom() throws GlobalException;
	public void allotRoom() throws GlobalException;
	public void deleteUser() throws GlobalException;
	public void  usersInARoom();
	public void addInDueAmount () throws GlobalException;
	public void paidDueAmount () throws GlobalException;
	public void viewGivenUserProfile();
	
}
