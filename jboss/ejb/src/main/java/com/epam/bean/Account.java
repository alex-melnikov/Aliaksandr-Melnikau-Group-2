package com.epam.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Account bean
 */
@Entity
@Table(name = "Account")
public class Account implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -3715151883282683782L;
    /** Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Name */
    @Basic
    private String name;
    /** Balance */
    @Basic
    private double balance;
    /** Currency */
    @Basic
    private String currency;

    /**
     * Default constructor
     */
    public Account()
    {
        super();
    }

    /**
     * Constructor with parameters
     * @param name
     * @param balance
     * @param currency
     */
    public Account(String name, double balance, String currency)
    {
        super();
        this.name = name;
        this.balance = balance;
        this.currency = currency;
    }

    /**
     * get id
     * @return id
     */
    public int getId()
    {
        return id;
    }

    /**
     * get name
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * set name
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * get balance
     * @return balance
     */
    public double getBalance()
    {
        return balance;
    }

    /**
     * set balance
     * @param balance
     */
    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    /**
     * get currency
     * @return currency
     */
    public String getCurrency()
    {
        return currency;
    }

    /**
     * set currency
     * @param currency
     */
    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    @Override
    public String toString()
    {
        return "Account [id=" + id + ", name=" + name + ", balance=" + balance + ", currency=" + currency + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(balance);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((currency == null) ? 0 : currency.hashCode());
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Account other = (Account) obj;
        if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
            return false;
        if (currency == null)
        {
            if (other.currency != null)
                return false;
        }
        else if (!currency.equals(other.currency))
            return false;
        if (id != other.id)
            return false;
        if (name == null)
        {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        return true;
    }

}
