package com.HostelMS.serviceImp;

import com.HostelMS.service.AdminDashboard;

import org.apache.log4j.Logger;

public class AdminDashboardImp implements AdminDashboard{
	Logger Log = Logger.getLogger(AdminDashboardImp.class);
	@Override
	public void dashboard() {
		Log.info("\t\t************************** ADMIN DASHBOARD **************************");		
	}

}
