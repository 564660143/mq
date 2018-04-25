package activemq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
  private ConnectionFactory connectionFactory;
  private Connection connection;
  private Session session;
  private MessageProducer messageProducer;


  public Producer() {
    try {
      this.connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
          ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://localhost:61616");
      this.connection = connectionFactory.createConnection();
      connection.start();
      this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      this.messageProducer = session.createProducer(null);
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

  public Session getSession() {
    return session;
  }

  public void send() throws JMSException{
    Destination destination = this.session.createTopic("topic1");
    MapMessage mapMessage1 = this.session.createMapMessage();
    mapMessage1.setString("name", "±ÌÂä");
    mapMessage1.setStringProperty("age", "30");
    mapMessage1.setString("sex", "ÄÐ");
    MapMessage mapMessage2 = this.session.createMapMessage();
    mapMessage2.setString("name", "»ÆÈª");
    mapMessage2.setStringProperty("age", "25");
    mapMessage2.setString("sex", "ÄÐ");
    MapMessage mapMessage3 = this.session.createMapMessage();
    mapMessage3.setString("name", "ºì³¾");
    mapMessage3.setStringProperty("age", "30");
    mapMessage3.setString("sex", "Å®");
    MapMessage mapMessage4 = this.session.createMapMessage();
    mapMessage4.setString("name", "×ÏÄ°");
    mapMessage4.setStringProperty("age", "28");
    mapMessage4.setString("sex", "ÄÐ");
    this.messageProducer.send(destination, mapMessage1, DeliveryMode.NON_PERSISTENT, 1, 1000 * 60 * 10);
    this.messageProducer.send(destination, mapMessage2, DeliveryMode.NON_PERSISTENT, 1, 1000 * 60 * 10);
    this.messageProducer.send(destination, mapMessage3, DeliveryMode.NON_PERSISTENT, 1, 1000 * 60 * 10);
    this.messageProducer.send(destination, mapMessage4, DeliveryMode.NON_PERSISTENT, 1, 1000 * 60 * 10);
  }

  public void stop() {
    if (this.connection != null) {
      try {
        this.connection.close();
      } catch (JMSException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) throws JMSException, InterruptedException {
    Producer producer = new Producer();
    producer.send();
    producer.stop();
  }


}
