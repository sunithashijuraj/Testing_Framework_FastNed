# Testing_Framework_FastNed# Fastned Browser Automation Framework

Browser Automation using TestNG Framework

This is a Java Selenium test automation solution for https://fastned.com/nl website

## Technology Stack
-Java
-Maven
-TestNG

##PreRequisites
-Java -Version 11.X
-Maven -Version 3.8.6

##Project Directory
```bash
src
    +test
      +java
        +fastNedAutomation    Testcase Class File with TestNG Annotations
pom.xml                       Maven dependency File
testng.xml                    TestRunner class for TestNG framework
```

##Installation and Test Execution

-Clone the project with below command

```
git clone -b Master (https://github.com/sunithashijuraj/Testing_Framework_FastNed)

```

Open the project in any IDE Eclipse/IntelliJ.
Navigate to "fastnedframework" folder where we have pom.xml

Run the below command where "-DExecutionMode" parameter value can be updated "local" or "remote".

For the parameter value "local" - you can see the browser and navigation of automation to the given url and 
verify the test results in the Terminal
For the parameter value "remote" - It would be in headless mode, hence browser navigation in the screen will not be visible, 
instead only test results will be displayed in the Terminal Screen.

```
mvn clean test -DExecutionMode="local"
```

After the execution, The Generated HTML report will get opened in the system default browser.

##  Gitlab integration for CI CD (.gitlab-ci.yml)
- Gitlab source code (https://gitlab.com/sunithashijuraj/FastNed)
- Below are the Sample Screenshots of successful integration with CICD pipeline in GITLAB and Generated HTML report
![](C:\Users\sunit\Desktop\Pipeline_success_report.png)
![](C:\Users\sunit\Desktop\Pipeline_Job_success_report.png)
