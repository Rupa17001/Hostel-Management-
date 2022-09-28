//it is an Interface : it holds the abstract methods
package com.HostelMS.service;

import com.HostelMS.exception.GlobalException;

public interface LoginRegister {
	public void register() throws GlobalException;
	public void login() throws GlobalException;
}
