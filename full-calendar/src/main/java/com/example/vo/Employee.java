package com.example.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Employee {

	private int no;
	private String id;
	private String password;
	private String name;
	private String email;
	private Date createdDate;
	private Date updateDate;
	private int deptNo;
}
