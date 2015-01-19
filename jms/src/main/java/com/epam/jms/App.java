package com.epam.jms;

import com.epam.jms.producer.SimpleMessageProducer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.JMSException;

/**
 * Runner class
 */
public class App
{
    public static void main(String[] args) throws JMSException
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/producer-jms-context.xml", App.class);
        SimpleMessageProducer producer = (SimpleMessageProducer) context.getBean("messageProducer");
        producer.sendMessages();
    }
}
