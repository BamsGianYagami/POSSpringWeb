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
    item_id int,
    item_name varchar(30),
    item_price float,
    qty int,
    unit_count varchar(30),
    primary key (item_id)
);
CREATE TABLE SHOPPING_CART(
    USERNAME VARCHAR(30) NOT NULL,
    ITEM_ID INT NOT NULL,
    QTY INT NOT NULL,
    PRIMARY KEY (USERNAME, ITEM_ID)
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