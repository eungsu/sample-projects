package com.example.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {

	private String id;
	private String encryptPassword;
	private String type;
	private String name;
	private String email;
	private String authority;
	private int deptNo;
	private int positionNo;
	private Date createdDate;
	private Date updatedDate;

}