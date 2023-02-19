create table sample_departments (
    dept_no integer,
    dept_name varchar(100),
    primary key (dept_no)
);

create table sample_todo_categories (
    cat_no integer ,
    cat_name varchar(100),
    primary key (cat_no)
);

create table sample_todo_status (
	status_no integer,
	status_name varchar(100),
	primary key (status_no)
);

create table sample_employees (
    emp_no integer,
    emp_id varchar(100),
    emp_password varchar(100),
    emp_name varchar(100),
    emp_email varchar(100),
    emp_created_date date ,
    emp_updated_date date ,
    emp_dept_no integer references sample_departments (dept_no),
    primary key (emp_no)
);

create table sample_todos (
    todo_no integer,
    todo_cat_no integer references sample_todo_categories (cat_no),
    todo_title varchar(255),
    todo_emp_no integer references sample_employees (emp_no),
    todo_description varchar(1000),
    todo_start_date char(10),
    todo_start_time char(5),
    todo_end_date char(10),
    todo_end_time char(5),
    todo_all_day char(1),
    todo_status_no integer,
    todo_created_date date,
    todo_updated_date date,
	primary key (todo_no)
);

create sequence employees_seq  start with 2010;
create sequence todos_seq start with 10001;