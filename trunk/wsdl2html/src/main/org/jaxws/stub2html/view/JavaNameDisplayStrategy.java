package org.jaxws.stub2html.view;

import org.jaxws.stub2html.model.Stub;

/**
 * To display a java-style name? for example, orderId => Order ID
 * 
 * @author chenjianjx
 * 
 */
public interface JavaNameDisplayStrategy {

	public String displayElementName(String stubName) ;

	public String displayDataType(Class<?> type);

	public String displayRequired(Stub stub);

	public String displayMultiOccurs(Stub stub);

	public String displaySubType(Stub stub);

	public String displayReadableClassName(String className);

}
