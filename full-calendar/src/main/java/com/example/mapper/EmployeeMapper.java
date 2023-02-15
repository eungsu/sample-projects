package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.vo.Department;
import com.example.vo.Employee;

@Mapper
public interface EmployeeMapper {

	List<Department> getAllDepartments();
	Department getDepartmentByNo(int deptNo);
	
	List<Employee> getAllEmployees();
	List<Employee> getEmployeesByDeptNo(int deptNo);
	Employee getEmployeeByNo(int empNo);
	Employee getEmployeeById(int empId);
	Employee getEmployeeByEmail(String empEmail);

	void insertEmployee(Employee employee);
}
