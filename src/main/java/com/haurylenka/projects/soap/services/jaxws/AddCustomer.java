
package com.haurylenka.projects.soap.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "addCustomer", namespace = "http://services.soap.projects.haurylenka.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addCustomer", namespace = "http://services.soap.projects.haurylenka.com/", propOrder = {
    "code",
    "email",
    "phone"
})
public class AddCustomer {

    @XmlElement(name = "code", namespace = "", required = true)
    private String code;
    @XmlElement(name = "email", namespace = "")
    private String email;
    @XmlElement(name = "phone", namespace = "")
    private String phone;

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

    /**
     * 
     * @return
     *     returns String
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 
     * @param email
     *     the value for the email property
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * 
     * @param phone
     *     the value for the phone property
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
