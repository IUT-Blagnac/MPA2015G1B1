@echo off
REM This file generated by AspectJ installer
REM Created on Mon Nov 02 17:40:58 CET 2015 by nicolas

if "%JAVA_HOME%" == "" set JAVA_HOME=C:\Program Files (x86)\Java\jre7
if "%ASPECTJ_HOME%" == "" set ASPECTJ_HOME=D:\COURS\S3\MPA\Projet\GITHUB\MPA2015G1B1\sprint_5\tools\aspectj1.7

if exist "%JAVA_HOME%\bin\java.exe" goto haveJava
if exist "%JAVA_HOME%\bin\java.bat" goto haveJava
if exist "%JAVA_HOME%\bin\java" goto haveJava
echo java does not exist as %JAVA_HOME%\bin\java
echo please fix the JAVA_HOME environment variable
:haveJava
"%JAVA_HOME%\bin\java" -classpath "%ASPECTJ_HOME%\lib\aspectjtools.jar;%JAVA_HOME%\lib\tools.jar;%CLASSPATH%" -Xmx64M org.aspectj.tools.ajc.Main %1 %2 %3 %4 %5 %6 %7 %8 %9
