package com.epam.bean.employee;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Project
 */
@Entity
@Table(name = "Project")
public class Project implements Serializable
{
    /** Id */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    /** Name */
    @Column(name = "name")
    private String name;
    /** Employees */
    @Fetch(FetchMode.JOIN)
    @ManyToMany
    @JoinTable(name = "EmployeeProjects", joinColumns = @JoinColumn(name = "projectId"), inverseJoinColumns = @JoinColumn(name = "employeeId"))
    private List<Employee> employees;

    /**
     * Default constructor
     */
    public Project()
    {
        super();
    }

    /**
     * Constructor with parameters
     * @param name
     */
    public Project(String name)
    {
        super();
        this.name = name;
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
     * get employees
     * @return employees
     */
    public List<Employee> getEmployees()
    {
        return employees;
    }

    /**
     * set employees
     * @param employees
     */
    public void setEmployees(List<Employee> employees)
    {
        this.employees = employees;
    }

}
