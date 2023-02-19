package com.example.dto;

import java.util.Date;

import com.example.util.DateUtils;
import com.example.vo.Department;
import com.example.vo.Employee;
import com.example.vo.Status;
import com.example.vo.Todo;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class TodoEvent {

	@JsonIgnore
	private Todo todo;
	@JsonIgnore
	private Employee employee;
	@JsonIgnore
	private Department department;
	@JsonIgnore
	private Status status;

	public String getId() {
		return String.valueOf(todo.getNo());
	}
	
	public boolean getAllDay() {
		return todo.getAllDay().equals("Y");
	}
	
	public Date getStart() {
		return DateUtils.textToDate(todo.getStartDate(), todo.getStartTime());
	}
	
	public Date getEnd() {
		return DateUtils.textToDate(todo.getEndDate(), todo.getEndTime());
	}
	
	public String getTitle() {
		return todo.getTitle();
	}
	
	
}
