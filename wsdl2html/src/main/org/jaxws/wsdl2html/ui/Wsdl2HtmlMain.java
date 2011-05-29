package org.jaxws.wsdl2html.ui;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jaxws.stub2html.view.freemarker.ClasspathFreemarkerWebServiceDisplayEngine;
import org.jaxws.stub2html.view.freemarker.FilePathFreemarkerWebServiceDisplayEngine;
import org.jaxws.stub2html.view.freemarker.FreemarkerWebServiceDisplayEngine;
import org.jaxws.stub2html.view.simple.SimpleJavaNameDisplayStrategy;
import org.jaxws.util.os.SystemProcessException;
import org.jaxws.wsdl2bytecodes.service.WsdlImportException;
import org.jaxws.wsdl2html.service.Wsdl2Html;

/**
 * 
 * @author chenjianjx
 * 
 */
public class Wsdl2HtmlMain {

    public static void main(String[] args) throws  IOException, WsdlImportException {

        if (args == null || args.length < 1) {
            System.out.println("please input the wsdl");
            return;
        }
        String wsdlUrl = args[0];

        File outputRootDir = new File("output/" + System.currentTimeMillis());

        File byteCodeDir = getByteCodeDir(outputRootDir);
        File htmlDir = getHtmlDir(outputRootDir);

        System.out.println("Generating from " + wsdlUrl);

        FreemarkerWebServiceDisplayEngine displayEngine = createDisplayEngine(args);

        String html = Wsdl2Html.generateHtml(byteCodeDir.getAbsolutePath(), wsdlUrl, displayEngine);
        FileUtils.writeStringToFile(new File(htmlDir, "report.html"), html);

        outputRootDir.mkdirs();
        System.out.println("Please find the HTML files at " + htmlDir.getAbsolutePath());
    }

    private static FreemarkerWebServiceDisplayEngine createDisplayEngine(String[] args) {
        FreemarkerWebServiceDisplayEngine displayEngine = null;
        if (args.length > 1) {
            File customTemplate = new File(args[1]);
            displayEngine = FilePathFreemarkerWebServiceDisplayEngine.createEngine(new SimpleJavaNameDisplayStrategy(), customTemplate);
        } else {
            displayEngine = ClasspathFreemarkerWebServiceDisplayEngine.createEngine(new SimpleJavaNameDisplayStrategy());
        }
        return displayEngine;
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
