package com.example.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.dto.DeptListDto;
import com.example.mapper.DeptMapper;

@Controller
public class HomeController {

	@Autowired
	private DeptMapper deptMapper;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/depts")
	public String employees(Model model) {
		List<DeptListDto> dtos = deptMapper.getDeptList();
		System.out.println(dtos);
		model.addAttribute("dtos", dtos);
		return "depts";
	}
}
