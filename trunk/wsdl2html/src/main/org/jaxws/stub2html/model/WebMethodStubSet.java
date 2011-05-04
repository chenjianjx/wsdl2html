package org.jaxws.stub2html.model;

import java.util.ArrayList;
import java.util.List;

import org.jaxws.stub2html.service.StubTypeTreeRepository;

/**
 * it correspond to a method in a web service 
 * @author chenjianjx
 * 
 */
public class WebMethodStubSet {

	private String methodName;
	private List<Stub> requestStubs = new ArrayList<Stub>();
	private Stub responseStub;
	private StubTypeTreeRepository stubTypeTreeRepository = new StubTypeTreeRepository();

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Stub getResponseStub() {
		return responseStub;
	}

	public void setResponseStub(Stub responseStub) {
		this.responseStub = responseStub;
	}

	public List<Stub> getRequestStubs() {
		return requestStubs;
	}

	public void addRequetStub(Stub stub){
		requestStubs.add(stub);
	}

	public StubTypeTreeRepository getStubTypeTreeRepository() {
		return stubTypeTreeRepository;
	}
	
	
	
	
}
