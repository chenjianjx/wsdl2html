# wsdl2html

Converts WSDLs to html documents which are readable by everybody. 
 

## Example: 

![v3-sample](doc/image/v3-sample.png)

Check the full html [here](https://rawgit.com/chenjianjx/wsdl2html/master/doc/sample/BbsWebService_v2.html). 


# How to run

## Run as a command line tool

Note:  minimum jdk (not just jre) version: 1.8

Clone or download the code

```bash
cd wsdl2html
mvn clean package 
unzip target/wsdl2html*jarset.zip -d /path/to/your/dir
cd /path/to/your/dir/wsdl2html*
./wsdl2html.sh http://.../some?wsdl /path/to/your/html/directory  
# or wsdl2html.bat for windows

```

## Run with docker

Clone or download the code

```bash
./run-in-docker.sh
```

## Run it inside your application

Add the maven/gradle dependency. See: https://mvnrepository.com/artifact/com.github.chenjianjx/wsdl2html
 
Then, 

```java
String html = org.jaxws.wsdl2html.service.Wsdl2Html.generateHtml(wsdlUrl); 
```  
