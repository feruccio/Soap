package com.haurylenka.projects.soap.services;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import com.haurylenka.projects.soap.beans.Customer;
import com.haurylenka.projects.soap.exceptions.CustomerDAOException;
import com.haurylenka.projects.soap.exceptions.SoapException;
import com.haurylenka.projects.soap.exceptions.SoapImplLoadException;
import com.haurylenka.projects.soap.interfaces.CustomerDAO;

@WebService(endpointInterface = 
		"com.haurylenka.projects.soap.services.CustomerService")
public class CustomerServiceImpl implements CustomerService {

	@Resource
	private WebServiceContext wsContext;
	
	@Override
	public List<Customer> getCustomers(String code) {
		try {
			CustomerDAO custDAO = getCustomerDAOImpl();
			return custDAO.getCustomers(code);
		} catch (SoapImplLoadException | CustomerDAOException e) {
			throw new SoapException("Soap web service exception", e);
		}
	}

	@Override
	public Customer getCustomer(String code, int id) {
		try {
			CustomerDAO custDAO = getCustomerDAOImpl();
			return custDAO.getCustomer(code, id);
		} catch (SoapImplLoadException | CustomerDAOException e) {
			throw new SoapException("Soap web service exception", e);
		}
	}

	@Override
	public Customer addCustomer(String code, String email, String phone) {
		try {
			CustomerDAO custDAO = getCustomerDAOImpl();
			Customer customer = new Customer();
			customer.setEmail(email);
			customer.setPhone(phone);
			return custDAO.addCustomer(code, customer);
		} catch (SoapImplLoadException | CustomerDAOException e) {
			throw new SoapException("Soap web service exception", e);
		}
	}

	@Override
	public Customer updateCustomer(
			String code, int id, String email, String phone) {
		try {
			CustomerDAO custDAO = getCustomerDAOImpl();
			Customer customer = new Customer();
			customer.setId(id);
			customer.setEmail(email);
			customer.setPhone(phone);
			return custDAO.updateCustomer(code, customer);
		} catch (SoapImplLoadException | CustomerDAOException e) {
			throw new SoapException("Soap web service exception", e);
		}
	}

	@Override
	public Customer deleteCustomer(String code, int id) {
		try {
			CustomerDAO custDAO = getCustomerDAOImpl();
			return custDAO.deleteCustomer(code, id);
		} catch (SoapImplLoadException | CustomerDAOException e) {
			throw new SoapException("Soap web service exception", e);
		}
	}

	private CustomerDAO getCustomerDAOImpl() throws SoapImplLoadException {
		try {
			ServletContext context = 
					(ServletContext) wsContext.getMessageContext()
					.get(MessageContext.SERVLET_CONTEXT);
			String customerDAOImplName = 
					context.getInitParameter("customerDAOImpl");
			Class<?> clazz = Class.forName(customerDAOImplName);
			return (CustomerDAO) clazz.newInstance();
		} catch (ClassNotFoundException 
				| InstantiationException 
				| IllegalAccessException e) {
			throw new SoapImplLoadException(
					"Unable to obtain a CustomerDAO implementation", e);
		}
	}

}
