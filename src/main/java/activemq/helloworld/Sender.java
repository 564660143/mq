package activemq.helloworld;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {

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
      * ���岽��������Ҫͨ��Session���󴴽���Ϣ�ķ��ͺͽ��ն��������ߺ�������)��MessageProducer��MessageConsumer
      */
     MessageProducer producer = session.createProducer(null);
     
     /**
      * ������������ʹ��MessageProducer��setDeliveryMode����Ϊ�����ó־û���ǳ־û����ԣ�DeliveryMode��
      */
//     producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
     
     /**
      * ���߲���ʹ��JMS�淶��TextMessage���󴴽����ݣ�ͨ��Session���󣩣�
      * ����MessageProducer��send�����������ݣ�
      * ͬ��ͻ���ʹ��receive������������
      */
     for (int i = 0; i < 100; i++) {
       TextMessage textMessage = session.createTextMessage("�������ݣ�" + i);
       /**
        * ��һ��������Ŀ���ַ
        * �ڶ��������������������Ϣ
        * �������������������ݵ�ģʽ
        * ���ĸ����������ȼ�
        * �������������Ϣ�Ĺ���ʱ��
        */
       producer.send(destination, textMessage);
       TimeUnit.SECONDS.sleep(1);
    }
     
     /**
      * �����Ҫ�ֶ��ر�����
      */
     if (connection != null) {
      connection.close();
    }

  }

}
