package com.epam.springmvc.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.springmvc.model.Employee;
import com.epam.springmvc.model.Unit;
import com.epam.springmvc.services.IEntityService;
import com.epam.springmvc.services.ServiceException;

/**
 * Unit Controller
 */
@Controller
@RequestMapping("/units")
public class UnitController extends AbstactBaseController
{
    /** injection instance of EntityServiceBean */
    @Autowired
    private IEntityService entityService;
    /** Logger */
    private static Logger logger = Logger.getLogger(UnitController.class.getName());

    /**
     * creates random instance of unit
     * 
     * @return tiles or url for redirecting
     */
    @RequestMapping("/create")
    public String createUnit()
    {
        Unit unit = new Unit();
        try
        {
            entityService.createUnit(unit);
        }
        catch (ServiceException e)
        {
            logger.error(e);
        }
        return "redirect:/units";
    }

    /**
     * updates unit by id
     * 
     * @param id - unit id
     * @param employeeID - employee id
     * @return tiles or url for redirecting
     */
    @RequestMapping("/update/{id}")
    public String updateUnit(@PathVariable("id") long id, @RequestParam(value = "employeeID", required = false) Long employeeID)
    {
        if (employeeID != null)
        {
            try
            {
                Unit unit = entityService.findUnit(id);
                if (unit != null)
                {
                    Employee employee = entityService.findEmployee(employeeID);
                    unit.addEmployee(employee);
                    entityService.updateUnit(unit);
                }
            }
            catch (ServiceException e)
            {
                logger.error(e);
            }
        }
        return "redirect:/units";
    }

    /**
     * remove unit by id
     * 
     * @param id - unit id
     * @return tiles or url for redirecting
     */
    @RequestMapping("/remove/{id}")
    public String deleteUnit(@PathVariable("id") long id)
    {
        try
        {
            entityService.removeUnit(id);
        }
        catch (ServiceException e)
        {
            logger.error(e);
        }
        return "redirect:/units";
    }

    /**
     * find all unit
     * 
     * @param model - instance of model
     * @return tiles or url for redirecting
     */
    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model)
    {
        try
        {
            model.addAttribute("units", entityService.findAllUnits());
        }
        catch (ServiceException e)
        {
            logger.error(e);
        }
        return "units";
    }
}
