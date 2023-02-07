create table sample_departments (
    dept_no bigint(5) not null,
    dept_name varchar(100)
);

create table sample_employees (
    emp_no bigint(5) not null auto_increment,
    emp_dept_no bigint(5) not null references sample_departments (dept_no),
    emp_email varchar(100) not null unique,
    emp_password varchar(20) not null,
    emp_name varchar(100) not null,
    emp_created_date date now(),
    emp_updated_date date now(),
    primary key (emp_no)
);

create table sample_todo_categories (
    cat_no bigint(5) not null,
    cat_name varchar(100) not null,
    primary key (cat_no)
);

create table sample_todos (
    todo_no bigint(5) not null auto_increment,
    todo_cat_no bigint(5) not null references sample_todo_categories (cat_no),
    todo_title varchar(255) not null,
    todo_description varchar(1000) not null,
    todo_due_date date not null

)