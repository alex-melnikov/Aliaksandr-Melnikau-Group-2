package com.epam.springmvc.dao.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.epam.springmvc.dao.IEntityDAO;

/**
 * Abstract class for all data source classes
 * @param <E> - class type being manipulated
 */
public abstract class AbstractDAO<E> implements IEntityDAO<E>
{
    /** hibernate persistent manager */
    @Inject
    private SessionFactory sessionFactory;

    /** class type being manipulated */
    private Class<E> clazz;

    /**
     * constructor
     * 
     * @param clazz - class type being manipulated
     */
    protected AbstractDAO(Class<E> clazz)
    {
        this.clazz = clazz;
    }

    /**
     * {@inheritDoc}
     */
    public void create(E obj)
    {
        Session session = sessionFactory.getCurrentSession();
        try
        {
            Transaction transaction = session.beginTransaction();
            session.persist(obj);
            transaction.commit();
        }
        catch (Exception e)
        {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(E obj)
    {
        Session session = sessionFactory.getCurrentSession();
        try
        {
            Transaction transaction = session.beginTransaction();
            session.merge(obj);
            transaction.commit();
        }
        catch (Exception e)
        {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public E find(Long id)
    {
        Session session = sessionFactory.getCurrentSession();
        E result = null;
        try
        {
            Transaction transaction = session.beginTransaction();
            result = (E) session.get(clazz, id);
            transaction.commit();
        }
        catch (Exception e)
        {
            session.close();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public Set<E> findAll()
    {
        Session session = sessionFactory.getCurrentSession();
        Set<E> result = null;
        try
        {
            Transaction transaction = session.beginTransaction();
            result = new LinkedHashSet<E>(session.createCriteria(clazz).list());
            transaction.commit();
        }
        catch (Exception e)
        {
            session.close();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Long id)
    {
        Session session = sessionFactory.getCurrentSession();
        try
        {
            Transaction transaction = session.beginTransaction();
            session.delete(session.load(clazz, id));
            transaction.commit();
        }
        catch (Exception e)
        {
            session.close();
        }
    }
}
