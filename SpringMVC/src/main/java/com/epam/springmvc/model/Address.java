package com.epam.springmvc.model;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * embeddable class, not persistent
 *
 */
@Embeddable
@Access(AccessType.FIELD)
public class Address implements Serializable
{
    /** city name */
    @Column(name = "city_name")
    private String city;

    /** street name */
    @Column(name = "street_name")
    private String street;

    /**
     * constructor
     */
    public Address()
    {

    }

    /**
     * constructor
     * 
     * @param city - city name
     * @param street - street name
     */
    public Address(String city, String street)
    {
        this.city = city;
        this.street = street;
    }

    /**
     * gets city
     * 
     * @return city name
     */
    public String getCity()
    {
        return city;
    }

    /**
     * sets city
     * 
     * @param city - city name
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * gets street
     * 
     * @return street name
     */
    public String getStreet()
    {
        return street;
    }

    /**
     * sets street
     * 
     * @param street - street name
     */
    public void setStreet(String street)
    {
        this.street = street;
    }
}
