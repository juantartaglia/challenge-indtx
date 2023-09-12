create table price (id bigint not null, brand_id bigint not null, curr varchar(255) check (curr in ('EUR','USD')), end_date timestamp(6), price numeric(38,2), price_list_id bigint, priority integer, product_id bigint, start_date timestamp(6), primary key (id));
create sequence price_seq start with 1 increment by 50;

INSERT INTO PRICE (ID,BRAND_ID,START_DATE,END_DATE,PRICE_LIST_ID,PRODUCT_ID,PRIORITY,PRICE,CURR)
VALUES
(0,1,'2020-06-14 00:00:00+2','2020-12-31 23:59:59+2',1,35455,0,35.50,'EUR'),
(1,1,'2020-06-14 15:00:00+2','2020-06-14 18:30:00+2',2,35455,1,25.45,'EUR'),
(2,1,'2020-06-15 00:00:00+2','2020-06-15 11:00:00+2',3,35455,1,30.50,'EUR'),
(3,1,'2020-06-15 16:00:00+2','2020-12-31 23:59:59+2',4,35455,1,38.95,'EUR');