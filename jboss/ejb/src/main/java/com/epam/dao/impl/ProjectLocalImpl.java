package com.epam.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.epam.bean.employee.Project;
import com.epam.dao.interfaces.IProjectLocal;
import com.epam.dao.interfaces.IProjectRemote;

/**
 * Project service implementation
 */
@Stateless(name = "TestProxy")
public class ProjectLocalImpl implements IProjectLocal, IProjectRemote
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
    public boolean createProject(Project project)
    {
        if (project != null)
        {
            em.getTransaction().begin();
            em.persist(project);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProject(Project project)
    {
        if (project != null)
        {
            em.getTransaction().begin();
            em.merge(project);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProject(int id)
    {
        if (id >= 0)
        {
            Project project = em.find(Project.class, id);
            if (project != null)
            {
                em.getTransaction().begin();
                em.remove(project);
                em.getTransaction().commit();
                return true;
            }
        }
        return false;
    }

    @Override
    public Project findProject(int id)
    {
        Project project = null;
        if (id > 0)
        {
            project = em.find(Project.class, id);
        }
        return project;
    }

}
