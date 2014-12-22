package com.epam.springmvc.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Project persistent class model
 *
 */
@Entity
@Table(name = "projects")
public class Project implements Serializable
{
    /** unique ID */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "project_id")
    private Long id;

    /**
     * bidirectional mapping to employee, employee and project can exist without each other
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "employees_projects", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<Employee> employees = new LinkedHashSet<Employee>();

    /**
     * constructor
     */
    public Project()
    {

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
     * gets assigned Employees
     * 
     * @return assigned Employees
     */
    public Set<Employee> getEmployees()
    {
        return new LinkedHashSet<Employee>(employees);
    }

    /**
     * add employee to project
     * 
     * @param employee - employee instance
     * @return - true if success added
     */
    public boolean addEmployee(Employee employee)
    {
        return employees.add(employee);
    }

    /**
     * remove employee from project
     * 
     * @param employee - employee instance
     * @return - true if success removed
     */
    public boolean removeEmployee(Employee employee)
    {
        return employees.remove(employee);
    }

}
