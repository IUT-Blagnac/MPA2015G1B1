@echo off
REM This file generated by AspectJ installer
REM Created on Mon May 25 20:59:18 CEST 2015 by Kaze

if "%JAVA_HOME%" == "" set JAVA_HOME=C:\Program Files (x86)\Java\jre7
if "%ASPECTJ_HOME%" == "" set ASPECTJ_HOME=c:\aspectj1.7

if exist "%JAVA_HOME%\bin\java.exe" goto haveJava
if exist "%JAVA_HOME%\bin\java.bat" goto haveJava
if exist "%JAVA_HOME%\bin\java" goto haveJava
echo java does not exist as %JAVA_HOME%\bin\java
echo please fix the JAVA_HOME environment variable
:haveJava
"%JAVA_HOME%\bin\java" -classpath "%ASPECTJ_HOME%\lib\aspectjtools.jar;%JAVA_HOME%\lib\tools.jar;%CLASSPATH%" -Xmx64M org.aspectj.tools.ajdoc.Main %1 %2 %3 %4 %5 %6 %7 %8 %9
