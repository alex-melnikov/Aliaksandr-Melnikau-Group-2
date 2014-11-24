package com.epam.bean.employee;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Personal info
 */
@Entity
@Table(name = "PersonalInfo")
public class PersonalInfo implements Serializable
{
    /** Id */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    /** status */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    /**
     * Default constructor
     */
    public PersonalInfo()
    {
        super();
    }

    /**
     * Constructor with parameters
     * @param status
     */
    public PersonalInfo(Status status)
    {
        super();
        this.status = status;
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
     * set id
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * get status
     * @return status
     */
    public Status getStatus()
    {
        return status;
    }

    /**
     * set status
     * @param status
     */
    public void setStatus(Status status)
    {
        this.status = status;
    }

}
