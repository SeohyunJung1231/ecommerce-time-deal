USE timedeal;

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
    user_id bigint not null,
    name varchar(20),
    sale_start_at datetime not null,
    sale_end_at datetime not null,
    price bigint not null,
    stock bigint not null,
    primary key (id)
);

COMMIT;