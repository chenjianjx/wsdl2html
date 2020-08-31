## wsdl/xml documentation

* wsdl documentation = documentation about classes and methods
  * wsdl->java: https://javaee.github.io/metro/doc/user-guide/ch03.html#generating-javadocs-from-wsdl-documentation
  * java->wsdl:  Seems CXF can do it: http://cxf.apache.org/javadoc/latest/org/apache/cxf/annotations/WSDLDocumentation.html
  
* data type schema documentation = documentation about fields in a class
  * wsdl->java: ?
  * java->wsdl:  JDK cannot do it. CXF can't help either: https://issues.apache.org/jira/browse/CXF-6291?jql=text%20~%20%22%40WSDLDocumentation%22

* webservice param documentation = documentation about parameters in a class