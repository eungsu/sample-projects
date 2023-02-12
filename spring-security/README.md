# 스프링 시큐리티로 인증처리 구현

- 사용자와 직원(직원과 관리자)에 대한 인증처리를 지원하는 샘플 애플리케이션이다.

## 개발환경

구분| 버전 
---- | ------- 
java | 17
spring boot | 2.6.7
database | h2

## 데이터베이스 스키마와 샘플 데이터

### 데이터베이스마스키마

```sql
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
    auth varchar(20) not null,
    dept_no bigint(5) references sample_departments (dept_no),
    position_no bigint(5) references sample_positions (position_no),
    created_date date default now(),
    updated_date date default now(),
    primary key (id)
);
```

### 샘플 스키마

```sql
insert into sample_departments values(1000, '기획팀');
insert into sample_departments values(2000, '디자인팀');
insert into sample_departments values(3000, '개발팀');
insert into sample_departments values(4000, '테스트팀');

insert into sample_positions values(101, '사원');
insert into sample_positions values(102, '대리');
insert into sample_positions values(103, '과장');
insert into sample_positions values(104, '차장');
insert into sample_positions values(105, '부장');
insert into sample_positions values(106, '임원');

insert into sample_employees
values('emp1', '$2a$10$HBHJuiu69vd.G2pLa8Erd.NiFPgAc5VYg1uXXd.MOvcmmzpOxcRpe', '직원1', 'emp1@gmail.com', 'ROLE_EMPLOYEE', 1000, 101, now(), now());

insert into sample_employees
values('emp2', '$2a$10$HBHJuiu69vd.G2pLa8Erd.NiFPgAc5VYg1uXXd.MOvcmmzpOxcRpe', '직원2', 'emp1@gmail.com', 'ROLE_EMPLOYEE', 1000, 101, now(), now());

insert into sample_employees
values('admin1', '$2a$10$HBHJuiu69vd.G2pLa8Erd.NiFPgAc5VYg1uXXd.MOvcmmzpOxcRpe', '관리자1', 'emp1@gmail.com', 'ROLE_ADMIN', 1000, 104, now(), now());

insert into sample_employees
values('admin2', '$2a$10$HBHJuiu69vd.G2pLa8Erd.NiFPgAc5VYg1uXXd.MOvcmmzpOxcRpe', '관리자2', 'emp1@gmail.com', 'ROLE_ADMIN', 1000, 105, now(), now());

insert into sample_users
values('user1', '$2a$10$HBHJuiu69vd.G2pLa8Erd.NiFPgAc5VYg1uXXd.MOvcmmzpOxcRpe', '사용자1', 'user1@gmail.com', now(), now());
```

## 구현내용

- 사용자와 직원에 대한 인증처리는 같은 인증처리 프로세스로 구현한다.
- 사용자는 별도의 권한이 없다.
- 직원은 ROLE_EMPLOYEE와 ROLE_ADMIN 권한 중 하나를 가진다.
- 사용자 서비스는 인증된 사용자와 인증된 직원(직원 혹은 관리자)만 요청할 수 있다.
- 직원 서비스는 인증된 직원(직원 혹은 관리자)만 요청할 수 있다.
- 관리자 서비스는 인증된 직원(관리자)만 요청할 수 있다.

### 요청 URI

요청 URI | 내용 | 인증여부 | 필요권한
---- | ---- | ---- | ----
/ | 홈 화면 요청 | N | 없음
/user/register | 사용자 가입화면 요청 | N | 없음
/user/registered | 사용자 가입완료 화면 요청 N | 없음
/emp/register | 직원 가입화면 요청 | N | 없음
/emp/registered | 직원 가입완료 화면 요청 N | 없음
/login | 로그인 화면 요청 |N | 없음
/logout | 로그아웃 요청 | N | 없음
/user/home | 사용자 홈 화면 요청 | Y | 없음
/emp/home | 직원 홈 화면 요청 | Y | ROLE_EMPLOYEE 혹은 ROLE_ADMIN
/emp/admin | 관리자 홈 화면 요청 | Y | ROLE_ADMIN

## 주요 클래스

