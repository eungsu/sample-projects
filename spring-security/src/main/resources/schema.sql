create table sample_users (
    id varchar(100) ,
    encrypt_password varchar(100) not null,
    name varchar(100) not null,
    email varchar(100) not null,
    created_date date default now(),
    updated_date date default now(),
    primary key (id)
);

create table sample_employees (
    id varchar(100) ,
    encrypt_password varchar(100) not null,
    name varchar(100) not null,
    email varchar(100) not null,
    authority varchar(20) not null,
    created_date date default now(),
    updated_date date default now(),
    primary key (id)
);