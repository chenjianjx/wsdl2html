package org.jaxws.integrationtest.exampleWebService.deprecated;

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
@XmlType(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({FunProduct.class, NotFunProduct.class})
public abstract class Product {

	@XmlElement(required = true)
	private String productName;

}
