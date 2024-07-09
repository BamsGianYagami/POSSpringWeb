CREATE TABLE USER_INFO (
    id int,
    username varchar(30),
    name varchar(60),
    email varchar(255),
    password varchar(255),
    roles varchar(30),
    primary key (id)
);
CREATE TABLE STOCK (
    itemId int,
    itemName varchar(30),
    itemPrice float,
    qty int,
    unitCount varchar(30),
    primary key (itemId)
);

CREATE SEQUENCE STOCK_SEQUENCE
START WITH 1
INCREMENT BY 1
MINVALUE 1
NOCYCLE;

CREATE SEQUENCE USER_INFO_SEQUENCE
START WITH 1
INCREMENT BY 1
MINVALUE 1
NOCYCLE;

-- UNTUK RESTART
ALTER SEQUENCE STOCK_SEQUENCE RESTART WITH 1;
ALTER SEQUENCE USER_INFO_SEQUENCE RESTART WITH 1;