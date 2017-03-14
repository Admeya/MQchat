package ru.inno.chat;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Ирина on 14.03.2017.
 */
public class Consumer implements Runnable{

    public void run() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost");
        try {
            Connection myConnection = factory.createConnection();
            myConnection.start();
            Session session = myConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("Dest");

            MessageConsumer messageConsumer = session.createConsumer(destination);
            Message message = messageConsumer.receive(10000);
            System.out.println(((TextMessage) message).getText());
            session.close();
            myConnection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
