package com.epam.jms.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Message producer class
 * 
 */
public class SimpleMessageProducer
{
    /** Logger */
    private static final Logger LOG = LoggerFactory.getLogger(SimpleMessageProducer.class);

    /**
     * jms template
     */
    protected JmsTemplate jmsTemplate;

    /**
     * number of messages
     */
    protected int numberOfMessages = 100;

    /**
     * set number of messages
     * @param numberOfMessages
     */
    public void setNumberOfMessages(int numberOfMessages)
    {
        this.numberOfMessages = numberOfMessages;
    }

    /**
     * get template
     * @return jms template
     */
    public JmsTemplate getJmsTemplate()
    {
        return jmsTemplate;
    }

    /**
     * set jms template
     * @param jmsTemplate
     */
    public void setJmsTemplate(JmsTemplate jmsTemplate)
    {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * send message
     * @throws JMSException
     */
    public void sendMessages() throws JMSException
    {
        final StringBuilder buffer = new StringBuilder("hello message");
        jmsTemplate.send(new MessageCreator()
        {
            public Message createMessage(Session session) throws JMSException
            {
                TextMessage message = session.createTextMessage();
                message.setText(buffer.toString());
                return message;
            }
        });
    }
}
