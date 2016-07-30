package org.jaxws.stub2html.view;

import org.jaxws.stub2html.model.WebServiceStubSet;

/**
 * Display stubs of a web method as readable String
 * 
 * @author chenjianjx
 * 
 */
public abstract class WebServiceDisplayEngine {

	public abstract String displayWebSerivce(WebServiceStubSet serviceStubSet);

	public WebServiceDisplayEngine() {
		super();
	}

}
