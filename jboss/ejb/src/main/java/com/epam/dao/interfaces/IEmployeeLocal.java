package com.epam.dao.interfaces;

import com.epam.bean.employee.Employee;

/**
 * Employee service local interface
 */
public interface IEmployeeLocal
{
    /**
     * Create new employee
     * @param employee
     * @return isCreated
     */
    public boolean createEmployee(Employee employee);

    /**
     * update employee
     * @return isUpdated
     */
    public boolean updateEmployee(Employee employee);

    /**
     * update employee
     * @param id
     * @return isDeleted
     */
    public boolean deleteEmployee(int id);

    /**
     * find employee
     * @param id
     * @return employee
     */
    public Employee findEmployee(int id);

    /**
     * assign employee for project
     * @param employeeId
     * @param projectId
     * @return isAssigned
     */
    public boolean assignEmployeeToProject(int employeeId, int projectId);

    /**
     * add employee to unit
     * @param employeeId
     * @param unitId
     * @return isAdded
     */
    public boolean addEmployeeToUnit(int employeeId, int unitId);

}
