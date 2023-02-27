package com.example.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.annotation.Login;
import com.example.dto.TodoEvent;
import com.example.service.TodoService;
import com.example.vo.Employee;
import com.example.web.form.RegisterTodoForm;

@RestController
@RequestMapping("/todos")
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@PostMapping("/add")
	public TodoEvent addTodo(@Login Employee employee, @RequestBody RegisterTodoForm form) {
		return todoService.addTodo(employee, form);
	}
	
	@GetMapping("/delete")
	public void deleteTodo(@Login Employee employee, @RequestParam("todoNo") int todoNo) {
		todoService.deleteTodo(employee.getNo(), todoNo);
	}
	
	@GetMapping("/events")
	public List<TodoEvent> getEvents(@Login Employee employee, 
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern =  "yyyy-MM-dd") Date endDate) {
		
		Map<String, Object> param = new HashMap<>();
		param.put("empNo", employee.getNo());
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		
		return todoService.getEvents(param);
	}
	
}
