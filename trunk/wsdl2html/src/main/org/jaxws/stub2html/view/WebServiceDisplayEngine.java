package org.jaxws.stub2html.view;

import org.jaxws.stub2html.model.WebServiceStubSet;


/**
 * Display stubs of a web method as readable String
 * 
 * @author chenjianjx
 * 
 */
public abstract class WebServiceDisplayEngine {

    protected JavaNameDisplayStrategy nameDisplayingStrategy;

    public abstract String displayWebSerivce(WebServiceStubSet serviceStubSet);

    public WebServiceDisplayEngine(JavaNameDisplayStrategy nameDisplayingStrategy) {
        super();
        this.nameDisplayingStrategy = nameDisplayingStrategy;
    }
  
    public JavaNameDisplayStrategy getNameDisplayingStrategy() {
        return nameDisplayingStrategy;
    }

    public void setNameDisplayingStrategy(JavaNameDisplayStrategy nameDisplayingStyle) {
        this.nameDisplayingStrategy = nameDisplayingStyle;
    }

}
