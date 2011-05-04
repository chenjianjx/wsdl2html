package org.jaxws.stub2html.model;

import java.util.ArrayList;
import java.util.List;


/**
 * it correspond to a web service class 
 * @author chenjianjx
 *
 */
public class WebServiceStubSet {

	private List<WebMethodStubSet> methodStubs  = new ArrayList<WebMethodStubSet>();

	public List<WebMethodStubSet> getMethodStubs() {
		return methodStubs;
	}

	public void addMethodStubs( WebMethodStubSet  methodStub) {
		methodStubs.add(methodStub);
	}
	
	
	
	
	
}
