package activemq.listener;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

class Listener implements MessageListener{

  public void onMessage(Message message) {
    try {
      if (message instanceof MapMessage) {
        MapMessage ret = (MapMessage) message;
        System.out.println(ret.toString());
        System.out.println(ret.getString("name"));
        System.out.println(ret.getStringProperty("age"));
        System.out.println(ret.getString("sex"));
      } else {
        System.out.println("其他消息：" + message.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}



public class Consumer {
  
  private ConnectionFactory connectionFactory;
  private Connection connection;
  private Session session;
  private Destination destination;
  private MessageConsumer messageConsumer;
  private String selector_0 = "age='30'";
  private String selector_1 = "name ='黄泉'";

  public Consumer() {
    try {
      this.connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
          ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://localhost:61616");
      this.connection = connectionFactory.createConnection();
      connection.start();
      this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      this.destination = this.session.createQueue("first");
      // selector选择器，可以按照SQL格式进行消息筛选
      this.messageConsumer = session.createConsumer(destination, selector_0);
      this.messageConsumer = session.createConsumer(destination, selector_1);
      this.messageConsumer = session.createConsumer(destination);
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }
  
  public void receive(){
    try {
      this.messageConsumer.setMessageListener(new Listener());
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) throws JMSException, InterruptedException {
    Consumer consumer = new Consumer();
    consumer.receive();
  }
}
