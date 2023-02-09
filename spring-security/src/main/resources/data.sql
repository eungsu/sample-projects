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
