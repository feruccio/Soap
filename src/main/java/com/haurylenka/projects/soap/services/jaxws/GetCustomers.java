
package com.haurylenka.projects.soap.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getCustomers", namespace = "http://services.soap.projects.haurylenka.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCustomers", namespace = "http://services.soap.projects.haurylenka.com/")
public class GetCustomers {

    @XmlElement(name = "code", namespace = "", required = true)
    private String code;

    /**
     * 
     * @return
     *     returns String
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 
     * @param code
     *     the value for the code property
     */
    public void setCode(String code) {
        this.code = code;
    }

}
