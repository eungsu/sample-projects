package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.TodoMapper;
import com.example.vo.Category;
import com.example.vo.Status;
import com.example.web.form.RegisterTodoForm;

@Service
public class TodoService {

	@Autowired
	private TodoMapper todoMapper;
	
	public void addTodo(RegisterTodoForm todoForm) {
		
	}

	public List<Category> getAllCategories() {
		return todoMapper.getAllCategories();
	}
	
	public List<Status> getAllStatuses() {
		return todoMapper.getAllStatuses();
	}
}
