package com.epam.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.epam.bean.employee.Employee;
import com.epam.bean.employee.Project;
import com.epam.bean.employee.Unit;
import com.epam.dao.interfaces.IEmployeeLocal;
import com.epam.dao.interfaces.IEmployeeRemote;

/**
 * Employee service implementation
 */
@Stateless(name = "TestProxy")
public class EmployeeLocalImpl implements IEmployeeLocal, IEmployeeRemote
{
    @PersistenceContext
    private EntityManager em;

    /**
     * get entity manager
     * @return entity manager
     */
    public EntityManager getEm()
    {
        return em;
    }

    /**
     * set entity manager
     * @param em
     */
    public void setEm(EntityManager em)
    {
        this.em = em;
    }

    @Override
    public boolean createEmployee(Employee employee)
    {
        if (employee != null)
        {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateEmployee(Employee employee)
    {
        if (employee != null)
        {
            em.getTransaction().begin();
            em.merge(employee);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(int id)
    {
        if (id >= 0)
        {
            Employee employee = em.find(Employee.class, id);
            if (employee != null)
            {
                em.getTransaction().begin();
                em.remove(employee);
                em.getTransaction().commit();
                return true;
            }
        }
        return false;
    }

    @Override
    public Employee findEmployee(int id)
    {
        Employee employee = null;
        if (id >= 0)
        {
            employee = em.find(Employee.class, id);
        }
        return employee;
    }

    @Override
    public boolean assignEmployeeToProject(int employeeId, int projectId)
    {
        if (employeeId >= 0 && projectId >= 0)
        {
            Project project = em.find(Project.class, projectId);
            Employee employee = em.find(Employee.class, employeeId);
            if (project != null && employee != null)
            {
                em.getTransaction().begin();
                project.getEmployees().add(employee);
                employee.getProjects().add(project);
                em.getTransaction().commit();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addEmployeeToUnit(int employeeId, int unitId)
    {
        if (employeeId >= 0 && unitId >= 0)
        {
            Unit unit = em.find(Unit.class, unitId);
            Employee employee = em.find(Employee.class, employeeId);
            if (unit != null && employee != null)
            {
                em.getTransaction().begin();
                unit.getEmployees().add(employee);
                employee.setUnit(unit);
                em.getTransaction().commit();
                return true;
            }
        }
        return false;
    }

}
