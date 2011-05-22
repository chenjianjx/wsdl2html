package org.jaxws.stub2html.ui;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.jaxws.stub2html.model.WebMethodStubSet;
import org.jaxws.stub2html.model.WebServiceStubSet;
import org.jaxws.stub2html.service.WebServiceStubSetFactory;
import org.jaxws.stub2html.view.WebMethodDisplayEngine;
import org.jaxws.stub2html.view.simple.SimpleJavaNameDisplayStrategy;
import org.jaxws.stub2html.view.simple.SimpleWebMethodDisplayEngine;

/**
 * 
 * @author chenjianjx
 * 
 */
public class Stub2HtmlSimpleMain {

    public static void main(String[] args) throws Exception {
        Class<?> webServiceClass = Class.forName(args[0]);
        WebServiceStubSet serviceStubSet = WebServiceStubSetFactory.createWebServiceStubSet(webServiceClass);

        File outputDir = new File("output/" + System.currentTimeMillis());
        outputDir.mkdirs();

        for (WebMethodStubSet methodStubSet : serviceStubSet.getMethodStubs()) {
            System.out.println("Converting method " + methodStubSet.getMethodName());
            WebMethodDisplayEngine display = new SimpleWebMethodDisplayEngine(new SimpleJavaNameDisplayStrategy());
            String html = display.displayWebMethod(methodStubSet);
            File outputFile = new File(outputDir, methodStubSet.getMethodName() + ".html");
            FileUtils.writeStringToFile(outputFile, html);
        }

        System.out.println("Please find the HTML files at " + outputDir.getAbsolutePath());

    }
}
