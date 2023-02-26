create table sample_departments (
    dept_no integer,
    dept_name varchar(100),
    primary key (dept_no)
);

create table sample_employees (
    emp_id varchar(100),
    emp_password varchar(100),
    emp_name varchar(100),
    emp_email varchar(100),
    emp_created_date date ,
    emp_updated_date date ,
    emp_dept_no integer references sample_departments (dept_no),
    primary key (emp_id)
);

create table sample_customers (
    customer_id varchar(100),
    customer_password varchar(100),
    customer_name varchar(100),
    customer_created_date date,
    customer_updated_date date,
	primary key (customer_id)
);
