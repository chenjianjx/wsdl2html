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
import org.jaxws.stub2html.view.simple.SimpleJavaNameDisplayStrategy;
import org.jaxws.wsdl2bytecodes.service.Wsdl2ByteCodes.DeclarationCollisionException;
import org.jaxws.wsdl2bytecodes.service.WsdlImportException;
import org.jaxws.wsdl2html.service.Wsdl2Html;

/**
 * 
 * @author chenjianjx
 * 
 */
public class Wsdl2HtmlMain {

	private static final String ORIG_PKG_PARAM = "-origPkg";

	public static void main(String[] args) throws IOException, WsdlImportException {

		if (args == null || args.length == 0) {
			System.out.println("Params: [" + ORIG_PKG_PARAM + "]  wsdlUrl [targetDir]");
			return;
		}

		List<String> argList = new ArrayList<String>(Arrays.asList(args));

		boolean useOrigPkg = false;
		if (argList.contains(ORIG_PKG_PARAM)) {
			useOrigPkg = true;
			argList.remove(ORIG_PKG_PARAM);
		}

		String wsdlUrl = argList.get(0);

		File outputRootDir = new File("output/" + getUniqueNumber());
		if (argList.size() > 1) {
			outputRootDir = new File(argList.get(1));
			outputRootDir.mkdirs();
		}

		File byteCodeDir = getByteCodeDir(outputRootDir);
		File htmlDir = getHtmlDir(outputRootDir);

		System.out.println("Generating from " + wsdlUrl);

		FreemarkerWebServiceDisplayEngine displayEngine = createDisplayEngine(argList);
		String html = null;
		try {
			html = Wsdl2Html.generateHtml(byteCodeDir.getAbsolutePath(), wsdlUrl, useOrigPkg, displayEngine);
		} catch (DeclarationCollisionException e) {
			System.out.println("Failed to due to declaration exception. Please try again with flag: " + ORIG_PKG_PARAM);
			return;
		}
		FileUtils.writeStringToFile(new File(htmlDir, "report-" + getUniqueNumber() + ".html"), html);
		System.out.println("Please find the HTML files at " + htmlDir.getAbsolutePath());
	}

	private static String getUniqueNumber() {
		return System.currentTimeMillis() + "" + RandomUtils.nextLong();
	}

	private static FreemarkerWebServiceDisplayEngine createDisplayEngine(List<String> argList) {
		FreemarkerWebServiceDisplayEngine displayEngine = null;
		// if (argList.size() > 1) {
		// File customTemplate = new File(argList.get(1));
		// displayEngine =
		// FilePathFreemarkerWebServiceDisplayEngine.createEngine(new
		// SimpleJavaNameDisplayStrategy(), customTemplate);
		// } else {
		displayEngine = ClasspathFreemarkerWebServiceDisplayEngine.createEngine(new SimpleJavaNameDisplayStrategy());
		// }
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

}
