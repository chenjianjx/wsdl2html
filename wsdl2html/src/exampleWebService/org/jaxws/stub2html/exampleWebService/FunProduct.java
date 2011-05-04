package org.jaxws.stub2html.exampleWebService;

import java.util.Date;

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
@XmlType(name = "fun-profuct")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({CommonFunProduct.class, ExtremelyFunProduct.class})
public class FunProduct extends Product{

	@XmlElement(name = "get-fun-date", required = true)
	private Date getFunDate;
}
