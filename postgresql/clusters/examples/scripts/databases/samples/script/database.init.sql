CREATE USER user_samples WITH PASSWORD 'user_samples';
ALTER user user_samples WITH SUPERUSER;
CREATE TABLESPACE tbl_samples LOCATION 'G:\work\postgresql\clusters\examples\tablespaces\tbl_samples';
ALTER TABLESPACE tbl_samples OWNER TO user_samples;
CREATE DATABASE bas_samples OWNER user_samples TABLESPACE tbl_samples;
