package org.jaxws.wsdl2html.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.jaxws.bytecodes2stub.service.ByteCodePackageLoadingService;
import org.jaxws.stub2html.model.WebServiceStubSet;
import org.jaxws.stub2html.service.WebServiceStubSetFactory;
import org.jaxws.stub2html.view.WebServiceDisplayEngine;
import org.jaxws.stub2html.view.freemarker.ClasspathFreemarkerWebServiceDisplayEngine;
import org.jaxws.stub2html.view.freemarker.FreemarkerWebServiceDisplayEngine;
import org.jaxws.wsdl2bytecodes.model.ByteCodePackage;
import org.jaxws.wsdl2bytecodes.service.Wsdl2ByteCodes;
import org.jaxws.wsdl2bytecodes.service.WsdlImportException;

/**
 * 
 * @author chenjianjx
 * 
 */
public class Wsdl2Html {

	public static String generateHtml(final String byteCodesDirParent, final String wsdlUrl,
			final WebServiceDisplayEngine displayEngine, final boolean isDebug) throws WsdlImportException {
		final ByteCodePackage byteCodePackage = Wsdl2ByteCodes.generate(byteCodesDirParent, wsdlUrl, isDebug);
		final List<Class<?>> webServiceClass = getWebServiceClass(byteCodePackage);
		final WebServiceStubSet serviceStubSet = WebServiceStubSetFactory.createWebServiceStubSet(webServiceClass);
		return displayEngine.displayWebSerivce(serviceStubSet);
	}

	/**
	 * 
	 * 
	 * 
	 * @param wsdlUrl
	 * @param isDebug
	 * @return
	 * @throws WsdlImportException
	 */
	public static String generateHtml(final String wsdlUrl, final boolean isDebug) throws WsdlImportException {
		final FreemarkerWebServiceDisplayEngine displayEngine = ClasspathFreemarkerWebServiceDisplayEngine
				.createEngine();
		final String byteCodesDirParent = System.getProperty("java.io.tmpdir") + "/wsdl2html";
		return generateHtml(byteCodesDirParent, wsdlUrl, displayEngine, isDebug);
	}

	public static String generateHtml(final String wsdlUrl) throws WsdlImportException {
		return generateHtml(wsdlUrl, false);
	}

	private static List<Class<?>> getWebServiceClass(final ByteCodePackage byteCodePackage) {
		final List<Class<?>> allClasses = ByteCodePackageLoadingService.loadAll(byteCodePackage);
		final List<Class<?>> annotatedClasses = new ArrayList<Class<?>>();
		for (final Class<?> clazz : allClasses) {
			if (clazz.isInterface() && clazz.isAnnotationPresent(WebService.class)) {
				annotatedClasses.add(clazz);
			}
		}
		if (annotatedClasses.size()==0)
			throw new IllegalStateException("No WebService Class found ! ");
		return annotatedClasses;
	}

}
