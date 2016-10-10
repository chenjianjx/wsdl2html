package org.jaxws.wsdl2html.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.jaxws.stub2html.view.freemarker.ClasspathFreemarkerWebServiceDisplayEngine;
import org.jaxws.stub2html.view.freemarker.FreemarkerWebServiceDisplayEngine;
import org.jaxws.wsdl2bytecodes.service.WsdlImportException;
import org.jaxws.wsdl2html.service.Wsdl2Html;

/**
 * 
 * @author chenjianjx
 * 
 */
public class Wsdl2HtmlMain {
	private static final String TARGET_APIDOCS_LOCATION = "/target/classes/META-INF/apidocs/";
	private static String descriptionsPath = "";

	public static void main(String[] args) throws IOException, WsdlImportException {

		if (args == null || args.length == 0) {
			System.out.println("Params:  wsdlUrl [targetDir]");
			return;
		}

		List<String> argList = new ArrayList<String>(Arrays.asList(args));

		String wsdlUrl = argList.get(0);

		File outputRootDir = new File("output/" + getUniqueNumber());
		if (argList.size() > 1) {
			outputRootDir = new File(argList.get(1));
			outputRootDir.mkdirs();
		}

		File byteCodeDir = getByteCodeDir(outputRootDir);
		File htmlDir = getHtmlDir(outputRootDir);

		System.out.println("Generating from " + wsdlUrl);
		Wsdl2HtmlMain.descriptionsPath = wsdlUrl;

		FreemarkerWebServiceDisplayEngine displayEngine = createDisplayEngine(argList);
		String html = Wsdl2Html.generateHtml(byteCodeDir.getAbsolutePath(), wsdlUrl, displayEngine);
		FileUtils.writeStringToFile(new File(htmlDir, "report-" + getUniqueNumber() + ".html"), html);
		System.out.println("Please find the HTML files at " + htmlDir.getAbsolutePath());
	}

	private static String getUniqueNumber() {
		return System.currentTimeMillis() + "" + RandomUtils.nextLong();
	}

	private static FreemarkerWebServiceDisplayEngine createDisplayEngine(List<String> argList) {
		FreemarkerWebServiceDisplayEngine displayEngine = ClasspathFreemarkerWebServiceDisplayEngine.createEngine();
		return displayEngine;
	}

	private static File getHtmlDir(File outputRootDir) {
		File htmlDir = new File(outputRootDir, "html");
		htmlDir.mkdirs();
		return htmlDir;
	}

	private static File getByteCodeDir(File outputRootDir) {
		File byteCodeDir = new File(outputRootDir, "stubs-" + getUniqueNumber());
		byteCodeDir.mkdirs();
		return byteCodeDir;
	}

	public static String getDescriptionsPath() {
		return (new File(descriptionsPath)).getParent() + TARGET_APIDOCS_LOCATION;
	}
}
