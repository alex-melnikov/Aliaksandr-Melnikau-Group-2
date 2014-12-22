package com.epam.springmvc.dao;

import java.util.Set;

/**
 * common interface for any data source class
 * @param <E> - class type being manipulated
 */
public interface IEntityDAO<E>
{
    /**
     * store new object
     * 
     * @param obj - instance of class
     */
    void create(E obj);

    /**
     * update persisted object
     * 
     * @param obj - instance of class
     */
    void update(E obj);

    /**
     * find persisted object by ID
     * 
     * @param id - ID
     * 
     * @return instance of object
     */
    E find(Long id);

    /**
     * find All persisted objects
     * 
     * @return Set of persistent objects
     */
    Set<E> findAll();

    /**
     * delete stored object
     * 
     * @param id - ID
     */
    void delete(Long id);
}
