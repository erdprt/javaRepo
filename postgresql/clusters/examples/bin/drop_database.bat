call setclasspath.bat
echo 'drop database' %1%
SET NOM_BASE=%1%
psql.exe -d postgres -f %CLUSTER_INSTANCE%\scripts\databases\%NOM_BASE%\script\database.drop.sql
rmdir %CLUSTER_INSTANCE%\tablespaces\tbl_%1% 