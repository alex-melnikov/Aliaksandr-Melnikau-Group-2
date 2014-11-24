package com.epam.dao.interfaces;

import com.epam.bean.employee.Unit;

/**
 * Unit service local interface
 */
public interface IUnitRemote
{
    /**
     * Create new unit
     * @param unit
     * @return isCreated
     */
    public boolean createUnit(Unit unit);

    /**
     * update unit
     * @return isUpdated
     */
    public boolean updateUnit(Unit unit);

    /**
     * update unit
     * @param id
     * @return isDeleted
     */
    public boolean deleteUnit(int id);

    /**
     * find unit
     * @param id
     * @return unit
     */
    public Unit findUnit(int id);

}
