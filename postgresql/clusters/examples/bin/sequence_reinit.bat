call setclasspath.bat
echo 'reinit sequence database ' %1% ' schema ' %2%

SET DATABASE_NAME=%1%
SET SCHEMA_NAME=%2%
if "%SCHEMA_NAME%" == "" (set "SCHEMA_NAME=public")

psql.exe -d bas_%DATABASE_NAME% -f %CLUSTER_INSTANCE%\scripts\databases\%DATABASE_NAME%\schemas\%SCHEMA_NAME%\scripts\schema.sequence.reinit.sql
