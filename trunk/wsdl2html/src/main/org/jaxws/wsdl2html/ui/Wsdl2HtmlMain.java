package org.jaxws.wsdl2html.ui;

import static org.jaxws.stub2html.view.freemarker.FreemarkerWebServiceDisplayEngine.createEngine;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jaxws.stub2html.view.freemarker.FreemarkerWebServiceDisplayEngine;
import org.jaxws.stub2html.view.simple.SimpleJavaNameDisplayStrategy;
import org.jaxws.util.os.SystemProcessException;
import org.jaxws.wsdl2html.service.Wsdl2Html;

/**
 * 
 * @author chenjianjx
 * 
 */
public class Wsdl2HtmlMain {

    public static void main(String[] args) throws SystemProcessException, IOException {

        if (args == null || args.length < 1) {
            System.out.println("please input the wsdl");
            return;
        }
        String wsdlUrl = args[0];

        File outputRootDir = new File("output/" + System.currentTimeMillis());

        File byteCodeDir = getByteCodeDir(outputRootDir);
        File htmlDir = getHtmlDir(outputRootDir);

        System.out.println("Generating from " + wsdlUrl);

        String html = Wsdl2Html.generateHtml(byteCodeDir.getAbsolutePath(), wsdlUrl, getDefaultDisplayEngine());
        FileUtils.writeStringToFile(new File(htmlDir, "report.html"), html);

        outputRootDir.mkdirs();
        System.out.println("Please find the HTML files at " + htmlDir.getAbsolutePath());
    }

    private static FreemarkerWebServiceDisplayEngine getDefaultDisplayEngine() {
        return createEngine(new SimpleJavaNameDisplayStrategy(), "/org/jaxws/stub2html/view/freemarker/service.ftl");
    }

    private static File getHtmlDir(File outputRootDir) {
        File htmlDir = new File(outputRootDir, "html");
        htmlDir.mkdirs();
        return htmlDir;
    }

    private static File getByteCodeDir(File outputRootDir) {
        File byteCodeDir = new File(outputRootDir, "stubs");
        byteCodeDir.mkdirs();
        return byteCodeDir;
    }

}
