package org.jaxws.stub2html.exampleWebService;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 * 
 * @author chenjianjx
 * 
 */
public interface OrderSOAPService {

    @WebMethod
    public void placeOrder(@WebParam(name = "user-name") String userName, @WebParam(name = "password") String password, @WebParam(name = "order") Order order);

    @WebMethod
    @WebResult(name = "orders")
    public List<Order> findOrders(@WebParam(name = "user-id") Integer userId);

    @WebMethod
    public void emptyMethod();

}
