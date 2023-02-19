package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.vo.Category;
import com.example.vo.Status;
import com.example.vo.Todo;

@Mapper
public interface TodoMapper {

	List<Category> getAllCategories();
	List<Status> getAllStatuses();
	void insertTodo(Todo todo);
	
}
