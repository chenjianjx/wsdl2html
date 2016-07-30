package org.jaxws.integrationtest.exampleWebService.deprecated;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * 
 * @author chenjianjx
 * 
 */
@WebService
public interface ProductPort {

	@WebMethod
	public void placeOrder(@WebParam(name = "userName") String userName, @WebParam(name = "password") String password, @WebParam(name = "order") Order order);

	@WebMethod
	@WebResult(name = "orders")
	public List<Order> findOrders(@WebParam(name = "user-id") Integer userId);

	@WebMethod
	public void emptyMethod();

}
