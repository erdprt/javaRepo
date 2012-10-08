SET search_path TO public;

ALTER TABLE customer ADD CONSTRAINT customer_customer_id_pkey PRIMARY KEY (customer_id);
CREATE SEQUENCE customer_customer_id_seq START 1 INCREMENT BY 1 OWNED BY customer.customer_id;