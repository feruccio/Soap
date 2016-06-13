
package com.haurylenka.projects.soap.services.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getCustomersResponse", namespace = "http://services.soap.projects.haurylenka.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCustomersResponse", namespace = "http://services.soap.projects.haurylenka.com/")
public class GetCustomersResponse {

    @XmlElement(name = "Customer", namespace = "")
    private List<com.haurylenka.projects.soap.beans.Customer> _return;

    /**
     * 
     * @return
     *     returns List<Customer>
     */
    public List<com.haurylenka.projects.soap.beans.Customer> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<com.haurylenka.projects.soap.beans.Customer> _return) {
        this._return = _return;
    }

}
