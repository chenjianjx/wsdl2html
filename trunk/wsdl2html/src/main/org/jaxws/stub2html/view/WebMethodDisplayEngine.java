package org.jaxws.stub2html.view;

import org.jaxws.stub2html.model.WebMethodStubSet;


/**
 * Display stubs of a web method as readable String
 * 
 * @author chenjianjx
 * 
 */
public abstract class WebMethodDisplayEngine {

    protected JavaNameDisplayStrategy nameDisplayingStrategy;

    public abstract String displayWebMethod(WebMethodStubSet methodStubSet);

    public WebMethodDisplayEngine(JavaNameDisplayStrategy nameDisplayingStrategy) {
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
