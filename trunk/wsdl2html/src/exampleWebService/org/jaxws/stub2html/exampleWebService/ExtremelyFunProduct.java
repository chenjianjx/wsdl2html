package org.jaxws.stub2html.exampleWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author chenjianjx
 * 
 */

@XmlType(name = "extremely-fun-profuct")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExtremelyFunProduct extends FunProduct{

	@XmlElement(name = "extreme-programmer", required = true)
	private String extremeProgrammer;
}
