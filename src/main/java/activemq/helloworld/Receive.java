package activemq.helloworld;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;


public class Receive {
  
  public static void main(String[] args) throws JMSException, InterruptedException {
    /**
     * ��һ��������ConnectionFactory����������Ҫ�����û��������롢�Լ�Ҫ���ӵĵ�ַ
     * @param Ĭ���û���
     * @param Ĭ������
     * @param Ĭ�ϵ�ַ tcp://localhost:61616
     */
    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
        ActiveMQConnectionFactory.DEFAULT_USER,
        ActiveMQConnectionFactory.DEFAULT_PASSWORD,
        "tcp://localhost:61616");
    
    /**
     *  �ڶ�����ͨ��ConnectionFactory��ȡһ��Connection���ӣ�
     *  ���ҵ���connection��start�����������ӣ�connectionĬ���ǹرյ�
     */
    Connection connection = connectionFactory.createConnection();
    connection.start();
    
    /**
     *  ��������ͨ��Connection���󴴽�Session(�����Ļ�������)�Ự�����ڽ�����Ϣ
     *  ��������1Ϊ�Ƿ��������񣬲�������2��ǩ��ģʽ��һ����������Ϊ�Զ�ǩ�� 
     */
    Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
    
    /**
     * ���Ĳ���ͨ��session����Destination����
     * ָ����һ���ͻ�������ָ��������ϢĿ���������Ϣ��Դ�Ķ���
     * ��PTPģʽ�У�Destination������Queue������
     */
    Destination destination = session.createQueue("first");
    
    /**
     * ���岽��ͨ��Session����MessageConsumer
     */
    MessageConsumer messageConsumer = session.createConsumer(destination);
    
    while(true){
      TextMessage text = (TextMessage)messageConsumer.receive();
      System.out.println("�������ݣ�" + text.getText());
      TimeUnit.SECONDS.sleep(1);
    }
    
  }
  
  
  

}
