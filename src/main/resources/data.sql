-- TODO 초기 구축 완료되면 제거한다
drop database timedeal;
create database timedeal;


USE timedeal;

-- TODO 왜 초기 실행이 적용이 안되지..
START TRANSACTION;

-- 테이블 설계
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
    id bigint not null,
    type varchar(20) not null,
    primary key (id)
);
create table role_permission(
    id bigint not null auto_increment,
    role_id bigint not null,
    permission_id bigint not null,
    primary key (id)
);
create table permission(
    id bigint not null auto_increment,
    name varchar(127) not null,
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
    product_id bigint not null,
    sale_start_at datetime not null,
    sale_end_at datetime not null,
    primary key (id)
);
create table member_product(
    id bigint not null auto_increment,
    buyer_id bigint not null,
    product_id bigint not null,
    primary key (id)
);
alter table member add foreign key (role_id) references role (id);
alter table role_permission add foreign key (role_id) references role (id);
alter table role_permission add foreign key (permission_id) references permission (id);
alter table product add foreign key (seller_id) references member (id);
alter table sale_time add foreign key (product_id) references product (id);
alter table member_product add foreign key (buyer_id) references member (id);
alter table member_product add foreign key (product_id) references product (id);


-- 초기 데이터 구축
insert into permission (id, name, description) values --TODO 권한 쪽은 초기에 어떤 데이터를 넣어야 하는지 모름
(1, 'PRODUCT_CREATE', 'access for product creation'),
(2, 'PURCHASED_PRODUCT_LIST', 'access for list of purchased product');

insert into role (id, type) values -- auto_increment 칼럼에는 0 이 들어가지 않음 (왜?)
(0, 'ADMIN'),
(1, 'USER');

insert into role_permission (id, role_id, permission_id) values
(1, 0, 1),
(2, 1, 2);


COMMIT;