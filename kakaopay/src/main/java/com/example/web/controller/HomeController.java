package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.service.UserService;
import com.example.vo.User;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

    @RequestMapping(path = "/")
    public String home() {
        return "home";
    }

    @GetMapping(path = "/loginform")
    public String loginform() {
        return "loginform";
    }

    @GetMapping(path = "/register")
    public String form() {
        return "registerform";
    }

    @PostMapping(path = "/register")
    public String register(@RequestParam(name = "name") String name,
    		@RequestParam(name = "email") String email,
    		@RequestParam(name = "password") String password) {
    	
    	try {
	    	User user = new User();
	    	user.setEmail(name);
	    	user.setEmail(email);
	    	user.setPassword(passwordEncoder.encode(password));
	    	
	    	userService.addNewUser(user);
    	} catch (RuntimeException e) {
    		return "redirect:/register?error";
    	}
    	
        return "redirect:/completed";
    }

    @GetMapping(path = "/completed")
    public String completed() {
        return "completed";
    }
}
