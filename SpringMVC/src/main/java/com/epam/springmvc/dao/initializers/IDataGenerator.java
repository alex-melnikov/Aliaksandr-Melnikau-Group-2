package com.epam.springmvc.dao.initializers;

/**
 * common interface of all Data Generator classes
 * @param <E> - persistence class
 */
public interface IDataGenerator<E>
{
    /**
     * saves persistent data
     */
    void storeData();
}
