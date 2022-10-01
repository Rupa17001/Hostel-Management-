
/*@Author : Rupa :)
 * test class
 * test for package HostelMS.dao
 * methods of classes : HostelDaoImp  ,   UserDaoImp  ,   AdminDaoImp  */
package com.HostelMS;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.HostelMS.config.HibernateUtil;
import com.HostelMS.dao.HostelDao;
import com.HostelMS.daoImp.AdminDaoImp;
import com.HostelMS.daoImp.HostelDaoImp;
import com.HostelMS.daoImp.UserDaoImp;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.User;

import org.hibernate.Session;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {

	// ************ Methods of class HotelDaoImp ************
	
  @Disabled
  @Test
  @DisplayName("Test Registration")
  public void testRegistration() {
	  //object in of the class in which Registration method resided
	  HostelDao dao = new HostelDaoImp();
	  
	  //values pass to the table to store
	  //they are new values and satisfies Validators
	  User u1=new User();
		u1.setUserName("abcd");
		u1.setUserPassword("acb123");
		u1.setUserPhone("8722065724");
		u1.setUserRole("student");
		u1.setUserAddress("Manipur");
		
		//the user with this name already exists in table
		//this will raise an exception 
		User u2=new User();
		u2.setUserName("Rupa");
		u2.setUserPassword("1452");
		u2.setUserPhone("9874563210");
		u2.setUserRole("student");
		u2.setUserAddress("Delhi");
		
		assertAll(
				//this is true because user with this name do not exist before & satisfies validators
				()->assertEquals(1,dao.registration(u1)),
				//this is also true because same username will raise Global Exception
				()->assertThrows(GlobalException.class,()->dao.registration(u2)));
			
		
  }
  @Test
  @Disabled
  @DisplayName("Testing Login Method ")
  //method has Global Exception because method call has it
  public void testLogin() throws GlobalException{
	  // object for class HostelDaoImp
	  HostelDao dao = new HostelDaoImp();
	  
	  //building session
	  Session ses = HibernateUtil.getSession();
	  
	  //getting attributes of User table @Id = 1 in u1 object
	  User u1 = ses.get(User.class, 1);
	  //getting the User object returned by login object in u2
	  //this User already exists in User table @Id = 1
	  User u2 = dao.login("Rupa", "5424");
	  
	  
	  assertAll(
			  //true case
			  ()->assertEquals(u2.toString(),u1.toString()),
			  //this is true 
			  //because User with this name do not exists
			  //and wrong password causes Global Exception
			  ()->assertThrows(GlobalException.class,()->dao.login("Ketan", "7854"))
			  );
  
  
  }
  
  //*************** Methods of class UserDaoImp **************
  
  @Disabled
  @Test
  @DisplayName(" View Room")
  public void testViewRoom() {
	  //object
	  UserDaoImp dao = new UserDaoImp();
	  //Session build
	  Session ses = HibernateUtil.getSession();
	  
	  // u1 and u2 objects both are calling same user as @Id = 1 in both
	  User u1 = ses.get(User.class, 1);
	  User u2 = dao.viewRoom(1);
	  
	  User u3 = ses.get(User.class, 4);
	  
	  assertAll(
			  //true case
			  ()->assertEquals(u2.toString(),u1.toString()),
			  //this will raise error because u3 points to Id = 4 and u1 points to Id = 1
			  ()->assertEquals(u3.toString(),u1.toString())
			  );
	  
  }
  @Disabled
  @Test
  @DisplayName("View Duo Amount")
  public void testViewDuoAmt() {
	//object
	  UserDaoImp dao = new UserDaoImp();

	  assertAll(
			  //test true
			  //because User @ID = 1 has Due Amount of 250
			  ()->assertEquals(250,dao.viewDuoAmount(1)),
			  //test fails
			  //because User @Id = 2 have  0 Due Amount in the table
			  ()->assertEquals(250,dao.viewDuoAmount(2))
			  );

  }
  @Disabled
  @Test
  @DisplayName("Testing changePhoneNumber method")
  public void testChangeNumber() {
	//object
	  UserDaoImp dao = new UserDaoImp();
	  assertAll(
			  //true case
			  //because method only returns 1
			  ()->assertEquals(1,dao.changePhoneNumber(2, "9874563210")),
			  //test fails
			  //because method only returns 1
			  ()->assertEquals(-1,dao.changePhoneNumber(2, "9874563210"))
			  );
  }
  
  // *********** Testing methods of AdminDaoImp Class ************
  
  @Disabled
  @Test
  @DisplayName("Testing Paid Due Amount")
  public void testPaidDueAmount () {
	//object
	  AdminDaoImp dao = new AdminDaoImp();
	  assertAll(
			  //true case
			  //because method only returns 1
			  ()->assertEquals(1,dao.paidDueAmount(1, 20)),
			  //test true
			  //throws Global Exception
			  //because User @Id 2 have no Due Amount
			  ()->assertThrows(GlobalException.class,()->dao.paidDueAmount(2, 20))
			  );
  
  }
  @Disabled
  @Test
  @DisplayName(" Testing Adding Due Amount")
  public void testAddDueAmount() {
	//object
	  AdminDaoImp dao = new AdminDaoImp();
	  assertAll(
			  //true case
			  //because method only returns 1
			  ()->assertEquals(1,dao.addInDueAmount(1, 20)),
			  //test fails
			  //because user do not exists
			  ()->assertEquals(1,dao.addInDueAmount(5, 20))
			  );
  }
  
  @Test
  @DisplayName("Testing viewGivenUserProfile")
  public void testViewGivenUserProfile() {
	//object
	  AdminDaoImp dao = new AdminDaoImp();
	//building session
	  Session ses = HibernateUtil.getSession();
	  
	  //getting attributes of User table @Id = 1 in u1 object
	  User u1 = ses.get(User.class, 1);
	  User u2 = dao.viewGivenUserProfile(1);// method
	  User u3 = ses.get(User.class, 2);
	  assertAll(
			  //true case
			  ()->assertEquals(u1.toString(),u2.toString()),
			  //this will raise error because u3 points to Id = 2 and u2 points to Id = 1
			  ()->assertEquals(u3.toString(),u2.toString())
			  );
	  
	  
	  
  }
 
  }
