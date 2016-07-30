package org.jaxws.integrationtest.exampleWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * update a post
 * 
 * @author chenjianjx@gmail.com
 *
 */
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class UpdatePostRequest extends SavePostRequest {

	/**
	 * the postId.
	 */
	@XmlElement(required = true)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long postId) {
		this.id = postId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
