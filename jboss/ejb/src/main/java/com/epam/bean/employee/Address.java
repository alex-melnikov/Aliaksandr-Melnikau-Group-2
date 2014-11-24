package com.epam.bean.employee;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Address
 */
@Embeddable
@Access(AccessType.FIELD)
public class Address
{
    /** City */
    @Column(name = "city")
    private String city;
    /** Street */
    @Column(name = "street")
    private String street;

    /**
     * Default constructor
     */
    public Address()
    {
        super();
    }

    /**
     * Constructor witn parameters
     * @param city
     * @param street
     */
    public Address(String city, String street)
    {
        super();
        this.city = city;
        this.street = street;
    }

    /**
     * get city
     * @return city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * set city
     * @param city
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * get street
     * @return street
     */
    public String getStreet()
    {
        return street;
    }

    /**
     * set street
     * @param street
     */
    public void setStreet(String street)
    {
        this.street = street;
    }

}
