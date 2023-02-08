create table sample_users (
    id varchar(100) ,
    encrypt_password varchar(100) not null,
    name varchar(100) not null,
    email varchar(100) not null,
    created_date date default now(),
    updated_date date default now(),
    primary key (id)
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
    id varchar(100) ,
    encrypt_password varchar(100) not null,
    name varchar(100) not null,
    email varchar(100) not null,
    authority varchar(20) not null,
    dept_no bigint(5) references sample_departments (dept_no),
    position_no bigint(5) references sample_positions (position_no),
    created_date date default now(),
    updated_date date default now(),
    primary key (id)
);