package org.jaxws.stub2html.view.freemarker;

import java.io.IOException;

import org.jaxws.stub2html.model.WebServiceStubSet;
import org.jaxws.stub2html.view.JavaNameDisplayStrategy;
import org.jaxws.stub2html.view.WebServiceDisplayEngine;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 
 * @author chenjianjx
 * 
 */
public class FreemarkerWebServiceDisplayEngine extends WebServiceDisplayEngine {

    private String absoluteFtlClassPath;
    private Configuration configuration;

    private FreemarkerWebServiceDisplayEngine(JavaNameDisplayStrategy nameDisplayingStrategy, String absoluteFtlClassPath) {
        super(nameDisplayingStrategy);

        if (!absoluteFtlClassPath.startsWith("/")) {
            throw new IllegalArgumentException("Template's class-path has to start with '/'");
        }

        configuration = new Configuration();
        configuration.setLocalizedLookup(false);
        configuration.setClassForTemplateLoading(FreemarkerWebServiceDisplayEngine.class, "/");
        configuration.setObjectWrapper(new DefaultObjectWrapper());
        this.absoluteFtlClassPath = absoluteFtlClassPath;
    }

    public static FreemarkerWebServiceDisplayEngine createEngine(JavaNameDisplayStrategy nameDisplayingStrategy, String absoluteFtlClassPath) {
        return new FreemarkerWebServiceDisplayEngine(nameDisplayingStrategy, absoluteFtlClassPath);
    }

    @Override
    public String displayWebSerivce(WebServiceStubSet serviceStubSet) {
        Template template = getTemplate();
        FreemarkerWebServiceDisplayer displayer = new FreemarkerWebServiceDisplayer(template, nameDisplayingStrategy, serviceStubSet);
        return displayer.displayWebSerivce();

    }

    private Template getTemplate() {
        try {
            return configuration.getTemplate(absoluteFtlClassPath);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
