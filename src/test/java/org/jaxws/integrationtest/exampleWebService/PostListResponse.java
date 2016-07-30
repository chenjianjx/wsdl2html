package org.jaxws.integrationtest.exampleWebService;

import java.util.List;

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
public class PostListResponse extends AbstractResponse {
	@XmlElement(required = false)
	private List<Post> posts;

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

}
