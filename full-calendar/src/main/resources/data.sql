insert into sample_departments values(10, '기획팀');
insert into sample_departments values(20, '디자인팀');
insert into sample_departments values(30, '분석설계팀');
insert into sample_departments values(40, '개발팀');
insert into sample_departments values(50, '테스트팀');
insert into sample_departments values(60, '운영팀');

insert into sample_todo_categories values (101, '회의');
insert into sample_todo_categories values (102, '발표');
insert into sample_todo_categories values (103, '출장');
insert into sample_todo_categories values (104, '외근');
insert into sample_todo_categories values (105, '교육');
insert into sample_todo_categories values (106, '휴가');
insert into sample_todo_categories values (109, '병가');
insert into sample_todo_categories values (110, '보고');
insert into sample_todo_categories values (111, '연차');
insert into sample_todo_categories values (112, '재택');

insert into sample_todo_status values (1001, '예정');
insert into sample_todo_status values (1002, '진행');
insert into sample_todo_status values (1003, '완료');
insert into sample_todo_status values (1004, '종료');
insert into sample_todo_status values (1005, '수정');
insert into sample_todo_status values (1006, '삭제');

insert into sample_employees values (2001, 'emp1', 'zxcv1234', '직원1', 'emp1@naver.com', now(), now(), 10);
insert into sample_employees values (2002, 'emp2', 'zxcv1234', '직원2', 'emp2@naver.com', now(), now(), 10);
insert into sample_employees values (2003, 'emp3', 'zxcv1234', '직원3', 'emp3@naver.com', now(), now(), 10);
insert into sample_employees values (2004, 'emp4', 'zxcv1234', '직원4', 'emp4@naver.com', now(), now(), 20);
insert into sample_employees values (2005, 'emp5', 'zxcv1234', '직원5', 'emp5@naver.com', now(), now(), 20);
insert into sample_employees values (2006, 'emp6', 'zxcv1234', '직원6', 'emp6@naver.com', now(), now(), 30);
insert into sample_employees values (2007, 'emp7', 'zxcv1234', '직원7', 'emp7@naver.com', now(), now(), 30);
insert into sample_employees values (2008, 'emp8', 'zxcv1234', '직원8', 'emp8@naver.com', now(), now(), 30);
insert into sample_employees values (2009, 'emp9', 'zxcv1234', '직원9', 'emp9@naver.com', now(), now(), 40);

insert into sample_todos 
(todo_no, todo_cat_no, todo_title, todo_emp_no, todo_description, todo_all_day, todo_start_date, todo_start_time, todo_end_date, todo_end_time, todo_status_no, todo_created_date, todo_updated_date) 
values 
(next value for todos_seq, 101, '휴가', 2001, '연차휴가하기', 'Y', '2023-01-15', null, '2023-01-18', null, 1004, now(), now());

insert into sample_todos 
(todo_no, todo_cat_no, todo_title, todo_emp_no, todo_description, todo_all_day, todo_start_date, todo_start_time, todo_end_date, todo_end_time, todo_status_no, todo_created_date, todo_updated_date) 
values 
(next value for todos_seq, 101, '교육1', 2001, '직무 적성 교육', 'N', '2023-01-28', '09:00', '2023-01-31', '13:00', 1004, now(), now());

insert into sample_todos 
(todo_no, todo_cat_no, todo_title, todo_emp_no, todo_description, todo_all_day, todo_start_date, todo_start_time, todo_end_date, todo_end_time, todo_status_no, todo_created_date, todo_updated_date) 
values 
(next value for todos_seq, 101, '회의1', 2001, '새 프로젝트 회의', 'N', '2023-02-04', '08:00', '2023-02-04', '09:00', 1004, now(), now());


insert into sample_todos 
(todo_no, todo_cat_no, todo_title, todo_emp_no, todo_description, todo_all_day, todo_start_date, todo_start_time, todo_end_date, todo_end_time, todo_status_no, todo_created_date, todo_updated_date) 
values 
(next value for todos_seq, 101, '회의2', 2001, '새 프로젝트 회의', 'N', '2023-02-10', '09:00', '2023-02-10', '12:00', 1003, now(), now());


insert into sample_todos 
(todo_no, todo_cat_no, todo_title, todo_emp_no, todo_description, todo_all_day, todo_start_date, todo_start_time, todo_end_date, todo_end_time, todo_status_no, todo_created_date, todo_updated_date) 
values 
(next value for todos_seq, 101, '회의3', 2001, '새 프로젝트 회의', 'N', '2023-02-20', '11:00', '2023-02-20', '12:00', 1001, now(), now());


insert into sample_todos 
(todo_no, todo_cat_no, todo_title, todo_emp_no, todo_description, todo_all_day, todo_start_date, todo_start_time, todo_end_date, todo_end_time, todo_status_no, todo_created_date, todo_updated_date) 
values 
(next value for todos_seq, 102, '발표1', 2001, '스토리 보드 발표', 'N', '2023-02-22', '09:00', '2023-02-23', '11:00', 1001, now(), now());


insert into sample_todos 
(todo_no, todo_cat_no, todo_title, todo_emp_no, todo_description, todo_all_day, todo_start_date, todo_start_time, todo_end_date, todo_end_time, todo_status_no, todo_created_date, todo_updated_date) 
values 
(next value for todos_seq, 103, '출장1', 2001, '대전 사업소 출장', 'Y', '2024-02-27', null, '2023-02-27', null, 1001, now(), now());


insert into sample_todos 
(todo_no, todo_cat_no, todo_title, todo_emp_no, todo_description, todo_all_day, todo_start_date, todo_start_time, todo_end_date, todo_end_time, todo_status_no, todo_created_date, todo_updated_date) 
values 
(next value for todos_seq, 104, '외근1', 2001, '외부 미팅하기1', 'N', '2023-02-28', '09:00', '2023-02-28', '12:00', 1001, now(), now());

insert into sample_todos 
(todo_no, todo_cat_no, todo_title, todo_emp_no, todo_description, todo_all_day, todo_start_date, todo_start_time, todo_end_date, todo_end_time, todo_status_no, todo_created_date, todo_updated_date) 
values 
(next value for todos_seq, 104, '외근2', 2001, '외부 미팅하기2', 'N', '2023-02-28', '15:00', '2023-02-28', '18:00', 1001, now(), now());


