package org.jaxws.wsdl2html.service;

import java.util.List;

import javax.jws.WebService;

import org.jaxws.bytecodes2stub.service.ByteCodePackageLoadingService;
import org.jaxws.stub2html.model.WebMethodStubSet;
import org.jaxws.stub2html.model.WebServiceStubSet;
import org.jaxws.stub2html.service.WebServiceStubSetFactory;
import org.jaxws.stub2html.view.DisplayEngine;
import org.jaxws.stub2html.view.simple.SimpleDisplayEngine;
import org.jaxws.stub2html.view.simple.SimpleJavaNameDisplayStrategy;
import org.jaxws.util.os.SystemProcessException;
import org.jaxws.wsdl2bytecodes.model.ByteCodePackage;
import org.jaxws.wsdl2bytecodes.service.Wsdl2ByteCodes;

/**
 * 
 * @author chenjianjx
 * 
 */
public class Wsdl2Html {
 
	public static String generateHtml(String byteCodesDirParent, String wsdlUrl) throws SystemProcessException {
		ByteCodePackage byteCodePackage = Wsdl2ByteCodes.generate(byteCodesDirParent, wsdlUrl);
		Class<?> webServiceClass = getWebServiceClass(byteCodePackage);
		WebServiceStubSet serviceStubSet = WebServiceStubSetFactory.createWebServiceStubSet(webServiceClass);
		return printAllMethods(serviceStubSet);
	}

	private static String printAllMethods(WebServiceStubSet serviceStubSet)   {
		StringBuffer html = new StringBuffer();
		List<WebMethodStubSet> methodStubs = serviceStubSet.getMethodStubs();
		html.append("<h1> " + methodStubs.size() + " Methods in total </h1>");
		html.append(newLines());
		for (WebMethodStubSet methodStubSet : methodStubs) {
			System.out.println("Converting method " + methodStubSet.getMethodName());
			DisplayEngine display = new SimpleDisplayEngine(methodStubSet, new SimpleJavaNameDisplayStrategy());
			String htmlForMethod = display.displayWebMethod();
			html.append("==========================Web Method==========================================");
			html.append(htmlForMethod);
			html.append(newLines());
		}
		return html.toString();
	}

	private static Class<?> getWebServiceClass(ByteCodePackage byteCodePackage)  {
		List<Class<?>> allClasses = ByteCodePackageLoadingService.loadAll(byteCodePackage);

		for (Class<?> clazz : allClasses) {
			if (clazz.isInterface() && clazz.isAnnotationPresent(WebService.class)) {
				return clazz;
			}
		}
		throw new IllegalStateException("No WebService Class found ! ");
	}

	private static String newLines() {
		return "<p/><p/><p/><p/>";
	}

}
