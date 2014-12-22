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
import com.epam.springmvc.model.Project;
import com.epam.springmvc.services.IEntityService;
import com.epam.springmvc.services.ServiceException;

/**
 * Project Controller
 */
@Controller
@RequestMapping("/projects")
public class ProjectController extends AbstactBaseController
{
    /** injection instance of EntityServiceBean */
    @Autowired
    private IEntityService entityService;
    /** Logger */
    private static Logger logger = Logger.getLogger(ProjectController.class.getName());

    /**
     * creates random instance of project
     * 
     * @return tiles or url for redirecting
     */
    @RequestMapping("/create")
    public String createProject()
    {
        Project project = new Project();
        try
        {
            entityService.createProject(project);
        }
        catch (ServiceException e)
        {
            logger.error(e);
        }
        return "redirect:/projects";
    }

    /**
     * updates project by id
     * 
     * @param id - project id
     * @param employeeID - employee id
     * @return tiles or url for redirecting
     */
    @RequestMapping("/update/{id}")
    public String updateProject(@PathVariable("id") long id, @RequestParam(value = "employeeID", required = false) Long employeeID)
    {
        if (employeeID != null)
        {
            try
            {
                Project project = entityService.findProject(id);
                if (project != null)
                {
                    Employee employee = entityService.findEmployee(employeeID);
                    project.addEmployee(employee);
                    entityService.updateProject(project);
                }
            }
            catch (ServiceException e)
            {
                logger.error(e);
            }
        }
        return "redirect:/projects";
    }

    /**
     * remove project by id
     * 
     * @param id - project id
     * @return tiles or url for redirecting
     */
    @RequestMapping("/remove/{id}")
    public String deleteProject(@PathVariable("id") long id)
    {
        try
        {
            entityService.removeProject(id);
        }
        catch (ServiceException e)
        {
            logger.error(e);
        }
        return "redirect:/projects";
    }

    /**
     * find all project
     * 
     * @param model - instance of model
     * @return tiles or url for redirecting
     */
    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model)
    {
        try
        {
            model.addAttribute("projects", entityService.findAllProjects());
        }
        catch (ServiceException e)
        {
            logger.error(e);
        }
        return "projects";
    }
}
