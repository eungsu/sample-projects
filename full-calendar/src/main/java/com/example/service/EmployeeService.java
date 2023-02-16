package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.EmployeeMapper;
import com.example.vo.Department;
import com.example.vo.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	
	public List<Department> getAllDepartments() {
		return employeeMapper.getAllDepartments();
	}
	
	public Employee login(String empId, String password) {
		Employee employee = employeeMapper.getEmployeeById(empId);
		if (employee == null) {
			throw new RuntimeException("직원정보가 존재하지 않습니다.");
		}
		if (!employee.getPassword().equals(password)) {
			throw new RuntimeException("비밀번호가 일치하지 않습니다.");
		}
		return employee;
	}
}
