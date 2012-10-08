SET search_path TO sample1,public;

SELECT SETVAL('customer_customer_id_seq', (SELECT MAX(customer_id) FROM customer));
SELECT nextval('customer_customer_id_seq');
select currval('customer_customer_id_seq');
SELECT nextval('customer_customer_id_seq');
