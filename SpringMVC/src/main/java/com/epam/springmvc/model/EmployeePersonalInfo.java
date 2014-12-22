package com.epam.springmvc.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * personal info persistent class model
 *
 */
@Entity
@Table(name = "employee_personal_info")
public class EmployeePersonalInfo implements Serializable
{
    /** unique ID */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "employeePersonalInfo_id")
    private Long id;

    /** characteristics */
    private String characteristic;

    /** employee */
    @OneToOne(mappedBy = "personalInfo")
    private Employee employee;

    /**
     * constructor
     */
    public EmployeePersonalInfo()
    {

    }

    /**
     * constructor
     * 
     * @param characteristic - personal characteristic
     */
    public EmployeePersonalInfo(String characteristic)
    {
        this.characteristic = characteristic;
    }

    /**
     * gets ID
     * 
     * @return ID
     */
    public Long getId()
    {
        return id;
    }

    /**
     * gets characteristics
     * 
     * @return characteristics
     */
    public String getCharacteristic()
    {
        return characteristic;
    }

    /**
     * sets characteristics
     * 
     * @param characteristic - characteristics
     */
    public void setCharacteristic(String characteristic)
    {
        this.characteristic = characteristic;
    }

    /**
     * gets employee instance
     * 
     * @return employee instance
     */
    public Employee getEmployee()
    {
        return employee;
    }
}
