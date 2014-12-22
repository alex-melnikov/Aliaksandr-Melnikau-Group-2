package com.epam.springmvc.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * employee persistent class model
 */
@Entity
@Table(name = "employees")
public class Employee implements Serializable
{
    /**
     * employee status enum
     * 
     */
    public enum EmployeeStatus {
        /** full time work */
        FULL_TIME_EMPLOYE,
        /** part time work */
        PART_TIME_EMPLOYE
    }

    /** unique ID */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "employee_id")
    private Long id;

    /** first name */
    @Column(name = "first_name")
    private String firstName;

    /** last name */
    @Column(name = "last_name")
    private String lastName;

    /** work time status */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    /** address Address */
    @Embedded
    private Address address;

    /** employee personal info EmployeePersonalInfo can't exist without employee */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeePersonalInfo_id")
    private EmployeePersonalInfo personalInfo;

    /**
     * bidirectional mapping to project, project and employee can exist without each other
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "employees_projects", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects = new LinkedHashSet<Project>();

    /**
     * bidirectional mapping to unit, unit and employee can exist without each other
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "units_employees", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "unit_id"))
    private Unit unit;

    /**
     * constructor
     */
    public Employee()
    {

    }

    /**
     * constructor
     * 
     * @param firstName - first name
     * @param lastName - last name
     * @param status - work time status
     * @param address - address
     * @param personalInfo - personal info
     */
    public Employee(String firstName, String lastName, EmployeeStatus status, Address address, EmployeePersonalInfo personalInfo)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.address = address;
        this.personalInfo = personalInfo;
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
     * gets first name
     * 
     * @return first name
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * sets first name
     * 
     * @param firstName - first name
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * gets last name
     * 
     * @return last name
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * sets last name
     * 
     * @param lastName - last name
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * gets work time status
     * 
     * @return work time status
     */
    public EmployeeStatus getStatus()
    {
        return status;
    }

    /**
     * sets work time status
     * 
     * @param status - work time status
     */
    public void setStatus(EmployeeStatus status)
    {
        this.status = status;
    }

    /**
     * gets address
     * 
     * @return address
     */
    public Address getAddress()
    {
        return address;
    }

    /**
     * sets address
     * 
     * @param address - address
     */
    public void setAddress(Address address)
    {
        this.address = address;
    }

    /**
     * gets personal info
     * 
     * @return personal info
     */
    public EmployeePersonalInfo getPersonalInfo()
    {
        return personalInfo;
    }

    /**
     * sets personal info
     * 
     * @param personalInfo - personal info
     */
    public void setPersonalInfo(EmployeePersonalInfo personalInfo)
    {
        this.personalInfo = personalInfo;
    }

    /**
     * gets assigned projects
     * 
     * @return assigned projects
     */
    public Set<Project> getProjects()
    {
        return new LinkedHashSet<Project>(projects);
    }

    /**
     * assign to project
     * 
     * @param project - project
     * @return true if success assigned
     */
    public boolean assignToProject(Project project)
    {
        return projects.add(project);
    }

    /**
     * unassign from project
     * 
     * @param project - project
     * @return true if success unassigned
     */
    public boolean unassignProject(Project project)
    {
        return projects.remove(project);
    }

    /**
     * gets unit
     * 
     * @return unit
     */
    public Unit getUnit()
    {
        return unit;
    }

    /**
     * sets unit
     * 
     * @param unit - unit
     */
    public void setUnit(Unit unit)
    {
        this.unit = unit;
    }
}
