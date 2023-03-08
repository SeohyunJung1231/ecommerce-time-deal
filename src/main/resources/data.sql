-- TODO 초기 구축 완료되면 제거한다
drop database timedeal;
create database timedeal;


USE timedeal;

-- TODO 왜 초기 실행이 적용이 안되지..
START TRANSACTION;

create table member(
    id bigint not null auto_increment,
    role_id bigint not null,
    account varchar(20) not null,
    name varchar(20),
    password varchar(20) not null,
    email varchar(255) not null,
    phone varchar(20),
    primary key (id)
);
create table role(
    id bigint not null auto_increment,
    type varchar(20) not null,
    primary key (id)
);
create table role_permission(
    id bigint not null auto_increment,
    role_id bigint not null,
    permission_id not null,
    primary key (id)
);
create table permission(
    id bigint not null auto_increment,
    name varchar(20) not null,
    description varchar(255),
    primary key (id)
);
create table product(
    id bigint not null auto_increment,
    seller_id bigint not null,
    name varchar(20),
    price bigint not null,
    stock bigint not null,
    primary key (id)
);
create table sale_time(
    id bigint not null auto_increment,
    sale_start_at datetime not null,
    sale_end_at datetime not null,
    primary key (id)
);
alter table member add foreign key (role_id) references role (id);
alter table role_permission add foreign key (role_id) references role (id);
alter table role_permission add foreign key (permission_id) references permission (id);
alter table product add foreign key (seller_id) references member (id);
alter table sales_time add foreign key (product_id) references product (id)

COMMIT;