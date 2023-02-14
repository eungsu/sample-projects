create table sample_users (
    user_id varchar(100) ,
    user_encrypt_password varchar(100) not null,
    user_name varchar(100) not null,
    user_email varchar(100) not null,
    user_created_date date default now(),
    user_updated_date date default now(),
    primary key (user_id)
);

create table sample_user_roles (
	user_id varchar(100) references sample_users(user_id),
	user_role_name varchar(100)
);

create table sample_departments (
    dept_no bigint(4),
    dept_name varchar(100),
    primary key (dept_no)
);

create table sample_positions (
    position_no bigint(4),
    position_name varchar(100),
    primary key (position_no)
);

create table sample_employees (
    employee_id varchar(100) ,
    employee_encrypt_password varchar(100) not null,
    employee_name varchar(100) not null,
    employee_email varchar(100) not null,
    employee_dept_no bigint(5) references sample_departments (dept_no),
    employee_position_no bigint(5) references sample_positions (position_no),
    employee_created_date date default now(),
    employee_updated_date date default now(),
    employee_primary key (employee_id)
);

create table sample_employee_roles (
	employee_id varchar(100) references sample_employees (employee_id),
	employee_role_name varchar(100)
);
