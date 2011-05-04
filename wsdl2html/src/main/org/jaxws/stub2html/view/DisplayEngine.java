package org.jaxws.stub2html.view;

import org.jaxws.stub2html.model.WebMethodStubSet;

/**
 * Display stubs as readable String
 * 
 * @author chenjianjx
 * 
 */
public abstract class DisplayEngine {

	protected WebMethodStubSet methodStubSet;
	protected JavaNameDisplayStrategy nameDisplayingStrategy;

	public abstract String displayWebMethod()  ;

	public DisplayEngine(WebMethodStubSet methodStubSet, JavaNameDisplayStrategy nameDisplayingStrategy) {
		super();
		this.methodStubSet = methodStubSet;
		this.nameDisplayingStrategy = nameDisplayingStrategy;
	}

	public WebMethodStubSet getMethodStubSet() {
		return methodStubSet;
	}

	public void setMethodStubSet(WebMethodStubSet methodStubSet) {
		this.methodStubSet = methodStubSet;
	}

	public JavaNameDisplayStrategy getNameDisplayingStrategy() {
		return nameDisplayingStrategy;
	}

	public void setNameDisplayingStrategy(JavaNameDisplayStrategy nameDisplayingStyle) {
		this.nameDisplayingStrategy = nameDisplayingStyle;
	}

}
