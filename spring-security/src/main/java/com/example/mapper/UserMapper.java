package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.vo.Role;
import com.example.vo.User;

@Mapper
public interface UserMapper {

	User getUserById(String id);
	List<Role> getUserRolesByUserId(String id);
	void insertUser(User user);
	void insertUserRole(Role role);
}
