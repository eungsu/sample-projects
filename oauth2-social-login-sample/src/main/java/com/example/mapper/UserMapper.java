package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.vo.User;

@Mapper
public interface UserMapper {

	User getUserById(String userId);
	void insertUser(User user);
	void updateUser(User user);
}
