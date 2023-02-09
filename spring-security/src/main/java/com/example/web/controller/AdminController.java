package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.security.AuthenticatedUser;
import com.example.security.vo.LoginUser;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/home")
	public String home(@AuthenticatedUser LoginUser loginUser, Model model) {
		model.addAttribute("id", loginUser.getId());
		model.addAttribute("name", loginUser.getName());
		return "admin/home";
	}
}
