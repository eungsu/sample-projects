package com.example.web.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRegisterForm {

	private String auth;
	private String id;
	private String password;
	private String name;
	private String email;
	private int deptNo;
	private int positionNo;

}
