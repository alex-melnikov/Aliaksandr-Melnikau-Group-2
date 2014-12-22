package com.epam.springmvc.services;

import java.util.Set;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epam.springmvc.dao.IEmployeeDAO;
import com.epam.springmvc.dao.IProjectDAO;
import com.epam.springmvc.dao.IUnitDAO;
import com.epam.springmvc.model.Employee;
import com.epam.springmvc.model.Project;
import com.epam.springmvc.model.Unit;

/**
 * entity service EJB, allows to do any CRUD operations on entity classes
 *
 */
@Service("entityService")
public class EntityServiceBean implements IEntityService
{
    @Resource(name = "projectDAO")
    private IProjectDAO projectDAO;

    @Autowired
    private IUnitDAO unitDAO;

    private IEmployeeDAO employeeDAO;

    @Autowired
    public EntityServiceBean(IEmployeeDAO employeeDAO)
    {
        this.employeeDAO = employeeDAO;
    }

    /**
     * create employee
     * 
     * @param employee - Employee instance
     * @throws ServiceException if employee is null
     */
    public void createEmployee(Employee employee) throws ServiceException
    {
        if (employee == null)
        {
            throw new ServiceException("employee can't be null");
        }
        employeeDAO.create(employee);
    }

    /**
     * update employee
     * 
     * @param employee - Employee instance
     * @throws ServiceException if employee is null
     */
    public void updateEmployee(Employee employee) throws ServiceException
    {
        if (employee == null)
        {
            throw new ServiceException("employee can't be null");
        }
        employeeDAO.update(employee);
    }

    /**
     * find employee by id
     * 
     * @param id - Employee id
     * @throws ServiceException if id is null
     */
    public Employee findEmployee(Long id) throws ServiceException
    {
        if (id == null)
        {
            throw new ServiceException("id can't be null");
        }
        return employeeDAO.find(id);
    }

    /**
     * find all employees
     * 
     * @return List of employees
     * @throws ServiceException - any unexpected exception
     */
    public Set<Employee> findAllEmployees() throws ServiceException
    {
        try
        {
            return employeeDAO.findAll();
        }
        catch (Exception e)
        {
            throw new ServiceException("can't retrieve objects: ", e);
        }
    }

    /**
     * remove employee by id
     * 
     * @param id - Employee id
     * @throws ServiceException if id is null
     */
    public void removeEmployee(Long id) throws ServiceException
    {
        if (id == null)
        {
            throw new ServiceException("id can't be null");
        }
        employeeDAO.delete(id);
    }

    /**
     * create project
     * 
     * @param project - Project instance
     * @throws ServiceException if project is null
     */
    public void createProject(Project project) throws ServiceException
    {
        if (project == null)
        {
            throw new ServiceException("project can't be null");
        }
        projectDAO.create(project);
    }

    /**
     * update project
     * 
     * @param project - Project instance
     * @throws ServiceException if project is null
     */
    public void updateProject(Project project) throws ServiceException
    {
        if (project == null)
        {
            throw new ServiceException("project can't be null");
        }
        projectDAO.update(project);
    }

    /**
     * find project by id
     * 
     * @param id - Project id
     * @throws ServiceException if id is null
     */
    public Project findProject(Long id) throws ServiceException
    {
        if (id == null)
        {
            throw new ServiceException("id can't be null");
        }
        return projectDAO.find(id);
    }

    /**
     * find all projects
     * 
     * @return List of projects
     * @throws ServiceException - any unexpected exception
     */
    public Set<Project> findAllProjects() throws ServiceException
    {
        try
        {
            return projectDAO.findAll();
        }
        catch (Exception e)
        {
            throw new ServiceException("can't retrieve objects: ", e);
        }
    }

    /**
     * remove project by id
     * 
     * @param id - Project id
     * @throws ServiceException if id is null
     */
    public void removeProject(Long id) throws ServiceException
    {
        if (id == null)
        {
            throw new ServiceException("id can't be null");
        }
        projectDAO.delete(id);

    }

    /**
     * create unit
     * 
     * @param unit - Unit instance
     * @throws ServiceException if unit is null
     */
    public void createUnit(Unit unit) throws ServiceException
    {
        if (unit == null)
        {
            throw new ServiceException("unit can't be null");
        }
        unitDAO.create(unit);

    }

    /**
     * update unit
     * 
     * @param unit - Unit instance
     * @throws ServiceException if unit is null
     */
    public void updateUnit(Unit unit) throws ServiceException
    {
        if (unit == null)
        {
            throw new ServiceException("unit can't be null");
        }
        unitDAO.update(unit);

    }

    /**
     * find unit by id
     * 
     * @param id - Unit id
     * @throws ServiceException if id is null
     */
    public Unit findUnit(Long id) throws ServiceException
    {
        if (id == null)
        {
            throw new ServiceException("id can't be null");
        }
        return unitDAO.find(id);
    }

    /**
     * find all units
     * 
     * @return List of units
     * @throws ServiceException - any unexpected exception
     */
    public Set<Unit> findAllUnits() throws ServiceException
    {
        try
        {
            return unitDAO.findAll();
        }
        catch (Exception e)
        {
            throw new ServiceException("can't retrieve objects: ", e);
        }
    }

    /**
     * remove unit by id
     * 
     * @param id - Unit id
     * @throws ServiceException if id is null
     */
    public void removeUnit(Long id) throws ServiceException
    {
        if (id == null)
        {
            throw new ServiceException("id can't be null");
        }
        unitDAO.delete(id);
    }
}
