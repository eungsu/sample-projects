create table sample_products (
    product_no integer,
    product_name varchar(100),
    product_maker varchar(100),
    product_price integer,
    product_discount_price integer,
    product_stock integer,
    product_created_date date ,
    product_updated_date date ,
    primary key (product_no)
);

create sequence products_seq start with 10000;