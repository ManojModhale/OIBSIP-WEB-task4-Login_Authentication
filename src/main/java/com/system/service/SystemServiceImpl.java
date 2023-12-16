package com.system.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.bean.User;
import com.system.dao.UserDao;

@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private UserDao dao;

    @Override
    public String registerUser(User user) {
        System.out.println("Password from newUser Form = " + user.getPassword());
        String encodedPassword = hashPassword(user.getPassword());
        user.setPassword(encodedPassword);
        System.out.println("Encoded Password = " + user.getPassword());
        User user2 = dao.save(user);
        return user2.getLoginId();
    }

    @Override
    public boolean authenticateUser(String loginId, String password) {
        System.out.println("From Login form : " + loginId + " " + password);
        User user = dao.findById(loginId).orElse(null);
        System.out.println("After authentication for Login : " + user.getLoginId() + " " + user.getPassword());
        return user != null && hashPassword(password).equals(user.getPassword());
        
    }

    @Override
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return null;
        }
    }
}
