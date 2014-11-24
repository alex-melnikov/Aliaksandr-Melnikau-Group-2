package com.epam.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * ExchangeRate bean
 */
@Entity
public class ExchangeRate implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -8446275418774211508L;
    /** Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** First currency */
    @Basic
    private String fromCurrency;
    /** Second currency */
    @Basic
    private String toCurrency;
    /** Rate */
    @Basic
    private double rate;

    /**
     * get id
     * @return id
     */
    public int getId()
    {
        return id;
    }

    /**
     * get fromCurrency
     * @return fromCurrency
     */
    public String getFromCurrency()
    {
        return fromCurrency;
    }

    /**
     * set fromCurrency
     * @param fromCurrency
     */
    public void setFromCurrency(String fromCurrency)
    {
        this.fromCurrency = fromCurrency;
    }

    /**
     * get toCurrency
     * @return toCurrency
     */
    public String getToCurrency()
    {
        return toCurrency;
    }

    /**
     * set toCurrency
     * @param toCurrency
     */
    public void setToCurrency(String toCurrency)
    {
        this.toCurrency = toCurrency;
    }

    /**
     * get rate
     * @return rate
     */
    public double getRate()
    {
        return rate;
    }

    /**
     * set rate
     * @param rate
     */
    public void setRate(double rate)
    {
        this.rate = rate;
    }

}
