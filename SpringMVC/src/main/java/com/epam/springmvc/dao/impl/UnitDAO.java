package com.epam.springmvc.dao.impl;

import org.springframework.stereotype.Repository;

import com.epam.springmvc.dao.IUnitDAO;
import com.epam.springmvc.model.Unit;

/**
 * IUnitDAO implementation
 */
@Repository("unitDAO")
public class UnitDAO extends AbstractDAO<Unit> implements IUnitDAO
{
    /**
     * constructor
     */
    public UnitDAO()
    {
        super(Unit.class);
    }
}