package com.epam.springmvc.services;

import java.util.Set;
import com.epam.springmvc.model.Employee;
import com.epam.springmvc.model.Project;
import com.epam.springmvc.model.Unit;

/**
 * entity service EJB local
 */
public interface IEntityService
{
    /**
     * create employee
     * 
     * @param employee - Employee instance
     * @throws ServiceException if employee is null
     */
    void createEmployee(Employee employee) throws ServiceException;

    /**
     * update employee
     * 
     * @param employee - Employee instance
     * @throws ServiceException if employee is null
     */
    void updateEmployee(Employee employee) throws ServiceException;

    /**
     * find employee by id
     * 
     * @param id - Employee id
     * @throws ServiceException if id is null
     */
    Employee findEmployee(Long id) throws ServiceException;

    /**
     * find all employees
     * 
     * @return List of employees
     * @throws ServiceException - any unexpected exception
     */
    Set<Employee> findAllEmployees() throws ServiceException;

    /**
     * remove employee by id
     * 
     * @param id - Employee id
     * @throws ServiceException if id is null
     */
    void removeEmployee(Long id) throws ServiceException;

    /**
     * create project
     * 
     * @param project - Project instance
     * @throws ServiceException if project is null
     */
    void createProject(Project project) throws ServiceException;

    /**
     * update project
     * 
     * @param project - Project instance
     * @throws ServiceException if project is null
     */
    void updateProject(Project project) throws ServiceException;

    /**
     * find project by id
     * 
     * @param id - Project id
     * @throws ServiceException if id is null
     */
    Project findProject(Long id) throws ServiceException;

    /**
     * find all projects
     * 
     * @return List of projects
     * @throws ServiceException - any unexpected exception
     */
    Set<Project> findAllProjects() throws ServiceException;

    /**
     * remove project by id
     * 
     * @param id - Project id
     * @throws ServiceException if id is null
     */
    void removeProject(Long id) throws ServiceException;

    /**
     * create unit
     * 
     * @param unit - Unit instance
     * @throws ServiceException if unit is null
     */
    void createUnit(Unit unit) throws ServiceException;

    /**
     * update unit
     * 
     * @param unit - Unit instance
     * @throws ServiceException if unit is null
     */
    void updateUnit(Unit unit) throws ServiceException;

    /**
     * find unit by id
     * 
     * @param id - Unit id
     * @throws ServiceException if id is null
     */
    Unit findUnit(Long id) throws ServiceException;

    /**
     * find all units
     * 
     * @return List of units
     * @throws ServiceException - any unexpected exception
     */
    Set<Unit> findAllUnits() throws ServiceException;

    /**
     * remove unit by id
     * 
     * @param id - Unit id
     * @throws ServiceException if id is null
     */
    void removeUnit(Long id) throws ServiceException;
}
