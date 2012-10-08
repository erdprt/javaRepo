call setclasspath.bat
echo 'create database' %1%
mkdir %CLUSTER_INSTANCE%\tablespaces\tbl_%1%

SET DATABASE_NAME=%1%

psql.exe -d postgres -f %CLUSTER_INSTANCE%\scripts\databases\%DATABASE_NAME%\script\database.init.sql