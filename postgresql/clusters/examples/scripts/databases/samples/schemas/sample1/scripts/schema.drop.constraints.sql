SET search_path TO sample1,public;

ALTER TABLE customer DROP CONSTRAINT customer_customer_id_pkey;
DROP SEQUENCE customer_customer_id_seq;