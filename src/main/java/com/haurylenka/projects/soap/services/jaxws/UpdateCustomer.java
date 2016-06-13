
package com.haurylenka.projects.soap.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "updateCustomer", namespace = "http://services.soap.projects.haurylenka.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateCustomer", namespace = "http://services.soap.projects.haurylenka.com/", propOrder = {
    "code",
    "id",
    "email",
    "phone"
})
public class UpdateCustomer {

    @XmlElement(name = "code", namespace = "", required = true)
    private String code;
    @XmlElement(name = "id", namespace = "", required = true)
    private int id;
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
     *     returns int
     */
    public int getId() {
        return this.id;
    }

    /**
     * 
     * @param id
     *     the value for the id property
     */
    public void setId(int id) {
        this.id = id;
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
