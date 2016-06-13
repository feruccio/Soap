
package com.haurylenka.projects.soap.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "deleteCustomerResponse", namespace = "http://services.soap.projects.haurylenka.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteCustomerResponse", namespace = "http://services.soap.projects.haurylenka.com/")
public class DeleteCustomerResponse {

    @XmlElement(name = "Customer", namespace = "")
    private com.haurylenka.projects.soap.beans.Customer _return;

    /**
     * 
     * @return
     *     returns Customer
     */
    public com.haurylenka.projects.soap.beans.Customer getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.haurylenka.projects.soap.beans.Customer _return) {
        this._return = _return;
    }

}
