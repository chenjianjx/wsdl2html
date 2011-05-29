package org.jaxws.stub2html.view.freemarker;

import java.io.IOException;

import org.jaxws.stub2html.view.JavaNameDisplayStrategy;
import org.jaxws.stub2html.view.simple.SimpleJavaNameDisplayStrategy;
import org.jaxws.util.lang.ClassNameUtils;

import freemarker.template.Template;

/**
 * 
 * @author chenjianjx
 * 
 */
public class ClasspathFreemarkerWebServiceDisplayEngine extends FreemarkerWebServiceDisplayEngine {

    private String absoluteFtlClassPath;

    private ClasspathFreemarkerWebServiceDisplayEngine(JavaNameDisplayStrategy nameDisplayingStrategy, String absoluteFtlClassPath) {
        super(nameDisplayingStrategy);

        if (!absoluteFtlClassPath.startsWith("/")) {
            throw new IllegalArgumentException("Template's class-path has to start with '/'");
        }
        configuration.setClassForTemplateLoading(ClasspathFreemarkerWebServiceDisplayEngine.class, "/");
        this.absoluteFtlClassPath = absoluteFtlClassPath;
    }

    public static FreemarkerWebServiceDisplayEngine createEngine(JavaNameDisplayStrategy nameDisplayingStrategy, String absoluteFtlClassPath) {
        return new ClasspathFreemarkerWebServiceDisplayEngine(nameDisplayingStrategy, absoluteFtlClassPath);
    }
    
    public static FreemarkerWebServiceDisplayEngine createEngine(SimpleJavaNameDisplayStrategy nameDisplayingStrategy) {
        String ftlPath = "/"  +  ClassNameUtils.getRelativeDir(ClasspathFreemarkerWebServiceDisplayEngine.class.getName()) + "/service.ftl";
        return createEngine(nameDisplayingStrategy, ftlPath);
    }
    
    protected Template getTemplate() {
        try {
            return configuration.getTemplate(absoluteFtlClassPath);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

 

}
