package com.example.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Customer {

	private String id;
	private String password;
	private String name;
	private Date createdDate;
	private Date updatedDate;
}
