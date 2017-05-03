E:
Cd "E:\workspace_swfitlite\NordeaFramework"
set M2_HOME=E:\AllInstallablesEXE\OpenSource\QA tools\Maven\apache-maven-3.3.9-bin\apache-maven-3.3.9
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_121
set PATH=%PATH%;%M2_HOME%\bin;%JAVA_HOME%\bin
::mvn -Dgroups="Gtest" test
mvn test -DsuiteXmlFile=testng.xml
pause