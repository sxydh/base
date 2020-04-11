
package cn.net.bhe.basics.jms.ptp;

import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.jms.QueueSender;

import java.util.Hashtable;

import javax.jms.DeliveryMode;
import javax.jms.QueueSession;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;

public class Sender {
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
        QueueSession queSession = queConn.createQueueSession(false, Session.DUPS_OK_ACKNOWLEDGE);

        // create a queue sender
        QueueSender queSender = queSession.createSender(queue);
        queSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        // create a simple message to say "Hello World"
        TextMessage message = queSession.createTextMessage("Hello World");

        // send the message
        int i = 0;
        while (i++ < 10) {
            Thread.sleep(2000);
            queSender.send(message);
            // print what we did
            System.out.println("Message Sent: " + message.getText());
            message.setText(Math.random() + "");
        }

        // close the queue connection
        queConn.close();
    }
}
