package com.system.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.system.bean.User;

public interface UserDao extends JpaRepository<User, String> 
{

}
