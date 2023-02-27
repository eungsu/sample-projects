package com.example.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.TodoEvent;
import com.example.vo.Category;
import com.example.vo.Status;
import com.example.vo.Todo;

@Mapper
public interface TodoMapper {

	List<Category> getAllCategories();
	List<Status> getAllStatuses();
	void insertTodo(Todo todo);
	List<TodoEvent> getTodoEvents(Map<String, Object> param);
	TodoEvent getTodoEventByNo(int todoNo);
	Todo getTodoByNo(int todoNo);
	void deleteTodo(int todoNo);
	
}
