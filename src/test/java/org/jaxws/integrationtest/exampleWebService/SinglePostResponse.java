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
public class SinglePostResponse extends AbstractResponse {

	@XmlElement(required = true)
	private Post post;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

}
