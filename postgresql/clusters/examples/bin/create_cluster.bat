call setclasspath.bat
echo 'create cluster'
rem initdb.exe -E %CLUSTER_ENCODING% -X %CLUSTER_INSTANCE%/transactions 
initdb.exe -X %CLUSTER_INSTANCE%\transactions