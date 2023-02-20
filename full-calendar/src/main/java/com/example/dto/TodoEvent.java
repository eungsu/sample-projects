package com.example.dto;

import java.util.Date;

import com.example.util.DateUtils;
import com.example.vo.Todo;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * TodoEvent는 일정정보, 직원정보, 부서정보, 상태정보를 포함하는 객체다.
 * TodoEvent는 FullCalendar에서 일정표시에 필요한 정보를 제공하는 객체다.
 * FullCalendar는 id(일정식별정보), start(일정시작일자, 시작시간), end(일정종료일자, 종료시간), allDay(하루종일 여부), title(일정제목)를 이용해서 일정정보를 표시한다.
 * TodoEvent는 getter 메소드를 사용해서 Todo, Employee, Dapartment, Status에서 필요한 정보를 조회해서 fullCalendar로 제공한다.
*/
public class TodoEvent {

	@JsonIgnore
	private Todo todo;

	public String getId() {
		return String.valueOf(todo.getNo());
	}
	
	public boolean getAllDay() {
		return "Y".equals(todo.getAllDay());
	}
	
	public Date getStart() {
		return DateUtils.textToDate(todo.getStartDate(), todo.getStartTime());
	}
	
	public Date getEnd() {
		return DateUtils.textToDate(todo.getEndDate(), todo.getEndTime());
	}
	
	public String getTitle() {
		return todo.getTitle();
	}
	
	
}
