package com.example.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.TodoService;
import com.example.vo.Category;
import com.example.vo.Status;

@RestController
@RequestMapping("/todos")
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@ModelAttribute("categories")
	public List<Category> categories() {
		return todoService.getAllCategories();
	}
	
	@ModelAttribute("statuses")
	public List<Status> statuses() {
		return todoService.getAllStatuses();
	}
	
	
}
