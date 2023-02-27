package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.vo.Employee;

@Mapper
public interface EmployeeMapper {

	Employee getEmployeeById(String empId);
}
