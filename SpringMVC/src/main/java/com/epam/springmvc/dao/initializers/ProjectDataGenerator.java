package com.epam.springmvc.dao.initializers;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import com.epam.springmvc.model.Project;

/**
 * project Data generator class
 */
@Component
public class ProjectDataGenerator implements IDataGenerator<Project>
{
    /** hibernate session factory */
    @Inject
    private SessionFactory sessionFactory;

    /** persistence data to be saved */
    @Resource
    private Map<Class, Object> initMap;

    /**
     * saves persist data in DB
     */
    @PostConstruct
    public void storeData()
    {
        if (initMap.containsKey(Project.class))
        {
            Session session = sessionFactory.getCurrentSession();
            try
            {
                Transaction transaction = session.beginTransaction();
                session.persist(initMap.get(Project.class));
                transaction.commit();
            }
            catch (Exception e)
            {
                session.close();
            }
        }

    }
}
