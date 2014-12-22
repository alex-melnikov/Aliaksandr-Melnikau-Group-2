package com.epam.springmvc.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.springmvc.model.Address;
import com.epam.springmvc.model.Employee;
import com.epam.springmvc.model.Employee.EmployeeStatus;
import com.epam.springmvc.model.Project;
import com.epam.springmvc.model.Unit;
import com.epam.springmvc.services.IEntityService;
import com.epam.springmvc.services.ServiceException;

/**
 * Employee Controller
 *
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController extends AbstactBaseController
{
    /** injection EntityServiceBean */
    @Autowired
    private IEntityService entityService;
    /** Logger */
    private static Logger logger = Logger.getLogger(EmployeeController.class.getName());

    /**
     * creates instance of Employee
     * 
     * @return tiles definition or redirect url
     */
    @RequestMapping("/create")
    public String addEmployee()
    {
        Employee employee = new Employee();
        try
        {
            entityService.createEmployee(employee);
        }
        catch (ServiceException e)
        {
            logger.error(e);
        }
        return "redirect:/employees";
    }

    /**
     * updates employee by id
     * 
     * @param id - id
     * @param firstName - first name
     * @param lastName - last name
     * @param status - status
     * @param city - city
     * @param street - street
     * @param projectID - project ID
     * @param unitID - unit ID
     * @return tiles or url for redirecting
     */
    @RequestMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") long id,
        @RequestParam(value = "firstName", required = false) String firstName,
        @RequestParam(value = "lastName", required = false) String lastName,
        @RequestParam(value = "status", required = false) String status,
        @RequestParam(value = "city", required = false) String city,
        @RequestParam(value = "street", required = false) String street,
        @RequestParam(value = "projectID", required = false) Long projectID,
        @RequestParam(value = "unitID", required = false) Long unitID)
    {
        try
        {
            Employee employee = entityService.findEmployee(id);
            if (employee != null)
            {
                if (StringUtils.hasText(firstName))
                {
                    employee.setFirstName(firstName);
                }
                if (StringUtils.hasText(lastName))
                {
                    employee.setLastName(lastName);
                }
                if (StringUtils.hasText(status))
                {
                    employee.setStatus(EmployeeStatus.valueOf(status));
                }
                Address address = employee.getAddress() != null ? employee.getAddress() : new Address();
                if (StringUtils.hasText(city))
                {
                    address.setCity(city);
                }
                if (StringUtils.hasText(street))
                {
                    address.setStreet(street);
                }
                employee.setAddress(address);
                if (projectID != null)
                {
                    Project project = entityService.findProject(projectID);
                    employee.assignToProject(project);
                }
                if (unitID != null)
                {
                    Unit unit = entityService.findUnit(unitID);
                    employee.setUnit(unit);
                }
                entityService.updateEmployee(employee);
            }
        }
        catch (ServiceException e)
        {
            logger.error(e);
        }
        return "redirect:/employees";
    }

    /**
     * remove employee by id
     * 
     * @param id - employee id
     * @return tiles or url for redirecting
     */
    @RequestMapping("/remove/{id}")
    public String deleteEmployee(@PathVariable("id") long id)
    {
        try
        {
            entityService.removeEmployee(id);
        }
        catch (ServiceException e)
        {
            logger.error(e);
        }
        return "redirect:/employees";
    }

    /**
     * find all employee
     * 
     * @param model - instance of model
     * @return tiles or url for redirecting
     */
    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model)
    {
        try
        {
            model.addAttribute("employees", entityService.findAllEmployees());
        }
        catch (ServiceException e)
        {
            logger.error(e);
        }
        return "employees";
    }
}
