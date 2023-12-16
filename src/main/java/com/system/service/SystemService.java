package com.system.service;


import com.system.bean.User;

public interface SystemService 
{
	
	public String registerUser(User user);
	
	public boolean authenticateUser(String loginId, String password);
	
	default String hashPassword(String password) {
        
        return password; 
    }
}
