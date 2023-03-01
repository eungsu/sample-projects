package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.annotation.Login;
import com.example.security.user.LoginUser;
import com.example.service.UserService;
import com.example.vo.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/info")
	public String info(@Login LoginUser loginUser, Model model) {
		User user = userService.getUser(loginUser.getId());
		model.addAttribute("user", user);
		
		return "user/info";
	}
}
