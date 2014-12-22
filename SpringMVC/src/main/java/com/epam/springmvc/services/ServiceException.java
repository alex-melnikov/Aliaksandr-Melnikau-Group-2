package com.epam.springmvc.services;

/**
 * entity service common exception class
 */
public class ServiceException extends Exception
{
    /**
     * constructor
     */
    public ServiceException()
    {

    }

    /**
     * constructor
     * 
     * @param message - exception message
     */
    public ServiceException(String message)
    {
        super(message);
    }

    /**
     * constructor
     * 
     * @param message - exception message
     * @param cause - root exception
     */
    public ServiceException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
