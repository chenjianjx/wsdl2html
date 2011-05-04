package org.jaxws.stub2html.exampleWebService;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author chenjianjx
 *
 */

@XmlType(name = "not-fun-profuct")
@XmlAccessorType(XmlAccessType.FIELD)
public class NotFunProduct extends Product{

	
	@XmlElement(name = "get-not-fun-date", required = true)
	private Date getNotFunDate;
}
