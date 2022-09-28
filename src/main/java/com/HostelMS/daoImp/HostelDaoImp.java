package com.HostelMS.daoImp;

import com.HostelMS.config.HibernateUtil;
import com.HostelMS.dao.HostelDao;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.User;

import org.hibernate.Session;

public class HostelDaoImp implements HostelDao{

	@Override
	public int registration(User u1) throws GlobalException{
		
		try(Session ses = HibernateUtil.getSession())//gets Session(this is auto try block)
		{
			User u2 = null;//object
			String userName = u1.getUserName();// stores userName redirected from App -> LoginRegister -> register()
			u2 = (User)ses.createQuery("from User where userName =: userName").setParameter("userName",userName).uniqueResult();//checks if userName already exists?
			//if userName is different then
			if (u2 == null) {
				// add data to database
				ses.beginTransaction();
				ses.save(u1);
				ses.getTransaction().commit();
				
				return 1;// returns to register (in LoginRegistration class)
			}
			else {
				throw new GlobalException("User already Exists");//if user with same name exists raise exception
			}
			
		}
		
		
	}

	@Override
	public User login(String userName, String userPassword) throws GlobalException {
		try(Session ses = HibernateUtil.getSession()){
			User u2 = null;//object
			u2 = (User)ses.createQuery("from User where userName =: userName").setParameter("userName",userName).uniqueResult();//checks if userName already exists?
			//if userName matches then checks for password
			if (u2 != null) {
				if (u2.getUserPassword().equals(userPassword)) {
					return u2;
				}
				// if password is wrong
				else {
					throw new GlobalException("Password didn't match !");
				}
			}
			//if name entered is wrong
			else {
				throw new GlobalException("User Not Found !");
			}
		}
		
	}
	
}
