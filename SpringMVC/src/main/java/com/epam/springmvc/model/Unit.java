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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * unit persistent class model
 */
@Entity
@Table(name = "units")
public class Unit implements Serializable
{
    /** unique ID */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "unit_id")
    private Long id;

    /**
     * bidirectional mapping to employee, employee and unit can exist without each other
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "units_employees", joinColumns = @JoinColumn(name = "unit_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<Employee> employees = new LinkedHashSet<Employee>();

    /**
     * constructor
     */
    public Unit()
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
     * gets assigned employees
     * 
     * @return assigned employees
     */
    public Set<Employee> getEmployees()
    {
        return new LinkedHashSet<Employee>(employees);
    }

    /**
     * add employee
     * 
     * @param employee - instance of employee
     * @return - true if success added
     */
    public boolean addEmployee(Employee employee)
    {
        return employees.add(employee);
    }

    /**
     * remove employee
     * 
     * @param employee - instance of employee
     * @return - true if success removed
     */
    public boolean removeEmployee(Employee employee)
    {
        return employees.remove(employee);
    }
}
