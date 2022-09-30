/* This class updates Admin's dashboard
 * This class Access the Object data
 * the insertion, deletion and updation in the table takes place in this class  */

package com.HostelMS.daoImp;

import java.util.List;

import javax.persistence.Query;

import com.HostelMS.config.HibernateUtil;
import com.HostelMS.dao.AdminDao;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.Room;
import com.HostelMS.model.User;

import org.hibernate.Session;


public class AdminDaoImp implements AdminDao{
	//method 1 = Returns the List of Rooms in the table
	@Override
	public List<Room> viewAllRoom() {
		//try block is auto closable
		try(Session ses = HibernateUtil.getSession()){ 
			
			//fetching table rows in list
			Query q = ses.createQuery("from Room");
			List<Room> roomList = q.getResultList();
			return roomList;
			
		}
		
	}

	@Override
	public List<User> viewAllUser() {
		
try(Session ses = HibernateUtil.getSession()){ 
			String student = "student";
			//fetching table rows in list
			Query q = ses.createQuery("from User where userRole=:student").setParameter("student", student);
			List<User> userList = q.getResultList();
			return userList;
			
		}
	}
	//method 3 : creates new room if it did not existed
	@Override
	public int createRoom(Room r1) throws GlobalException {
		try(Session ses = HibernateUtil.getSession()){
			ses.beginTransaction();
			
			String roomName = r1.getRoomName();
			Room r2 = null;
			// query below checks if Room with same already exists or not [ if exists then r2 will hold some integer and new room will not create]
			r2 = (Room) ses.createQuery("from Room where roomName =: roomName ").setParameter("roomName", roomName).uniqueResult();
			if (r2 == null) {
				ses.save(r1);
				ses.getTransaction().commit();
				return 1;
			}
			else {
				throw new GlobalException("This Room Name already exists.");
			}
		}
		
	}
	//method 4 : allots room to the specified user(from his userId)
	@Override
	public int allotRoom(int uId, int rId) {
		try(Session ses = HibernateUtil.getSession())
		{
			ses.beginTransaction();
			int status = ses.createQuery("update User set userRoom_roomId=:rId where userId=:uId ").setParameter("rId", rId).setParameter("uId", uId).executeUpdate();
			ses.getTransaction().commit();
			return status;
			
		}
	
	}

	//method 5 : deletes the user's data from the table (with the help of userId)
	@Override
	public int deleteUser(int uId) {
		try(Session ses=HibernateUtil.getSession())
		{
			ses.beginTransaction();
			int status= ses.createQuery("delete from User where userId=:uId").setParameter("uId", uId).executeUpdate();
			ses.getTransaction().commit();
			return status;
		}	
	 
	}
	
	//method 6 : returns list of Users from same Room
	@Override
	public List<User> usersInARoom(int rId) {
		try(Session ses=HibernateUtil.getSession()){
			
			Query qu=ses.createQuery("from User where userRoom_roomId=:rId").setParameter("rId", rId);
			List<User> userList=qu.getResultList();
			return userList;
		}
	}
	
	//method 7 : add the dueAmount of user in userFee
	@Override
	public int addInDueAmountint(int uId, int amount) {
		try(Session ses=HibernateUtil.getSession()){
			ses.beginTransaction();
			int dueamt=(int)ses.createQuery("select userFee from User where userId=:uId").setParameter("uId",uId).uniqueResult();
		
			dueamt+=amount;
			int status=ses.createQuery("update User set userFee=:dueamt where userId=:uId").setParameter("dueamt", dueamt).setParameter("uId", uId).executeUpdate();
			ses.getTransaction().commit();
			return status;
		}
		
	}

	//method 8 : subtracts the PaidAmount of user from userFee
	@Override
	public int paidDueAmount(int uId, int amount) {
		try(Session ses=HibernateUtil.getSession()){
			ses.beginTransaction();
			int dueamt=(int)ses.createQuery("select userFee from User where userId=:uId").setParameter("uId",uId).uniqueResult();
		
			dueamt-=amount;
			int status=ses.createQuery("update User set userFee=:dueamt where userId=:uId").setParameter("dueamt", dueamt).setParameter("uId", uId).executeUpdate();
			ses.getTransaction().commit();
			return status;
		}
		
	}

	//method 9 : returns the profile of specified user
	@Override
	public User viewGivenUserProfile(int uId) {
try(Session ses=HibernateUtil.getSession()){
			
			User u1=ses.get(User.class, uId);
			return u1;
		}
		
	}

}
