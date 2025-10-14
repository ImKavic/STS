@echo off
echo ===============================
echo Testing Database Connection...
echo ===============================

rem Compile Java source
javac -cp "lib\mssql-jdbc.jar" src\main\com\sts\App.java -d bin

rem Run the App
java -cp "bin;lib\mssql-jdbc.jar" com.sts.App

echo.
echo ===============================
echo Test complete.
pause
