package com.example.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.service.EmployeeService;
import com.example.util.SessionUtils;
import com.example.vo.Employee;
import com.example.web.form.RegisterEmployeeForm;

@Controller
public class MainController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/calendar")
	public String calendar() {
		return "calendar";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("depts", employeeService.getAllDepartments());
		return "register-form";
	}
	
	@PostMapping("/register")
	public String register(@Valid RegisterEmployeeForm form, BindingResult errors) {
		System.out.println(form);
		System.out.println(errors);
		if (errors.hasErrors()) {
			return "register-form";
		}
		return "redirect:/registered";
	}
	
	@GetMapping("/registered")
	public String completed() {
		return "completed";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login-form";
	}
	
	@PostMapping("/login")
	public String login(String id, String password) {
		Employee employee = employeeService.login(id, password);
		SessionUtils.setAttribute("LOGIN_EMP", employee);
		
		return "redirect:/";
	}
}
