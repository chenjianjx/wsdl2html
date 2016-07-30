package org.jaxws.stub2html.view.freemarker;

import org.jaxws.stub2html.model.WebServiceStubSet;
import org.jaxws.stub2html.view.WebServiceDisplayEngine;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 
 * @author chenjianjx
 * 
 */
public abstract class FreemarkerWebServiceDisplayEngine extends WebServiceDisplayEngine {

    protected Configuration configuration;

    public FreemarkerWebServiceDisplayEngine() {
        super();
        configuration = new Configuration();
        configuration.setLocalizedLookup(false);
        configuration.setObjectWrapper(new DefaultObjectWrapper());
    }

    @Override
    public String displayWebSerivce(WebServiceStubSet serviceStubSet) {
        Template template = getTemplate();
        FreemarkerWebServiceDisplayer displayer = new FreemarkerWebServiceDisplayer(template, serviceStubSet);
        return displayer.displayWebSerivce();
    }

    protected abstract Template getTemplate();

}
