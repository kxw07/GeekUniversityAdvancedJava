create table java_course.SHOP_USER
(
    USER_ID varchar(16) not null
        primary key,
    USER_NAME varchar(32) null,
    EMAIL varchar(64) null,
    PHONE varchar(16) null,
    LAST_LOGIN timestamp null,
    CREATE_TIME timestamp default CURRENT_TIMESTAMP null,
    UPDATE_TIME timestamp default CURRENT_TIMESTAMP null
);

create table java_course.SHOP_ORDER
(
    ORDER_ID varchar(16) not null
        primary key,
    USER_ID varchar(16) not null,
    REMARK varchar(256) null,
    ORDER_PRICE decimal(14,4) null,
    DELIVER_ADDRESS varchar(128) not null,
    IS_PAID tinyint(1) null,
    IS_CANCELED tinyint(1) null,
    IS_DELIVERED tinyint(1) null,
    CREATE_TIME timestamp default CURRENT_TIMESTAMP null,
    UPDATE_TIME timestamp default CURRENT_TIMESTAMP null
);

create table java_course.SHOP_ORDER_DETAIL
(
    ORDER_ID varchar(16) not null,
    PRODUCT_ID varchar(16) not null,
    ORDER_QUANTITY int null,
    CREATE_TIME timestamp default CURRENT_TIMESTAMP null,
    UPDATE_TIME timestamp default CURRENT_TIMESTAMP null
);

create table java_course.SHOP_PRODUCT
(
    PRODUCT_ID varchar(16) not null
        primary key,
    PRODUCT_NAME varchar(32) null,
    DESCRIPTION varchar(256) null,
    PRODUCT_PRICE decimal(14,4) null,
    WAREHOUSE_ID varchar(16) null,
    WAREHOUSE_QUANTITY int null,
    CREATE_TIME timestamp default CURRENT_TIMESTAMP null,
    UPDATE_TIME timestamp default CURRENT_TIMESTAMP null
);

create table java_course.SHOP_WAREHOUSE
(
    WAREHOUSE_ID varchar(16) not null
        primary key,
    WAREHOUSE_ADDRESS varchar(128) not null,
    MANAGER varchar(32) null,
    CREATE_TIME timestamp default CURRENT_TIMESTAMP null,
    UPDATE_TIME timestamp default CURRENT_TIMESTAMP null
);

