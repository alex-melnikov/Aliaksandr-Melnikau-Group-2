package com.epam.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.epam.bean.employee.Unit;
import com.epam.dao.interfaces.IUnitLocal;
import com.epam.dao.interfaces.IUnitRemote;

/**
 * Unit service implementation
 */
@Stateless(name = "TestProxy")
public class UnitLocalImpl implements IUnitLocal, IUnitRemote
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
    public boolean createUnit(Unit unit)
    {
        if (unit != null)
        {
            em.getTransaction().begin();
            em.persist(unit);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUnit(Unit unit)
    {
        if (unit != null)
        {
            em.getTransaction().begin();
            em.merge(unit);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUnit(int id)
    {
        if (id >= 0)
        {
            Unit unit = em.find(Unit.class, id);
            if (unit != null)
            {
                em.getTransaction().begin();
                em.remove(unit);
                em.getTransaction().commit();
                return true;
            }
        }
        return false;
    }

    @Override
    public Unit findUnit(int id)
    {
        Unit unit = null;
        if (id >= 0)
        {
            unit = em.find(Unit.class, id);
        }
        return unit;
    }

}
