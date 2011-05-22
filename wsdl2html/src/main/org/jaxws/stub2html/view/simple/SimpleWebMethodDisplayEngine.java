package org.jaxws.stub2html.view.simple;

import org.jaxws.stub2html.model.WebMethodStubSet;
import org.jaxws.stub2html.view.JavaNameDisplayStrategy;
import org.jaxws.stub2html.view.WebMethodDisplayEngine;

/**
 * Display stubs as simple HTML Tables
 * 
 * @author chenjianjx
 * 
 */
public class SimpleWebMethodDisplayEngine extends WebMethodDisplayEngine {

    public SimpleWebMethodDisplayEngine(JavaNameDisplayStrategy nameDisplayingStrategy) {
        super(nameDisplayingStrategy);
    }

    public String displayWebMethod(WebMethodStubSet methodStubSet) {
        SimpleWebMethodDisplayer displayer = new SimpleWebMethodDisplayer(methodStubSet, nameDisplayingStrategy);
        return displayer.displayWebMethod();
    }

}
