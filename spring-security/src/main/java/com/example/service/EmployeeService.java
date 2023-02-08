package com.example.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.mapper.EmployeeMapper;
import com.example.vo.Department;
import com.example.vo.Employee;
import com.example.vo.Position;
import com.example.web.form.EmployeeRegisterForm;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<Department> getAllDepartments() {
		return employeeMapper.getAllDepartments();
	}
	
	public List<Position> getAllPositions() {
		return employeeMapper.getAllPositions();
	}
	
	public void saveEmployee(EmployeeRegisterForm employeeRegisterForm) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeRegisterForm, employee);
		employee.setEncryptPassword(passwordEncoder.encode(employeeRegisterForm.getPassword()));
		
		employeeMapper.insertEmployee(employee);
	}

}
