package com.haurylenka.projects.soap.impls;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.haurylenka.projects.soap.beans.Customer;
import com.haurylenka.projects.soap.beans.Reservation;
import com.haurylenka.projects.soap.exceptions.CustomerDAOException;
import com.haurylenka.projects.soap.interfaces.CustomerDAO;

public class XMLCustomerDAOImpl implements CustomerDAO {
	
	@Override
	public List<Customer> getCustomers(String code) 
			throws CustomerDAOException {
		try {
			List<Customer> customers = null;
			Reservation reservation = getReservation(code);
			if (reservation != null) {
				customers = reservation.getCustomers();
			}
			return customers;
		} catch (JAXBException e) {
			throw new CustomerDAOException("Unable to get customers", e);
		} catch (IOException e) {
			throw new CustomerDAOException("Unable to find a resource", e);
		}
	}

	@Override
	public Customer getCustomer(String code, int id) 
			throws CustomerDAOException {
		try {
			List<Customer> customers = getCustomers(code);
			return getCustomer(customers, id);
		} catch (CustomerDAOException e) {
			throw new CustomerDAOException("Unable to get customer", e);
		}
	}
	
	private Customer getCustomer(List<Customer> customers, int id) {
		Customer customer = null;
		if (customers != null) {
			for (Customer cust : customers) {
				if (id == cust.getId()) {
					customer = cust;
					break;
				}
			}
		}
		return customer;
	}

	@Override
	public Customer addCustomer(String code, Customer customer) 
			throws CustomerDAOException {
		try {
			Reservation reservation = getReservation(code);
			List<Customer> customers = reservation.getCustomers();
			if (customers == null) {
				customers = new ArrayList<>();
			}
			customer.setId(generateId(customers));
			customers.add(customer);
			reservation.setCustomers(customers);
			saveReservation(reservation);
			return customer;
		} catch (JAXBException e) {
			throw new CustomerDAOException("Unable to get customers", e);
		} catch (IOException e) {
			throw new CustomerDAOException("Unable to find a resource", e);
		}
	}
	
	private int generateId(List<Customer> customers) {
		int id = 0;
		for (Customer customer : customers) {
			if (id < customer.getId()) {
				id = customer.getId();
			}
		}
		return ++id;
	}

	@Override
	public Customer updateCustomer(String code, Customer customer) 
			throws CustomerDAOException {
		try {
			Reservation reservation = getReservation(code);
			List<Customer> customers = reservation.getCustomers();
			Customer customerPers = getCustomer(customers, customer.getId());
			if (customerPers != null) {
				customerPers = merge(customerPers, customer);
				saveReservation(reservation);
			}
			return customerPers;
		} catch (JAXBException e) {
			throw new CustomerDAOException("Unable to update a customer", e);
		} catch (IOException e) {
			throw new CustomerDAOException("Unable to find a resource", e);
		}
	}

	private Customer merge(Customer customerPers, Customer customer) {
		String email = customer.getEmail();
		if (email != null && email.length() > 0) {
			customerPers.setEmail(email);
		}
		String phone = customer.getPhone();
		if (phone != null && phone.length() > 0) {
			customerPers.setPhone(phone);
		}
		return customerPers;
	}

	@Override
	public Customer deleteCustomer(String code, int id) 
			throws CustomerDAOException {
		try {
			Customer customer = null;
			Reservation reservation = getReservation(code);
			List<Customer> customers = reservation.getCustomers();
			Iterator<Customer> it = customers.iterator();
			while (it.hasNext()) {
				Customer cust = it.next();
				if (id == cust.getId()) {
					it.remove();
					saveReservation(reservation);
					customer = cust;
					break;
				}
			}
			return customer;
		} catch (JAXBException e) {
			throw new CustomerDAOException("Unable to delete a customer", e);
		} catch (IOException e) {
			throw new CustomerDAOException("Unable to find a resource", e);
		}
		
	}
	
	private Reservation getReservation(String code) 
			throws JAXBException, IOException {
		Reservation result = null;
		String resourcePath = getResourcePath();
		File file = new File(resourcePath);
		JAXBContext ctx = JAXBContext.newInstance(Reservation.class);
		Unmarshaller mrlr = ctx.createUnmarshaller();
		Reservation reservation = (Reservation) mrlr.unmarshal(file);
		String resCode = reservation.getCode();
		if (code.equals(resCode)) {
			result = reservation;
		}
		return result;
	}
	
	private void saveReservation(Reservation reservation) 
			throws JAXBException, IOException {
		String resourcePath = getResourcePath();
		File file = new File(resourcePath);
		JAXBContext ctx = JAXBContext.newInstance(Reservation.class);
		Marshaller mrlr = ctx.createMarshaller();
		mrlr.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		synchronized (XMLCustomerDAOImpl.class) {
			mrlr.marshal(reservation, file);
		}
	}
	
	private String getResourcePath() throws IOException {
		Properties props = new Properties();
		InputStream is = XMLCustomerDAOImpl.class.getClassLoader()
				.getResourceAsStream("config.properties");
		props.load(is);
		return props.getProperty("resourcePath");
	}

}
