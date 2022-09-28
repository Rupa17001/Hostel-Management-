package com.HostelMS.config;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.HostelMS.model.Room;
import com.HostelMS.model.User;

public class HibernateUtil {
		public static SessionFactory sesFactory;
		public static SessionFactory getSessionFactory() {
			if (sesFactory == null) {
				try {
					Configuration config = new Configuration();
					Properties prop = new Properties();
					prop.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
					prop.put(Environment.URL,"jdbc:mysql://localhost:3306/HostelMS");
					prop.put(Environment.USER, "root");
					prop.put(Environment.PASS, "17Rup@01");
					prop.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
					prop.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");
					prop.put(Environment.SHOW_SQL, "false");
					prop.put(Environment.HBM2DDL_AUTO, "update");
					
					config.setProperties(prop);
					config.addAnnotatedClass(User.class);
					config.addAnnotatedClass(Room.class);
					sesFactory = config.buildSessionFactory();
									
				}
				catch(Exception e){
					e.printStackTrace();
					
					return null;
				}
			}

			return sesFactory;

		}
		public static Session getSession() {
			return getSessionFactory().openSession();
		}
}
