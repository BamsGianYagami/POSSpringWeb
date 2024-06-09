CREATE TABLE USER_INFO (
    id int,
    name varchar(30),
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