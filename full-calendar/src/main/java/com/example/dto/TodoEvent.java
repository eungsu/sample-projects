package com.example.dto;

import java.util.Date;

import com.example.util.DateUtils;
import com.example.vo.Todo;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class TodoEvent {

	@JsonIgnore
	private Todo todo;

	public String getId() {
		return String.valueOf(todo.getNo());
	}
	
	public boolean getAllDay() {
		return "Y".equals(todo.getAllDay());
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
	
	public int getCatNo() {
		return todo.getCatNo();
	}
	
	public String getDescription() {
		return todo.getDescription();
	}
}
