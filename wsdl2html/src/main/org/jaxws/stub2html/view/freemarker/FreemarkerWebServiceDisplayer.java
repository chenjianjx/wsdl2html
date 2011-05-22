package org.jaxws.stub2html.view.freemarker;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jaxws.stub2html.model.WebServiceStubSet;
import org.jaxws.stub2html.view.JavaNameDisplayStrategy;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 
 * @author chenjianjx
 * 
 */
public class FreemarkerWebServiceDisplayer {

    private Template template;
    private WebServiceStubSet serviceStubSet;
    private JavaNameDisplayStrategy nameDisplayingStrategy;
    
    FreemarkerWebServiceDisplayer(Template template, JavaNameDisplayStrategy nameDisplayingStrategy, WebServiceStubSet serviceStubSet){
        this.template = template;
        this.nameDisplayingStrategy = nameDisplayingStrategy;
        this.serviceStubSet = serviceStubSet;
    }
    

    public String displayWebSerivce() {

        try {

            Map<String, Object> rootMap = new HashMap<String, Object>();
            rootMap.put("service", serviceStubSet);

            rootMap.put("elementType", new DisplayElementTypeMethodModel());
            rootMap.put("elementName", new DisplayElementNameMethodModel());
            rootMap.put("className", new DisplayReadableClassNameMethodModel());

            StringWriter out = new StringWriter();
            template.process(rootMap, out);
            return out.toString();

        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (TemplateException e) {
            throw new IllegalStateException(e);
        }

    }

    private Class<?> toClass(String className) {
        if (className.equals("byte"))
            return byte.class;
        if (className.equals("short"))
            return short.class;
        if (className.equals("int"))
            return int.class;
        if (className.equals("long"))
            return long.class;
        if (className.equals("char"))
            return char.class;
        if (className.equals("float"))
            return float.class;
        if (className.equals("double"))
            return double.class;
        if (className.equals("boolean"))
            return boolean.class;
        if (className.equals("void"))
            return void.class;

        try {
            return serviceStubSet.getWebServiceClass().getClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private final class DisplayElementTypeMethodModel implements TemplateMethodModel {

        @SuppressWarnings("rawtypes")
        @Override
        public Object exec(List arguments) throws TemplateModelException {
            String className = (String) arguments.get(0);
            Class<?> clazz = toClass(className);
            return nameDisplayingStrategy.displayElementType(clazz);
        }

    }

    private final class DisplayElementNameMethodModel implements TemplateMethodModel {

        @SuppressWarnings("rawtypes")
        @Override
        public Object exec(List arguments) throws TemplateModelException {
            return nameDisplayingStrategy.displayElementName((String) arguments.get(0));
        }

    }

    private final class DisplayReadableClassNameMethodModel implements TemplateMethodModel {

        @SuppressWarnings("rawtypes")
        @Override
        public Object exec(List arguments) throws TemplateModelException {
            String className = (String) arguments.get(0);
            Class<?> clazz = toClass(className);
            return nameDisplayingStrategy.displayClassName(clazz);
        }

    }

}
