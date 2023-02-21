package com.example.dto;

import java.util.List;
import java.util.StringJoiner;

import com.example.vo.Employee;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeptListDto {

	private int deptId;
	private String deptName;
	private List<Employee> employees;
	
	public String getEmployeeNames() {
		StringJoiner joiner = new StringJoiner(", ");
		for (Employee emp : employees) {
			joiner.add(emp.getFirstName());
		}
		return joiner.toString();
	}
}
