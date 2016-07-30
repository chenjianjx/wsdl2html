package org.jaxws.integrationtest.exampleWebService;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * an exemplary soap port
 * 
 * @author chenjianjx@gmail.com
 *
 */
@WebService(name = "BbsWebService", targetNamespace = "https://github.com/chenjianjx/wsdl2html")
public interface BbsWebService {

	@WebMethod
	@WebResult(name = "result")
	public SinglePostResponse newPost(@WebParam(name = "operatorUserId") Long operatorUserId, @WebParam(name = "request") NewPostRequest request);

	@WebMethod
	public SinglePostResponse updatePost(@WebParam(name = "operatorUserId") Long operatorUserId, @WebParam(name = "request") UpdatePostRequest request);

	@WebMethod
	public PostListResponse getAllPosts(@WebParam(name = "operatorUserId") Long operatorUserId);

	@WebMethod
	public PostListResponse getPostsByIds(@WebParam(name = "operatorUserId") Long operatorUserId, @WebParam(name = "postIds") List<Long> postIds);

	@WebMethod
	public List<Post> getAllPostsWithoutOverload(@WebParam(name = "operatorUserId") Long operatorUserId);

	@WebMethod
	public List<Long> getAllPostIdsWithoutOverload(@WebParam(name = "operatorUserId") Long operatorUserId);

	@WebMethod
	public VoidResponse deletePostById(@WebParam(name = "operatorUserId") Long operatorUserId, @WebParam(name = "postId") Long postId);

	@WebMethod
	public void empty();

	@WebMethod
	public boolean oldOrEven(@WebParam(name = "number") int number);

	@WebMethod
	@WebResult(name = "newToken")
	public void refreshToken(@WebParam(name = "oldToken", header = true) String oldToken);

}
