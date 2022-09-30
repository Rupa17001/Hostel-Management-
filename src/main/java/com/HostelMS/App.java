// @author : Rupa
/*
 * Loggers are used in place of println
 * */


package com.HostelMS;

import java.util.Scanner;

import com.HostelMS.exception.GlobalException;
import com.HostelMS.service.LoginRegister;
import com.HostelMS.serviceImp.LoginRegisterImp;

import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
	static Logger Log = Logger.getLogger(App.class);
    public static void main( String[] args ) throws GlobalException
    {
    	Scanner sc = new Scanner(System.in);
    	Log.info("\t\t\t---------------------------- Hostel Management System -----------------------------");
        LoginRegister logreg = new LoginRegisterImp();
        
        Log.info("-> Press 1 to Register\n  -> Press 2 to LOGIN");
        int op = sc.nextInt();
        
        switch (op) {
        	case 1 -> logreg.register();//register user
        	case 2 -> logreg.login();// user login
        }
        
        
    }
}
