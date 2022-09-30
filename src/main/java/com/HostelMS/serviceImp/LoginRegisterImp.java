package com.HostelMS.serviceImp;

import java.util.Scanner;
import java.util.regex.Pattern;

import com.HostelMS.App;
import com.HostelMS.dao.HostelDao;
import com.HostelMS.daoImp.HostelDaoImp;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.User;
import com.HostelMS.service.AdminDashboard;
import com.HostelMS.service.LoginRegister;
import com.HostelMS.service.UserDashboard;

import org.apache.log4j.Logger;

public class LoginRegisterImp implements LoginRegister {
	
	static Logger Log = Logger.getLogger(LoginRegisterImp.class);// logger class
	static Scanner bs = new Scanner(System.in);// global scanner 
	static HostelDao dao = new HostelDaoImp();// object
	@Override
	public void register() throws GlobalException{
		
		Log.info("Here you can Register");
		Log.info("Enter the Name : ");
		String uName = bs.next();
		Log.info("Enter the Password : ");
		String uPwd = bs.next();
		Log.info("Enter the Phone Number : ");
		String uPhone = bs.next();
		Log.info("Enter the Address : ");
		String uAddress = bs.next();
		
		User u1 = new User();// this class will store value (and is from Model Package)
		u1.setUserName(uName);
		u1.setUserPassword(uPwd);
		u1.setUserPhone(uPhone);
		u1.setUserAddress(uAddress);
		u1.setUserRoom(null);
		u1.setUserRole("Student");
		u1.setUserFee(0);
		
		// regular Expression to check the data format
		if (Pattern.matches("[a-zA-Z]{4,}", uName)&& Pattern.matches("[0-9a-zA-Z@#]{4,}", uPwd)&& Pattern.matches("[0-9]{10,}", uPhone))
		{
			//if correct then dao action are performed
			int status = dao.registration(u1);// from HostelDaoImp
			if(status == 1) {
				Log.info("Registration Successful");
			}
			else {
				Log.info("Somthing went wrong");
				}
		}
		else {
			throw new GlobalException("Invaid Data : \n Name must be alphabetic and should have more that 3 characters\nPassword "
					+ "should have more than four characters and can have a-z , A-Z, digits and @ and # symbol"
					+ "\n and you number should have 10 digits");
		}
	}
	
	

	@Override
	public void login() throws GlobalException {
		Log.info("+++  Here you can login  +++");
		Log.info("Enter User Name");
		String uName = bs.next();
		Log.info("Enter Password");
		String uPwd = bs.next();
		
		//data passed to HostelDao file
		 User u1 = dao.login(uName, uPwd);
		 //if login method works properly in dao class
		 if (u1 != null) {
			 Log.info("\n-----------------------------------------------------------------------------------------------------");
			 Log.info("Hey "+u1.getUserName()+" you have logged in successfully \n");
		 }
		 else {
			 Log.info("Somthing is wrong here!");
		 }
		
		 //  * Once user log in then it shows them Dashboard depending on their Role(userRole)
		 
		 AdminDashboard ad1 = new AdminDashboardImp();// object of adminDashboard
		 UserDashboard ud1 = new UserDashboardImp();// object of userDashboard
		 // to the user dashboard
		 if (u1.getUserRole().equals("Student")) {
			 ud1.dashboard(u1.getUserID());
		 }
		 //to the admin Dashboard
		 else if  (u1.getUserRole().equals("Admin")) {
			 ad1.dashboard();
		 }
	}

 }
