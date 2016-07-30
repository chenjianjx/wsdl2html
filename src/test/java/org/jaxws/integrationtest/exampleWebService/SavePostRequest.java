package org.jaxws.integrationtest.exampleWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * create or update a post
 * 
 * @author chenjianjx@gmail.com
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class SavePostRequest {

	/**
	 * the post's content. Content cannot be null, and must be 3-140 characters
	 */
	@XmlElement(required = true)
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
