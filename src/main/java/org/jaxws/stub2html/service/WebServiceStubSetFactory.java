package org.jaxws.stub2html.service;

import static org.jaxws.stub2html.service.WebMethodStubSetFactory.createWebMethodStubSet;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;

import org.jaxws.stub2html.model.WebServiceStubSet;

/**
 * 
 * @author chenjianjx
 * 
 */
public class WebServiceStubSetFactory {

	public static WebServiceStubSet createWebServiceStubSet(List<Class<?>> webServiceClasses) {
		WebServiceStubSet serviceStubs = new WebServiceStubSet();
		for (Class<?> webServiceClass : webServiceClasses) {
			List<Method> methods = getWebMethods(webServiceClass);
			serviceStubs.setWebServiceClass(webServiceClass);
			for (Method method : methods) {
				serviceStubs.addMethodStub(createWebMethodStubSet(method));
			}				
		}
		return serviceStubs;
	}

	private static List<Method> getWebMethods(Class<?> webServiceClass) {
		List<Method> webMethods = new ArrayList<Method>();
		for (Method method : webServiceClass.getMethods()) {
			if (method.isAnnotationPresent(WebMethod.class)) {
				webMethods.add(method);
			}
		}
		return webMethods;
	}
}
