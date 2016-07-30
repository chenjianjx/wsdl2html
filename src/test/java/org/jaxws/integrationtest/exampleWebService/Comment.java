package org.jaxws.integrationtest.exampleWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author chenjianjx
 *
 */
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Comment {

	/**
	 * comment id
	 */
	@XmlElement(required = true)
	protected Long id;

	/**
	 * the commenter's userId
	 */
	@XmlElement(required = true)
	protected Long userId;

	/**
	 * the comment's content
	 */
	@XmlElement(required = true)
	protected String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
