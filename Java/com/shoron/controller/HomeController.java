package com.shoron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shoron.service.LoginService;

@Controller
public class HomeController {

	//Login Service instance by using simple method format
//	LoginService service= new LoginService();
	
	//Set the login service- Autowiring
	@Autowired
	LoginService service;
	
	@RequestMapping(value="/")
	public String hello(){
		return "index";
	}
	
	@RequestMapping(value="/login", method= RequestMethod.GET)
	public String showLoginPage(){
		return "login";
	}
	
	@RequestMapping(value="/login", method= RequestMethod.POST)
	public String handleLoginRequest(@RequestParam String name,
			@RequestParam String password,
			Model model){
		
		if(service.validateUser(name, password)){
			model.addAttribute("name",name);
			model.addAttribute("password",password);
			return "welcome";
		}
		else{
			model.addAttribute("errorMessage","Invalid Username or Password");
				return "login";		
		}
		
	
	}
	
}
