package org.jaxws.integrationtest.exampleWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * create a post
 * 
 * @author chenjianjx@gmail.com
 *
 */
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class NewPostRequest extends SavePostRequest {

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
