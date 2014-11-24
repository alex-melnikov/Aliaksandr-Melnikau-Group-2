package com.epam.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.apache.log4j.Logger;

/**
 * Message driven bean
 *
 */
@MessageDriven(activationConfig = {@ActivationConfigProperty(propertyName = "destibationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/HelloQ")})
public class MyMDB implements MessageListener
{

    /**
     * logger
     */
    private static Logger logger = Logger.getLogger(MyMDB.class.getName());

    /**
     * Method gets jms message
     */
    @Override
    public void onMessage(Message message)
    {
        TextMessage textMessage = (TextMessage) message;
        try
        {
            logger.debug("MyMDB received: " + textMessage.getText());
            System.out.println("MyMDB received: " + textMessage.getText());
        }
        catch (JMSException e)
        {
            logger.error(e);
            e.printStackTrace();
        }
    }

}
