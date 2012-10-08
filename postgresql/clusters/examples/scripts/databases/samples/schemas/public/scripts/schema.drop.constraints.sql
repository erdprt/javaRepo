SET search_path TO public;

ALTER TABLE customer DROP CONSTRAINT customer_customer_id_pkey;
DROP SEQUENCE customer_customer_id_seq;