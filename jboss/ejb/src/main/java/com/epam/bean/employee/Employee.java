package com.epam.bean.employee;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Employee
 */
@Entity
@Table(name = "Employee")
public class Employee implements Serializable
{
    /** Id */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    /** First name */
    @Column(name = "firstName")
    private String firstName;
    /** Last name */
    @Column(name = "lastName")
    private String lastName;
    /** Address */
    @Embedded
    private Address address;
    /** Personal info */
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "personalInfoId")
    private PersonalInfo personalInfo;
    
    /** Unit */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "unitId")
    private Unit unit;

    /** Projects */
    @Fetch(FetchMode.JOIN)
    @ManyToMany
    @JoinTable(name = "EmployeeProjects", joinColumns = @JoinColumn(name = "employeeId"), inverseJoinColumns = @JoinColumn(name = "projectId"))
    private List<Project> projects;

    /**
     * Default constructor
     */
    public Employee()
    {
        super();
    }

    /**
     * Constructor with parameters
     * @param firstName
     * @param lastName
     * @param address
     */
    public Employee(String firstName, String lastName, Address address, PersonalInfo personalInfo, Unit unit)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.personalInfo = personalInfo;
        this.unit = unit;
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
     * get first name
     * @return first name
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * set first name
     * @param firstName
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * get last name
     * @return last name
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * set last name
     * @param lastName
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * get address
     * @return address
     */
    public Address getAddress()
    {
        return address;
    }

    /**
     * set address
     * @param address
     */
    public void setAddress(Address address)
    {
        this.address = address;
    }

    /**
     * get personal info
     * @return personalInfo
     */
    public PersonalInfo getPersonalInfo()
    {
        return personalInfo;
    }

    /**
     * set personal info
     * @param personalInfo
     */
    public void setPersonalinfo(PersonalInfo personalInfo)
    {
        this.personalInfo = personalInfo;
    }
    
    /**
     * get unit
     * @return unit
     */
    public Unit getUnit()
    {
        return unit;
    }

    /**
     * set unit
     * @param unit
     */
    public void setUnit(Unit unit)
    {
        this.unit = unit;
    }

    /**
     * get projects
     * @return projects
     */
    public List<Project> getProjects()
    {
        return projects;
    }

    /**
     * set projects
     * @param projects
     */
    public void setProjects(List<Project> projects)
    {
        this.projects = projects;
    }

}
