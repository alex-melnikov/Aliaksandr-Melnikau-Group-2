package com.epam.springmvc.dao.initializers;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.epam.springmvc.model.Employee;

/**
 * employee Data generator class
 */
@Component
public class EmployeeDataGenerator implements IDataGenerator<Employee>
{
    /** hibernate session factory */
    @Inject
    private SessionFactory sessionFactory;

    /** some value from property */
    private String valueFromProp;

    /** persistence data to be saved */
    @Resource
    private Map<Class, Object> initMap;

    /**
     * saves persist data in DB
     */
    @PostConstruct
    public void storeData()
    {
        if (initMap.containsKey(Employee.class))
        {
            Session session = sessionFactory.getCurrentSession();
            try
            {
                Transaction transaction = session.beginTransaction();
                session.persist(initMap.get(Employee.class));
                transaction.commit();
            }
            catch (Exception e)
            {
                session.close();
            }
        }
    }

    /**
     * sets vale
     * 
     * @param key - value
     */
    @Value("${prop.value}")
    public void setKeyFromProp(String key)
    {
        this.valueFromProp = key;
    }
}
