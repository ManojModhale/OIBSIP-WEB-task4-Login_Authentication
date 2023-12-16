package com.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.system.bean.BankAccount;
import com.system.bean.User;
import com.system.service.SystemService;

import jakarta.servlet.http.HttpSession;


@Controller
public class SystemController 
{
	@Autowired
	private SystemService service;
	
	@RequestMapping("/")
	public String showIndexPage() {
		System.out.println("inside show index Page");
		return "index";
	}
	
	@RequestMapping("/loginPage")
	public String loginUser() {
		System.out.println("inside login User");
		return "login";
	}
	
	@RequestMapping("/newUser")
	public String registerUser() {
		System.out.println("inside register User");
		return "newuser";
	}
	
	@Autowired
	HttpSession session;
	
	@RequestMapping("/registerUser")
	public ModelAndView newUserRegistration(@ModelAttribute User user) 
	{
		System.out.println("Registration of New User");
		ModelAndView mv=new ModelAndView("login");
		String loginId = service.registerUser(user);
		System.out.println("Registered User's Login Id : " + loginId);

		return mv;
	}
	
	
	@PostMapping("/loginUser")
	public ModelAndView loginUser(@RequestParam String loginId, @RequestParam String password) 
	{
	    if (service.authenticateUser(loginId, password)) {
	    	ModelAndView mv = new ModelAndView("home");
	    	List<BankAccount> acc = bankinfo();
	    	session.setAttribute("accList", acc);
	    	return mv; 
	    } 
	    else 
	    {
	        ModelAndView mv = new ModelAndView("login");
	        mv.addObject("error", "Invalid LoginId or Password");
	        return mv;
	    }
	}
	  
		public List<BankAccount> bankinfo() 
		{
			List<BankAccount> acc= new ArrayList<>();
			
			BankAccount account1=new BankAccount(2010110, "Gaurav", "Patil", "1234", 1500);
			BankAccount account2=new BankAccount(2010169, "Anshul", "Khambe", "4321", 2000);
			BankAccount account3=new BankAccount(2010145, "Ganesh", "Pansare", "9513", 3000);
			BankAccount account4=new BankAccount(2010127, "Rohit", "Shinde", "7531", 500);
			BankAccount account5=new BankAccount(2010181, "Hrishikesh", "Nandgaokar", "9876", 1200);
			acc=new ArrayList<BankAccount>();
			acc.add(account1);
			acc.add(account2);
			acc.add(account3);
			acc.add(account4);
			acc.add(account5);
			return acc;
		}

}
