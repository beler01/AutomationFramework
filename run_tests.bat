@echo off
cd c:\Users\Raj\eclipse-workspace\AutomationFramework
echo Starting Maven build...
c:\apache-maven-3.9.12\bin\mvn.cmd clean test -Dgroups=regression > maven_output.txt 2>&1
echo Maven build completed >> maven_output.txt
echo Exit code: %ERRORLEVEL% >> maven_output.txt
