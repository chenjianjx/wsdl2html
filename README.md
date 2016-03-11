# Introduction

__You've made a web service or sombody has given you a WSDL. So what are the input and output?__ Check the WSDL? No, that's not readable. Instead you can use __wsdl2html__ to __generate a readable HTML page from a WSDL url, such as__ 

![Alt html-table](/doc/image/generated-place-order.png?raw=true)


See? You will love it!  Here is a [colorful version](/doc/single-file.html). 

__Sometimes as a developer you haven't got the WSDL ready.__ Instead you've just finished the jax-ws stubs:

![Alt service](/doc/image/stub-order-soap-service.png?raw=true)

But you still want a HTML spec right away. In this case you can still use __wsdl2html__ to __generate a readable HTML page from jax-ws stubs__

# How to run

## Run as a command line tool

```shell

mvn package 
cd target 
unzip target/wsdl2html-some-version-jarset.zip -d /path/to/your/dir

# Go to the direction of extraction and you will see an executable file. Run it like, 

./wsdl2html.sh http://.../some?wsdl /path/to/your/html/directory  # or wsdl2html.bat for windows

```


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
			<version>2.0</version>
		</dependency>
		...
	</dependencies>	

```



```java

//if you call this method in your code, make sure the jdk version you used to run your code is 
// no lower than that of the jdk used  by your "wsimport" to run in shell

String html = org.jaxws.wsdl2html.service.Wsdl2Html.generateHtml(wsdlUrl); 
```  


To generate html from stub classes, check [Wsdl2HtmlITCase](src/test/java/org/jaxws/integrationtest/Wsdl2HtmlITCase.java)

```java

		WebServiceStubSet serviceStubSet = WebServiceStubSetFactory
				.createWebServiceStubSet(webServiceClass);

		File outputDir = new File("output/" + System.currentTimeMillis());
		outputDir.mkdirs();

		WebServiceDisplayEngine displayEngine = createEngine(
				new SimpleJavaNameDisplayStrategy(),

				/* you can use your own template here. this is a classpath */
				"/service.ftl");

		String html = displayEngine.displayWebSerivce(serviceStubSet);
		File outputFile = new File(outputDir, "report.html");
		FileUtils.writeStringToFile(outputFile, html);

		System.out.println("Please find the HTML files at "
				+ outputFile.getAbsolutePath());

```
