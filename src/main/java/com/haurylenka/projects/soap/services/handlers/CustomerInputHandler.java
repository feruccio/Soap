package com.haurylenka.projects.soap.services.handlers;

import javax.xml.bind.JAXBContext;
import javax.xml.ws.LogicalMessage;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

import com.haurylenka.projects.soap.services.jaxws.AddCustomer;
import com.haurylenka.projects.soap.services.jaxws.DeleteCustomer;
import com.haurylenka.projects.soap.services.jaxws.GetCustomer;
import com.haurylenka.projects.soap.services.jaxws.GetCustomers;
import com.haurylenka.projects.soap.services.jaxws.UpdateCustomer;

public class CustomerInputHandler implements LogicalHandler<LogicalMessageContext> {

	@Override
	public void close(MessageContext arg0) {
		// nothing
	}

	@Override
	public boolean handleFault(LogicalMessageContext arg0) {
		return true;
	}

	@Override
	public boolean handleMessage(LogicalMessageContext ctx) {
		Boolean outbound = (Boolean) ctx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (!outbound) {
			try {
				LogicalMessage msg = ctx.getMessage();
				JAXBContext jaxbCtx = JAXBContext.newInstance(
						GetCustomers.class, GetCustomer.class, 
						AddCustomer.class, UpdateCustomer.class, 
						DeleteCustomer.class);
				Object payload = msg.getPayload(jaxbCtx);
				String code;
				int id = 0;
				Class<?> clazz = payload.getClass();
				if (clazz == DeleteCustomer.class 
						|| clazz == GetCustomer.class 
						|| clazz == UpdateCustomer.class) {
					if (clazz == DeleteCustomer.class) {
						DeleteCustomer delCust = (DeleteCustomer) payload;
						code = delCust.getCode();
						id = delCust.getId();
					} else if (payload.getClass() == GetCustomer.class) {
						GetCustomer getCust = (GetCustomer) payload;
						code = getCust.getCode();
						id = getCust.getId();
					} else {
						UpdateCustomer updCust = (UpdateCustomer) payload;
						code = updCust.getCode();
						id = updCust.getId();
					}
					if (id <= 0) {
						throw new WebServiceException(
								"Id is required and must be greater than 0");
					}
				} else if (payload.getClass() == AddCustomer.class) {
					AddCustomer addCust = (AddCustomer) payload;
					code = addCust.getCode();
				} else {
					GetCustomers getCusts = (GetCustomers) payload;
					code = getCusts.getCode();
				}
				if (code == null || code.length() == 0) {
					throw new RuntimeException(
							"Reservation code is required");
				}
			} catch (Exception e) {
				throw new WebServiceException(e);
			}
		}
		return true;
	}

}
