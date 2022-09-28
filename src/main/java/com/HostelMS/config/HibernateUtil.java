package com.HostelMS.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
		public static SessionFactory sesFactory;
		public static SessionFactory getSessionFactory() {
			if (sesFactory == null) {
				try {
					Configuration config = new Configuration();
					Properties prop = new Properties();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
}
