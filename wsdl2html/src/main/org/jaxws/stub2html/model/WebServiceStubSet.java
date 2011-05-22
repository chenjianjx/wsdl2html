package org.jaxws.stub2html.model;

import java.util.ArrayList;
import java.util.List;

/**
 * it correspond to a web service class
 * 
 * @author chenjianjx
 * 
 */
public class WebServiceStubSet {

    private Class<?> webServiceClass;
    private List<WebMethodStubSet> methodStubs = new ArrayList<WebMethodStubSet>();

    public List<WebMethodStubSet> getMethodStubs() {
        return methodStubs;
    }

    public void addMethodStub(WebMethodStubSet methodStub) {
        methodStubs.add(methodStub);
    }

    public Class<?> getWebServiceClass() {
        return webServiceClass;
    }

    public void setWebServiceClass(Class<?> webServiceClass) {
        this.webServiceClass = webServiceClass;
    }

}
