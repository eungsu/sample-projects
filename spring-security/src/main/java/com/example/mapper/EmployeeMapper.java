package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.vo.Department;
import com.example.vo.Employee;
import com.example.vo.Position;

@Mapper
public interface EmployeeMapper {
	
	List<Department> getAllDepartments();
	List<Position> getAllPositions();
	Employee getEmployeeById(String id);
	void insertEmployee(Employee employee);
}
