call setclasspath.bat
pg_ctl.exe -D %PGDATA% -l %CLUSTER_LOGS%\journal_applicatif.log stop