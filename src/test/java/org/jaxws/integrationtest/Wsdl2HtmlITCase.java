package org.jaxws.integrationtest;

import static org.jaxws.stub2html.view.freemarker.ClasspathFreemarkerWebServiceDisplayEngine.createEngine;

import java.io.File;
import java.io.IOException;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import org.apache.commons.io.FileUtils;
import org.jaxws.integrationtest.exampleWebService.BbsWebService;
import org.jaxws.integrationtest.exampleWebService.NewPostRequest;
import org.jaxws.integrationtest.exampleWebService.PostListResponse;
import org.jaxws.integrationtest.exampleWebService.SinglePostResponse;
import org.jaxws.integrationtest.exampleWebService.UpdatePostRequest;
import org.jaxws.integrationtest.exampleWebService.VoidResponse;
import org.jaxws.integrationtest.exampleWebService.deprecated.OrderPort;
import org.jaxws.stub2html.model.WebServiceStubSet;
import org.jaxws.stub2html.service.WebServiceStubSetFactory;
import org.jaxws.stub2html.view.WebServiceDisplayEngine;
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
		Class<?> webServiceClass = OrderPort.class;
		WebServiceStubSet serviceStubSet = WebServiceStubSetFactory.createWebServiceStubSet(webServiceClass);

		File outputDir = new File("output/" + System.currentTimeMillis());
		outputDir.mkdirs();

		WebServiceDisplayEngine displayEngine = createEngine(
		/* you can use your own template here. this is a classpath */
		"/service.ftl");

		String html = displayEngine.displayWebSerivce(serviceStubSet);
		File outputFile = new File(outputDir, "report.html");
		FileUtils.writeStringToFile(outputFile, html);

		System.out.println("Please find the HTML files at " + outputFile.getAbsolutePath());

	}

	@Test
	public void formWsdl() throws Exception {
		Endpoint.publish("http://localhost:9999/ws/bbs", new BbsSoapServiceImpl());
		String wsdlUrl = "http://localhost:9999/ws/bbs?wsdl";
		System.out.println("Generating from " + wsdlUrl);
		String html = Wsdl2Html.generateHtml(wsdlUrl);
		File htmlDir = new File("output");
		FileUtils.writeStringToFile(new File(htmlDir, "report.html"), html);
		System.out.println("Please find the HTML files at " + htmlDir.getAbsolutePath());
	}

	@WebService(endpointInterface = "org.jaxws.integrationtest.exampleWebService.BbsWebService")
	private static final class BbsSoapServiceImpl implements BbsWebService {

		@Override
		public SinglePostResponse newPost(Long currentUserId, NewPostRequest request) {
			return null;
		}

		@Override
		public SinglePostResponse updatePost(Long currentUserId, UpdatePostRequest request) {
			return null;
		}

		@Override
		public PostListResponse getAllPosts(Long currentUserId) {
			return null;
		}

		@Override
		public VoidResponse deletePostById(Long currentUserId, Long postId) {
			return null;
		}
	}

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9999/ws/bbs", new BbsSoapServiceImpl());
	}

}
