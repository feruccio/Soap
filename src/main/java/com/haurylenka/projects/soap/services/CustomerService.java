package com.haurylenka.projects.soap.services;

import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.haurylenka.projects.soap.beans.Customer;

@WebService
@HandlerChain(file="../handler-chain.xml")
public interface CustomerService {

	@WebMethod
	@WebResult(name = "Customer")
	List<Customer> getCustomers(@WebParam(name = "code") String code);
	
	@WebMethod
	@WebResult(name = "Customer")
	Customer getCustomer(@WebParam(name = "code") String code, 
						@WebParam(name = "id") int id);
	
	@WebMethod
	@WebResult(name = "Customer")
	Customer addCustomer(@WebParam(name = "code") String code, 
						@WebParam(name = "email") String email,
						@WebParam(name = "phone") String phone);
	
	@WebMethod
	@WebResult(name = "Customer")
	Customer updateCustomer(@WebParam(name = "code") String code, 
							@WebParam(name = "id") int id,
							@WebParam(name = "email") String email,
							@WebParam(name = "phone") String phone);
	
	@WebMethod
	@WebResult(name = "Customer")
	Customer deleteCustomer(@WebParam(name = "code") String code, 
							@WebParam(name = "id") int id);
	
}
