package org.jaxws.stub2html.exampleWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author chenjianjx
 * 
 */
@SuppressWarnings("unused")
@XmlType(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({FunProduct.class, NotFunProduct.class})
public abstract class Product {

	@XmlElement(name = "product-name", required = true)
	private String productName;

}
