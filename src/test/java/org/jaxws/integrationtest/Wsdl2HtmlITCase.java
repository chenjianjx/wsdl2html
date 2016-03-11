package org.jaxws.integrationtest;

import static org.jaxws.stub2html.view.freemarker.ClasspathFreemarkerWebServiceDisplayEngine.createEngine;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import org.apache.commons.io.FileUtils;
import org.jaxws.integrationtest.exampleWebService.Order;
import org.jaxws.integrationtest.exampleWebService.OrderSOAPService;
import org.jaxws.stub2html.model.WebMethodStubSet;
import org.jaxws.stub2html.model.WebServiceStubSet;
import org.jaxws.stub2html.service.WebServiceStubSetFactory;
import org.jaxws.stub2html.view.WebMethodDisplayEngine;
import org.jaxws.stub2html.view.WebServiceDisplayEngine;
import org.jaxws.stub2html.view.freemarker.FreemarkerWebServiceDisplayEngine;
import org.jaxws.stub2html.view.simple.SimpleJavaNameDisplayStrategy;
import org.jaxws.stub2html.view.simple.SimpleWebMethodDisplayEngine;
import org.jaxws.wsdl2bytecodes.service.Wsdl2ByteCodes.DeclarationCollisionException;
import org.jaxws.wsdl2html.service.Wsdl2Html;
import org.junit.Test;

/**
 * 
 * @author chenjianjx@gmail.com
 *
 */
public class Wsdl2HtmlITCase {

	@Test
	public void fromStubTest_SingleReport() throws IOException {
		Class<?> webServiceClass = OrderSOAPService.class;
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

	}

	@Test
	public void fromStubTest_OneMethodOneFile() throws IOException {
		Class<?> webServiceClass = OrderSOAPService.class;
		WebServiceStubSet serviceStubSet = WebServiceStubSetFactory
				.createWebServiceStubSet(webServiceClass);

		File outputDir = new File("output/" + System.currentTimeMillis());
		outputDir.mkdirs();

		for (WebMethodStubSet methodStubSet : serviceStubSet.getMethodStubs()) {
			System.out.println("Converting method "
					+ methodStubSet.getMethodName());
			WebMethodDisplayEngine display = new SimpleWebMethodDisplayEngine(
					new SimpleJavaNameDisplayStrategy());
			String html = display.displayWebMethod(methodStubSet);
			File outputFile = new File(outputDir, methodStubSet.getMethodName()
					+ ".html");
			FileUtils.writeStringToFile(outputFile, html);
		}

		System.out.println("Please find the HTML files at "
				+ outputDir.getAbsolutePath());
	}

	@Test
	public void formWsdl() throws Exception {
		Endpoint.publish("http://localhost:9999/ws/order",
				new OrderSOAPServiceTestImpl());
		String wsdlUrl = "http://localhost:9999/ws/order?wsdl";
		System.out.println("Generating from " + wsdlUrl);
		String html = Wsdl2Html.generateHtml(wsdlUrl);
		File htmlDir = new File("output" + System.currentTimeMillis());
		FileUtils.writeStringToFile(new File(htmlDir, "report.html"), html);
		System.out.println("Please find the HTML files at "
				+ htmlDir.getAbsolutePath());
	}

	@WebService(endpointInterface = "org.jaxws.integrationtest.exampleWebService.OrderSOAPService")
	private static final class OrderSOAPServiceTestImpl implements
			OrderSOAPService {

		@Override
		public void placeOrder(String userName, String password, Order order) {

		}

		@Override
		public List<Order> findOrders(Integer userId) {

			return null;
		}

		@Override
		public void emptyMethod() {

		}

	}

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9999/ws/order",
				new OrderSOAPServiceTestImpl());
	}

}
