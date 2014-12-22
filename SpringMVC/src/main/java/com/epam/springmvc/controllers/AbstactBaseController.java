package com.epam.springmvc.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * base class for all web controllers
 */
public abstract class AbstactBaseController
{
    /**
     * handle any exception
     * 
     * @param throwable - exception
     * @return model-view instance
     */
    @ExceptionHandler(Throwable.class)
    public ModelAndView handleException(Throwable throwable)
    {
        ModelAndView model = new ModelAndView();
        model.addObject("exception", throwable);
        model.setViewName("error");
        return model;
    }
}
