package cn.net.bhe.basics.jms.ptp;

import javax.naming.Context;
import javax.naming.InitialContext;

import java.util.Hashtable;

import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.QueueSession;
import javax.jms.QueueReceiver;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;

public class Receiver {
    public static void main(String[] args) throws Exception {
        Hashtable<String, String> ht = new Hashtable<>();
        ht.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        ht.put(Context.PROVIDER_URL, "t3://localhost:7001");
        // get the initial context
        InitialContext context = new InitialContext(ht);

        // lookup the queue object
        Queue queue = (Queue) context.lookup("jms/jms_test_queue");

        // lookup the queue connection factory
        QueueConnectionFactory conFactory = (QueueConnectionFactory) context.lookup("jms/jms_test_connection_factory1");

        // create a queue connection
        QueueConnection queConn = conFactory.createQueueConnection();

        // create a queue session
        QueueSession queSession = queConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

        // create a queue receiver
        QueueReceiver queReceiver = queSession.createReceiver(queue);

        // start the connection
        queConn.start();

        long last = System.currentTimeMillis();
        long cur = last;
        while ((cur = System.currentTimeMillis()) - last < 30 * 60 * 1000) {
            // receive a message
            TextMessage message = (TextMessage) queReceiver.receive();
            // print the message
            System.out.println("Message Received: " + message.getText());
            last = cur;
        }

        // close the queue connection
        queConn.close();
    }
}