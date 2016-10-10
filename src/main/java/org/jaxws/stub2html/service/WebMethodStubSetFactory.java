package org.jaxws.stub2html.service;

import static org.jaxws.stub2html.service.JavaLanguageVariableFactory.createVariableFromMethodReturn;
import static org.jaxws.stub2html.service.JavaLanguageVariableFactory.createVariablesFromMethodParamaters;

import java.lang.reflect.Method;
import java.util.List;

import org.jaxws.stub2html.model.JavaLanguageVariable;
import org.jaxws.stub2html.model.Stub;
import org.jaxws.stub2html.model.WebMethodStubSet;

/**
 * 
 * @author chenjianjx
 * 
 */
public class WebMethodStubSetFactory {

	public static WebMethodStubSet createWebMethodStubSet(Method method) {
		WebMethodStubSet stubSet = new WebMethodStubSet();
		stubSet.setMethodName(method.getName());
		stubSet.setMethodDescription(DescriptionLocatorRepository.getInstance().findDescriptionByMethod(method));

		addRequestStubs(method, stubSet);
		if (!Void.TYPE.equals(method.getReturnType())) {
			addResponseStub(method, stubSet);
		}
		return stubSet;
	}

	private static void addResponseStub(Method method, WebMethodStubSet stubSet) {
		JavaLanguageVariable variable = createVariableFromMethodReturn(method);
		Stub stub = Variable2Stub.convertToStub(variable, null, stubSet.getStubTypeTreeRepository());
		stubSet.setResponseStub(stub);
	}

	private static void addRequestStubs(Method method, WebMethodStubSet stubSet) {
		List<JavaLanguageVariable> requestVariables = createVariablesFromMethodParamaters(method);
		for (JavaLanguageVariable variable : requestVariables) {
			Stub stub = Variable2Stub.convertToStub(variable, null, stubSet.getStubTypeTreeRepository());
			stubSet.addRequetStub(stub);
		}
	}

}
