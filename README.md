# wsdl2html

Converts WSDLs to html documents which are readable by everybody. 

Online version: [http://www.wsdl2html.com](http://www.wsdl2html.com) 
 

## Example: 

![v3-sample](doc/image/v3-sample.png)

Check the full html [here](https://rawgit.com/chenjianjx/wsdl2html/master/doc/sample/BbsWebService_v2.html). 


# How to run

## Run online

Run online if your WSDL has been hosted on a public site. Do it here: [http://www.wsdl2html.com](http://www.wsdl2html.com).

## Run as a command line tool

```shell

mvn package 
cd target 
unzip wsdl2html-some-version-jarset.zip -d /path/to/your/dir

# Go to the direction of extraction and you will see an executable file. Run it like, 

./wsdl2html.sh http://.../some?wsdl /path/to/your/html/directory  # or wsdl2html.bat for windows

```
## Run As  UI 

run wsdl2htmlForm.bat

![UI form](doc/image/FormUI.png)


## Run it inside your application

In your pom.xml, add the following: 

```xml

	<repositories>
		<repository>
			<id>jitpack.io</id>
			<url>https://jitpack.io</url>
		</repository>
		...
	</repositories>


	<dependencies>
		<dependency>
			<groupId>com.github.chenjianjx</groupId>
			<artifactId>wsdl2html</artifactId>
			<version>3.0.2</version>
		</dependency>
		...
	</dependencies>	

```



```java

//if you call this method in your code, make sure the jdk version you used to run your code is 
// no lower than that of the jdk used by your "wsimport" to run in shell

String html = org.jaxws.wsdl2html.service.Wsdl2Html.generateHtml(wsdlUrl); 
```  
