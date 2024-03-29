package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.vo.User;

@Mapper
public interface UserMapper {

	void insertUser(User user);
	User getUserByNo(int no);
	User getUserByEmail(String email);
	void updateUser(User user);
}
