package com.HostelMS.serviceImp;

import java.util.Scanner;

import com.HostelMS.dao.UserDao;
import com.HostelMS.daoImp.UserDaoImp;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.User;
import com.HostelMS.service.UserDashboard;

import org.jboss.logging.Logger;

public class UserDashboardImp implements UserDashboard{
	static UserDashboardImp udl = new UserDashboardImp();
	static UserDao dao = new UserDaoImp();
	static Logger Log = Logger.getLogger(UserDashboardImp.class);
	static int userId;
	static Scanner scan = new Scanner(System.in);
	
	//this is visible on console to choose operations
	// this is dashboard 
	@Override
	public void dashboard(int uId) throws GlobalException {
		Log.info("\n\n\t\t\t********************  USER DASHBOARD  ***********************");
		userId = uId;
		
		int op = 0;
		while(op<6) {
			Log.info("\n\t\t\t------------- YOU CAN CHOOSE ANY OF THE FOLLOWING --------------\n  -> Press 1 to View Room\n  -> Press 2 to View the Due Amount\n  -> Press 3 to Change"
					+ " Password\n  -> Press 4 to View Profile \n  -> Press 5 to change Phone Number\n  -> Press 6 to exit");
			op = scan.nextInt();
		
			switch(op) {
				case 1 -> udl.viewRoom();
				case 2 -> udl.viewDueAmount();
				case 3 -> udl.changePassword();
				case 4 -> udl.viewProfile();
				case 5 -> udl.changePhoneNumber();
				case 6 -> udl.exit();
			}
		}
		
	}

	// it returns the detail about Room
	@Override
	public void viewRoom() {
		
		User u2 = dao.viewRoom(userId);
		Log.info("Hey "+u2.getUserName()+" your Room details are as :\nRoom Id : "+u2.getUserRoom().getRoomId()+"\nRoom Name"
				+ " : "+ u2.getUserRoom().getRoomName()+"\nRoom's Type : "+u2.getUserRoom().getRoomType());
	}
	
	//this method returns the fee amount from table
	@Override
	public void viewDueAmount() {
		int amount = dao.viewDuoAmount(userId);
		Log.info("Fee Due : "+amount);
		
	}

	//Shows the whole table's attribute
	@Override
	public void viewProfile() {
		User u2 =dao.viewProfile(userId);
		Log.info(u2);
		
	}

	// to update or change the phone number of logged in user
	@Override
	public void  changePhoneNumber() {
		Log.info("Enter New Phone Number : ");
		String phone = scan.next();
		int st = dao.changePhoneNumber(userId, phone);
		if(st == 1) {
			Log.info("Phone Number updated");
		}
	}
	
	//it updates the password after verification
	@Override
	public void changePassword() throws GlobalException {
		Log.info("Enter Current Password : ");
		String oldPwd = scan.next();
		Log.info("Enter New Password : ");
		String newPwd = scan.next();
		int st = dao.changePassword(userId, oldPwd, newPwd);
		if(st == 1) {
			Log.info("Password is updated");
		}
	}
	public void exit() {
		 System.exit(0);
	}
}






