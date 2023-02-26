package com.example.web.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterEmployeeForm {
	private int deptNo;
	private String id;
	private String password;
	private String name;
	private String email;
}
