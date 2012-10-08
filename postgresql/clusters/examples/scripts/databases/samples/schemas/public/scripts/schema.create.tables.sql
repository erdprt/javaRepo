SET search_path TO public;

create table customer (
    customer_id                     integer                       ,
    title                           char(4)                       ,
    fname                           varchar(32)                   ,
    lname                           varchar(32)           not null,
    addressline                     varchar(64)                   ,
    town                            varchar(32)                   ,
    zipcode                         char(10)              not null,
    phone                           varchar(16)
);
