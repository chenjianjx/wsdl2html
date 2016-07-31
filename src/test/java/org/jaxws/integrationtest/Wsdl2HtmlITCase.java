package org.jaxws.integrationtest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import org.apache.commons.io.FileUtils;
import org.jaxws.integrationtest.exampleWebService.BbsWebService;
import org.jaxws.integrationtest.exampleWebService.EmptyWebService;
import org.jaxws.integrationtest.exampleWebService.NewPostRequest;
import org.jaxws.integrationtest.exampleWebService.Post;
import org.jaxws.integrationtest.exampleWebService.PostListResponse;
import org.jaxws.integrationtest.exampleWebService.SinglePostResponse;
import org.jaxws.integrationtest.exampleWebService.UpdatePostRequest;
import org.jaxws.integrationtest.exampleWebService.VoidResponse;
import org.jaxws.wsdl2bytecodes.service.WsdlImportException;
import org.jaxws.wsdl2html.service.Wsdl2Html;
import org.junit.Test;

/**
 * 
 * @author chenjianjx@gmail.com
 *
 */
public class Wsdl2HtmlITCase {

	@Test
	public void formWsdl() throws Exception {
		Endpoint.publish("http://localhost:9999/ws/bbs", new BbsSoapServiceImpl());
		String wsdlUrl = "http://localhost:9999/ws/bbs?wsdl";
		System.out.println("Generating from " + wsdlUrl);
		String html = Wsdl2Html.generateHtml(wsdlUrl);
		File htmlDir = new File("output");
		FileUtils.writeStringToFile(new File(htmlDir, "bbs.html"), html);
		System.out.println("Please find the HTML files at " + htmlDir.getAbsolutePath());
	}

	private void doGenerate(String wsdlUrl, String reportFileName) throws WsdlImportException, IOException {
		System.out.println("Generating from " + wsdlUrl);
		String html = Wsdl2Html.generateHtml(wsdlUrl);
		File htmlDir = new File("output");
		FileUtils.writeStringToFile(new File(htmlDir, reportFileName), html);
		System.out.println("Please find the HTML files at " + htmlDir.getAbsolutePath());
	}

	/**
	 * http://www.service-repository.com/
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPublicWsdls() throws Exception {
		doGenerate("https://webservices.amazon.com/AWSECommerceService/AWSECommerceService.wsdl", "AWSECommerceService.html");
		doGenerate("http://www.webservicex.com/globalweather.asmx?wsdl", "globalweather.html");
		doGenerate("http://wsf.cdyne.com/WeatherWS/Weather.asmx?WSDL", "Weather.html");
		doGenerate("http://www.thomas-bayer.com/axis2/services/BLZService?wsdl", "BLZService.html");
		doGenerate("http://soaptest.parasoft.com/calculator.wsdl", "calculator.html");
		doGenerate("http://www.dneonline.com/calculator.asmx?WSDL", "calculator.html");
		doGenerate("https://community.workday.com/custom/developer/API/Absence_Management/v26.2/Absence_Management.wsdl", "Absence_Management.html");
		doGenerate("http://www.webservicex.com/periodictable.asmx?wsdl", "periodictable.html");
		doGenerate("http://ws.cdyne.com/ip2geo/ip2geo.asmx?wsdl", "ip2geo.html");
	}

	@Test(expected = WsdlImportException.class)
	public void testIllWsdl() throws Exception {
		doGenerate("http://graphical.weather.gov/xml/DWMLgen/wsdl/ndfdXML.wsdl", "ndfdXML.html");
	}

	@Test(expected = Exception.class)
	public void formWsdl_EmptyService() throws Exception {
		Endpoint.publish("http://localhost:9999/ws/empty", new EmptySoapServiceImpl());
	}

	@WebService(endpointInterface = "org.jaxws.integrationtest.exampleWebService.EmptyWebService")
	private static final class EmptySoapServiceImpl implements EmptyWebService {

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

		@Override
		public PostListResponse getPostsByIds(Long operatorUserId, List<Long> postIds) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Post> getAllPostsWithoutOverload(Long operatorUserId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Long> getAllPostIdsWithoutOverload(Long operatorUserId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void empty() {
			// TODO Auto-generated method stub

		}

		@Override
		public void refreshToken(String oldToken) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean oldOrEven(int number) {
			// TODO Auto-generated method stub
			return false;
		}
	}

	public static void main(String[] args) throws WsdlImportException, IOException {
		Endpoint.publish("http://localhost:9999/ws/bbs", new BbsSoapServiceImpl());
	}

}
