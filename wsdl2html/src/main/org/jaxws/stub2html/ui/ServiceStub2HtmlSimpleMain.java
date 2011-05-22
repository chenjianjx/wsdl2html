package org.jaxws.stub2html.ui;

import static org.jaxws.stub2html.view.freemarker.FreemarkerWebServiceDisplayEngine.createEngine;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.jaxws.stub2html.model.WebServiceStubSet;
import org.jaxws.stub2html.service.WebServiceStubSetFactory;
import org.jaxws.stub2html.view.WebServiceDisplayEngine;
import org.jaxws.stub2html.view.simple.SimpleJavaNameDisplayStrategy;

/**
 * 
 * @author chenjianjx
 * 
 */
public class ServiceStub2HtmlSimpleMain {

    public static void main(String[] args) throws Exception {
        Class<?> webServiceClass = Class.forName(args[0]);
        WebServiceStubSet serviceStubSet = WebServiceStubSetFactory.createWebServiceStubSet(webServiceClass);

        File outputDir = new File("output/" + System.currentTimeMillis());
        outputDir.mkdirs();

        WebServiceDisplayEngine displayEngine = createEngine(new SimpleJavaNameDisplayStrategy(), "/org/jaxws/stub2html/view/freemarker/service.ftl");

        String html = displayEngine.displayWebSerivce(serviceStubSet);
        File outputFile = new File(outputDir, "report.html");
        FileUtils.writeStringToFile(outputFile, html);

        System.out.println("Please find the HTML files at " + outputFile.getAbsolutePath());

    }
}
