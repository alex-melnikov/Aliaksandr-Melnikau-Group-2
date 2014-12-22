package com.epam.springmvc.dao.impl;

import org.springframework.stereotype.Repository;

import com.epam.springmvc.dao.IProjectDAO;
import com.epam.springmvc.model.Project;

/**
 * IprojectDAO implementation
 */
@Repository("projectDAO")
public class ProjectDAO extends AbstractDAO<Project> implements IProjectDAO
{
    /**
     * constructor
     */
    public ProjectDAO()
    {
        super(Project.class);
    }
}
