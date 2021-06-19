create table java_course.SHOP_ORDER
(
    ORDER_ID varchar(16) not null
        primary key,
    REMARK varchar(256) null,
    ORDER_PRICE decimal(14,4) not null,
    USER_ID varchar(16) not null,
    USER_NAME varchar(32) not null,
    EMAIL varchar(64) null,
    PHONE varchar(16) null,
    DELIVER_ADDRESS varchar(128) not null,
    IS_PAID tinyint(1) default 0 not null,
    IS_CANCELED tinyint(1) default 0 not null,
    IS_DELIVERED tinyint(1) default 0 not null,
    CREATE_TIME bigint not null,
    UPDATE_TIME bigint not null
);

create table java_course.SHOP_ORDER_DETAIL
(
    ORDER_ID varchar(16) not null,
    PRODUCT_ID varchar(16) not null,
    PRODUCT_PRICE_AT_ORDER decimal(14,4) not null,
    ORDER_QUANTITY int not null,
    CREATE_TIME bigint not null,
    UPDATE_TIME bigint not null
);

create table java_course.SHOP_PRODUCT
(
    PRODUCT_ID varchar(16) not null
        primary key,
    PRODUCT_NAME varchar(32) not null,
    PRODUCT_PRICE decimal(14,4) not null,
    DESCRIPTION varchar(256) null,
    CATEGORY varchar(16) not null,
    WAREHOUSE_ID varchar(16) not null,
    CREATE_TIME bigint not null,
    UPDATE_TIME bigint not null
);

create table java_course.SHOP_USER
(
    USER_ID varchar(16) not null
        primary key,
    USER_NAME varchar(32) not null,
    EMAIL varchar(64) null,
    PHONE varchar(16) null,
    DELIVER_ADDRESS varchar(128) not null,
    LAST_LOGIN bigint null,
    CREATE_TIME bigint not null,
    UPDATE_TIME bigint not null
);

create table java_course.SHOP_WAREHOUSE
(
    WAREHOUSE_ID varchar(16) not null
        primary key,
    WAREHOUSE_ADDRESS varchar(128) not null,
    MANAGER varchar(32) null,
    CREATE_TIME bigint not null,
    UPDATE_TIME bigint not null
);

create table java_course.SHOP_WAREHOUSE_STACK
(
    WAREHOUSE_ID varchar(16) not null,
    PRODUCT_ID varchar(16) not null,
    PRODUCT_QUANTITY int not null,
    CREATE_TIME bigint not null,
    UPDATE_TIME bigint not null,
    constraint SHOP_WAREHOUSE_STACK_pk
        unique (WAREHOUSE_ID, PRODUCT_ID)
);


