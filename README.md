# wsdl2html

Converts WSDLs to html documents which are readable by everybody. 
 

## Example: 

![v3-sample](doc/image/v3-sample.png)

Check the full html [here](https://rawgit.com/chenjianjx/wsdl2html/master/doc/sample/BbsWebService_v2.html). 


# How to run

## Run as a command line tool

```bash

mvn package 
unzip target/wsdl2html*jarset.zip -d /path/to/your/dir
cd /path/to/your/dir/wsdl2html*
./wsdl2html.sh http://.../some?wsdl /path/to/your/html/directory  
# or wsdl2html.bat for windows

```

## Run with docker

```bash
docker build -t wsdl2html .
docker run --rm \
  -v $(pwd)/output:/usr/src/output \
  wsdl2html http://.../some?wsdl
```

## Run it inside your application

In your pom.xml, add the following: 

```xml

	<dependencies>
		...	
		<dependency>
			<groupId>com.github.chenjianjx</groupId>
			<artifactId>wsdl2html</artifactId>
			<version>4.0.0</version>
		</dependency>
		...
	</dependencies>	

```



```java

//Note: if you call this method in your code, make sure the jdk version you used to run your code is 
// no lower than that of the jdk used by your "wsimport" to run in shell

String html = org.jaxws.wsdl2html.service.Wsdl2Html.generateHtml(wsdlUrl); 
```  
