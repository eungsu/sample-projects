package com.example.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {

	private String id;
	private String password;
	private String name;
	private String email;
	private String providerType;
	private String roleType;
	private Date createdDate;
	private Date updatedDate;
}
