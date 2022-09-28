package com.HostelMS.daoImp;

import com.HostelMS.config.HibernateUtil;
import com.HostelMS.dao.UserDao;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.User;

import org.hibernate.Session;

public class UserDaoImp implements UserDao {

	@Override
	public User viewRoom(int uId) {
		try(Session ses = HibernateUtil.getSession()){
			ses.beginTransaction();
			User u2 = ses.get(User.class, uId);
			return u2;//returns User object
		}
		
	}

	@Override
	public int viewDuoAmount(int uId) {
		try(Session ses = HibernateUtil.getSession()){
			ses.beginTransaction();
			int amt = (int)ses.createQuery("select userFee from User where userId =: uId").setParameter("uId", uId).uniqueResult();
			return amt;
		}
		
	}

	@Override
	public User viewProfile(int uId) {
		try(Session ses = HibernateUtil.getSession()){
			ses.beginTransaction();
			User u2 = ses.get(User.class, uId);
			return u2;// returns User's object
		}
		
	}

	@Override
	public int changePhoneNumber (int uId, String phone) {
		try(Session ses = HibernateUtil.getSession()){
			
			ses.beginTransaction();
			int status = (int)ses.createQuery("update User set userPhone=:phone where userId =: uId").setParameter("phone", phone).setParameter("uId", uId).executeUpdate();
			ses.getTransaction().commit();//transaction closed
			
			return status;
		}
		
	}

	@Override
	public int changePassword ( int uId, String oldPwd, String newPwd) throws GlobalException {
		try(Session ses =HibernateUtil.getSession()){
			//updates password 
			ses.beginTransaction();
			User u1 = ses.get(User.class,uId);//get method retries attribute detail in object
			//updates only if Entered password is same as Existing password 
			if(u1.getUserPassword().equals(oldPwd)) {
				//status set to 1 if it updates
				int status = (int)ses.createQuery("update User set userPassword=:newPwd where userId =: uId").setParameter("newPwd", newPwd).setParameter("uId", uId).executeUpdate();
				ses.getTransaction().commit();
				
				return status;
			}
			//if something went wrong 
			else {
				throw new GlobalException("To Update password Enter Correct Password");
			}
		}
		
	}

}
