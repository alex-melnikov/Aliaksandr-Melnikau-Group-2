package com.epam.springmvc.dao.initializers;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Data generator factory class
 */
@Component
public final class DataGeneratorFactory
{
    /**
     * creates instance of ProjectDataGenerator
     * 
     * @return instance of ProjectDataGenerator
     */
    @Bean
    public static ProjectDataGenerator createProjectDataGenerator()
    {
        return new ProjectDataGenerator();
    }
}
