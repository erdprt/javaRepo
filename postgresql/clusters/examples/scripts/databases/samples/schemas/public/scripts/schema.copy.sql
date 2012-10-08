SET search_path TO public;

COPY customer FROM 'G:\work\postgresql\clusters\examples\scripts\databases\samples\schemas\sample1\datas\customer.csv' WITH DELIMITER as ';' CSV HEADER;