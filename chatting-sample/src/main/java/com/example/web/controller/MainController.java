package com.example.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.service.CustomerService;
import com.example.service.EmployeeService;
import com.example.util.SessionUtils;
import com.example.vo.Customer;
import com.example.vo.Employee;
import com.example.web.websocket.ChatSocketHandler;

@Controller
public class MainController {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	CustomerService customerService;
	@Autowired
	ChatSocketHandler chatSocketHandler;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/customer-chat")
	public String customeChat() {
		return "customers";
	}
	
	@GetMapping("/employee-chat")
	public String employeeChat(Model model) {
		return "employees";
	}
	
	@GetMapping("/login")
	public String loginEmployee() {
		return "login-form";
	}
	
	@PostMapping("/login")
	public String loginEmployee(String id, String password, String type) {
		if ("고객".equals(type)) {
			Customer customer = customerService.login(id, password);
			SessionUtils.setAttribute("LOGIN_ID", customer.getId());
			SessionUtils.setAttribute("LOGIN_NAME", customer.getName());
			SessionUtils.setAttribute("LOGIN_TYPE", "고객");
			
		} else if ("직원".equals(type)) {
			Employee employee = employeeService.login(id, password);
			SessionUtils.setAttribute("LOGIN_ID", employee.getId());
			SessionUtils.setAttribute("LOGIN_NAME", employee.getName());
			SessionUtils.setAttribute("LOGIN_TYPE", "직원");
		}
		
		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logoutEmployee(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
}
