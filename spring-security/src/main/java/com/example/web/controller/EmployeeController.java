package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.EmployeeService;
import com.example.web.form.EmployeeRegisterForm;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/register")
	public String form(Model model) {
		model.addAttribute("depts", employeeService.getAllDepartments());
		model.addAttribute("positions", employeeService.getAllPositions());
		
		return "emp/register-form";
	}
	
	@PostMapping("/register")
	public String register(EmployeeRegisterForm employeeRegisterForm) {
		employeeService.saveEmployee(employeeRegisterForm);
		
		return "redirect:/emp/registered";
	}
	
	@GetMapping("/registered")
	public String registered() {
		return "emp/completed";
	}
}
