/*
 * The method below all are void 
 * This class prints in console for Admin Dsshboard
 * Methods in this class calls the AdminDaoImp class by dao object 
 * 9 operation could be performed in the Admin Dashboard*/
package com.HostelMS.serviceImp;

import java.util.List;
import java.util.Scanner;

import com.HostelMS.dao.AdminDao;
import com.HostelMS.daoImp.AdminDaoImp;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.Room;
import com.HostelMS.model.User;
import com.HostelMS.service.AdminDashboard;

import org.apache.log4j.Logger;

public class AdminDashboardImp implements AdminDashboard{
	
	static AdminDao dao = new AdminDaoImp();
	static AdminDashboardImp ad = new AdminDashboardImp();
	static Scanner sc = new Scanner(System.in);
	static Logger Log = Logger.getLogger(AdminDashboardImp.class);
	
	
	@Override
	public void dashboard() throws GlobalException {
		Log.info("\n\t\t\t************************** ADMIN DASHBOARD **************************");
		int op = 0;// this variable is responsible for iteration and case switching below
		while (op<11) {
			Log.info("\n\t\t-------------------------------- PRESS KEY TO PERFORM GIVEN OPERATONS ---------------------------------------\n");
			Log.info("-> PRESS 1 To view All Rooms\n  -> PRESS 2 to view all Users\n  -> PRESS 3 to create a Room\n  -> PRESS 4 to allot"
					+ " Room to a User\n  -> PRESS 5 to delete a User's Data\n  -> PRESS 6 to check how many Users are in specific Room"
					+ "\n  -> PRESS 7 to add the Due Amount in User's Fee\n  -> PRESS 8 to deduct paid Amount from User's Fee\n  "
					+ "-> Press 9 to check the specific students data(profile)\n  -> PRESS OTHER THAN ABOVE OPTIONS TO EXIT");
			op = sc.nextInt();
			
			switch (op)//switch expression
			{
				case 1->{ad.viewAllRoom();}
				case 2->{ad.viewAllUser();}
				case 3->{ad.createRoom();}
				case 4->{ad.allotRoom();}
				case 5->{ad.deleteUser();}
				case 6->{ad.usersInARoom();}
				case 7->{ad.addInDueAmountint();}
				case 8->{ad.paidDueAmount();}
				case 9->{ad.viewGivenUserProfile();}
				default ->{System.exit(0);}
			}
			
			
		}
		
		
	}
	@Override
	public void viewAllRoom() {
		List<Room> roomList = (List<Room>) dao.viewAllRoom();
		Log.info("ROOM ID\t\t ROOM NAME \t\tROOM TYPE \n-------------------------------------------------------------------------------------------------------------------------");
		for(Room r : roomList) {
			Log.info(" "+r.getRoomId()+"\t\t "+r.getRoomName()+"\t\t "+r.getRoomType());
		}
		
	}
	
	// if press 2 this method will be executed
	@Override
	public void viewAllUser() { 
		List<User> userList = dao.viewAllUser();//fetching user tables's list from AdminDao
		Log.info("User ID\t\tUser NAME\tPhone Number\t\tRoom ID \n-------------------------------------------------------------------------------------------------------------------------");
		for(User u : userList) {
			Log.info(" "+u.getUserID()+"\t\t "+u.getUserName()+"\t\t "+u.getUserPhone()+"\t\t "+u.getUserRoom().getRoomId());
		}
		
	}
	
	// if admin press 3 it means : this method will create a room only if there is no room in the table with same name
	@Override
	public void createRoom() throws GlobalException {
		Log.info("Enter Room Id :");
		int id =  sc.nextInt();
		Log.info("Enter Room Name :");
		String name = sc.next();	
		Log.info("Enter Room Type :");
		String type = sc.next();
		
		Room r = new Room();
		r.setRoomId(id);
		r.setRoomName(name);
		r.setRoomType(type);
		
		
			int status = dao.createRoom(r);
			if (status == 1) {
				Log.info("ROOM added successfully");
		}
			else  {
				throw new GlobalException("Something went wrong");
			}
		
	}
	
	// if admin press 4 it means : this method will allot a room to the user
	@Override
	public void allotRoom() throws GlobalException {
		Log.info("Enter User Id :");
		int uid =  sc.nextInt();
		Log.info("Enter Room Id :");
		int rid =  sc.nextInt();	
		
		int status = dao.allotRoom(uid, rid);
		
			if (status == 1) {
				Log.info("ROOM alloted successfully");
			}
		
			else {
				throw new GlobalException("Something went wrong");
			}
	}
	
	// press 5 : this method deletes the profile of the user
	@Override
	public void deleteUser() throws GlobalException {
		Log.info("Enter User Id :");
		int uid =  sc.nextInt();
		int status = dao.deleteUser(uid);
		if (status == 1) {
			Log.info("User's data deleted from table successfuly");
		}
	
		else {
			throw new GlobalException("Something went wrong");
		}
	}
	
	//if press 6 : this method prints the list of users in a room
	@Override
	public void usersInARoom() {
		Log.info("Enter Room Id :");
		int rid =  sc.nextInt();
		
		List<User> userList = dao.usersInARoom(rid);
		Log.info("User ID\t\tUser NAME\tPhone Number\n-------------------------------------------------------------------------------------------------------------------------");
		for(User u : userList) {
			Log.info(" "+u.getUserID()+"\t\t "+u.getUserName()+"\t\t "+u.getUserPhone());
		}
		
	}
	
	// press 7 : this method adds the due amount to the user's fee
	@Override
	public void addInDueAmountint() throws GlobalException {
		Log.info("Enter User Id :");
		int uid =  sc.nextInt();
		Log.info("Enter Due Amount :");
		int amount =  sc.nextInt();
		
		int status =dao.addInDueAmountint(uid, amount);
		if (status == 1) {
			Log.info("User's Fee updated successfuly");
		}
	
		else {
			throw new GlobalException("Something went wrong");
		}
	}
	
	// press 8 : this method subtracts paid amount from user's fee
	@Override
	public void paidDueAmount() throws GlobalException {
		
		Log.info("Enter User Id :");
		int uid =  sc.nextInt();
		Log.info("Enter paid Amount :");
		int amount =  sc.nextInt();
		
		int status =dao.paidDueAmount(uid, amount);
		if (status == 1) {
			Log.info("User's Fee updated successfuly");
		}
	
		else {
			throw new GlobalException("Something went wrong");
		}
		
	}
	// press 9 : this method prints the profile of entered User
	@Override
	public void viewGivenUserProfile() {
		Log.info("Enter User Id :");
		int uid =  sc.nextInt();
		User u1 = dao.viewGivenUserProfile(uid);
		//the Log statements below are fancy to print a table like structure in console otherwise Log.info(u1); will also work perfectly fine
		Log.info("User ID\t\tUser NAME\tPhone Number\t\tAddress\t\tFee Pending\t\tRole\t\tRoom ID\t\tRoom Name\tRoom Type"
				+ "\n------------------------------------------------------------------------------------------------------------------------------------------------------");
		Log.info(" "+u1.getUserID()+"\t\t "+u1.getUserName()+"\t\t "+u1.getUserPhone()+"\t\t "+u1.getUserAddress()+"\t\t "+u1.getUserFee()
		+"\t\t "+u1.getUserRole()+" \t"+u1.getUserRoom().getRoomId()+"\t \t"+u1.getUserRoom().getRoomName()+"\t\t "+u1.getUserRoom().getRoomType());
		
	}


}
