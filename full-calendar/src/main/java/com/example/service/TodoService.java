package com.example.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.TodoEvent;
import com.example.mapper.TodoMapper;
import com.example.vo.Category;
import com.example.vo.Employee;
import com.example.vo.Status;
import com.example.vo.Todo;
import com.example.web.form.RegisterTodoForm;

@Service
public class TodoService {
	
	private final int TODO_STATUS_NO_FOR_SCHEDULED = 101;

	@Autowired
	private TodoMapper todoMapper;
	
	public TodoEvent addTodo(Employee employee, RegisterTodoForm todoForm) {
		Todo todo = new Todo();
		BeanUtils.copyProperties(todoForm, todo);
		todo.setEmpNo(employee.getNo());
		todo.setStatusNo(TODO_STATUS_NO_FOR_SCHEDULED);
		
		todoMapper.insertTodo(todo);
		
		return todoMapper.getTodoEventByNo(todo.getNo());
	}
	

	public List<Category> getAllCategories() {
		return todoMapper.getAllCategories();
	}
	
	public List<Status> getAllStatuses() {
		return todoMapper.getAllStatuses();
	}


	public List<TodoEvent> getEvents(Map<String, Object> param) {
		return todoMapper.getTodoEvents(param);
	}


	public void deleteTodo(int empNo, int todoNo) {
		Todo todo = todoMapper.getTodoByNo(todoNo);
		if (todo == null) {
			throw new RuntimeException("일정정보가 존재하지 않습니다.");
		}
		if (todo.getEmpNo() != empNo) {
			throw new RuntimeException("다른 직원의 일정정보는 삭제할 수 없습니다.");
		}
		todoMapper.deleteTodo(todoNo);
	}
}
