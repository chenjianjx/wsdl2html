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

@XmlType(name = "common-fun-profuct")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommonFunProduct  extends FunProduct{

	
	@XmlElement(name = "common-level", required = true)
	private CommonLevel level;
	
	@XmlElement(name = "happy-being-common")
	private boolean happyBeingCommon;
}
