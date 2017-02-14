package com.yuyue_shop_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuyue_shop_manager.service.UserService;



@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/gologin")
	public String goLogin(){
		return "login";
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public Object login(String userAcount,String userPassword){
		System.out.println(userAcount);
		return userService.getUserByAccount(userAcount, userPassword);
	}
}
