package org.jaxws.stub2html.service;

import static org.jaxws.stub2html.service.WebMethodStubSetFactory.createWebMethodStubSet;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.jws.WebMethod;

import org.jaxws.stub2html.model.WebServiceStubSet;

/**
 * 
 * @author chenjianjx
 * 
 */
public class WebServiceStubSetFactory {

	public static WebServiceStubSet createWebServiceStubSet(Class<?> webServiceClass) {
		List<Method> methods = getWebMethods(webServiceClass);
		WebServiceStubSet serviceStubs = new WebServiceStubSet();
		serviceStubs.setWebServiceClass(webServiceClass);
		methods.sort(new Comparator<Method>() {
			@Override
			public int compare(Method o1, Method o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (Method method : methods) {
			serviceStubs.addMethodStub(createWebMethodStubSet(method));
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
