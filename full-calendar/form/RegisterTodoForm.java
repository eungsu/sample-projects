package com.example.web.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterTodoForm {

	private String title;
	private int catNo;
	private String allDay;
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	private String description;
	
}
