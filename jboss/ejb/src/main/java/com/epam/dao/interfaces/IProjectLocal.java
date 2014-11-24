package com.epam.dao.interfaces;

import com.epam.bean.employee.Project;

/**
 * Project service local interface
 */
public interface IProjectLocal
{
    /**
     * Create new project
     * @param project
     * @return isCreated
     */
    public boolean createProject(Project project);

    /**
     * update project
     * @return isUpdated
     */
    public boolean updateProject(Project project);

    /**
     * update project
     * @param id
     * @return isDeleted
     */
    public boolean deleteProject(int id);

    /**
     * find project
     * @param id
     * @return project
     */
    public Project findProject(int id);

}
