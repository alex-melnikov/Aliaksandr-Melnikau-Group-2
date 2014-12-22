package com.epam.springmvc.dao.impl;

import org.springframework.stereotype.Repository;
import com.epam.springmvc.dao.IEmployeeDAO;
import com.epam.springmvc.model.Employee;

/**
 * IEmployeeDAO implementation
 */
@Repository("employeeDAO")
public class EmployeeDAO extends AbstractDAO<Employee> implements IEmployeeDAO
{
    /**
     * constructor
     */
    public EmployeeDAO()
    {
        super(Employee.class);
    }
}