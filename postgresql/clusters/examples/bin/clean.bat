call setclasspath.bat
cd %CLUSTER_INSTANCE%\logs
RMDIR /S /Q .
cd %CLUSTER_INSTANCE%\transactions
RMDIR /S /Q .
cd %CLUSTER_INSTANCE%\tablespaces
RMDIR /S /Q .
cd %CLUSTER_INSTANCE%\cluster
RMDIR /S /Q .
cd %CLUSTER_INSTANCE%\bin