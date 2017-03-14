package ru.inno.chat;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Ирина on 14.03.2017.
 */
public class Producer implements Runnable {
    public void run() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost");
        try {
            Connection myConnection = factory.createConnection();
            myConnection.start();
            Session session = myConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("Dest");
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            TextMessage textMessage = session.createTextMessage("Hello world!");
            producer.send(textMessage);
            session.close();
            myConnection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
