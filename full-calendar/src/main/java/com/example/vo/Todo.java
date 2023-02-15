package com.example.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Todo {

	private int no;
	private int catNo;
	private String title;
	private int empNo;
	private String description;
	private String dueDate;
	private String startTime;
	private String endTime;
	private String allDay;
	private int statusNo;
	private Date createdDate;
	private Date updatedDate;
}
