create table sample_users (
    user_id varchar(100),
    user_password varchar(100),
    user_name varchar(100),
    user_email varchar(255),
    user_picture varchar(100),
    user_provider_type varchar(20),
    user_role_type varchar(20),
    user_created_date date,
    user_updated_date date,
	primary key (user_id)
);
