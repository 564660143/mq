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
     * 第一步：建立ConnectionFactory工厂对象，需要填入用户名、密码、以及要连接的地址
     * @param 默认用户名
     * @param 默认密码
     * @param 默认地址 tcp://localhost:61616
     */
    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
        ActiveMQConnectionFactory.DEFAULT_USER,
        ActiveMQConnectionFactory.DEFAULT_PASSWORD,
        "tcp://localhost:61616");
    
    /**
     *  第二步：通过ConnectionFactory获取一个Connection连接，
     *  并且调用connection的start方法开启连接，connection默认是关闭的
     */
    Connection connection = connectionFactory.createConnection();
     connection.start();
    
    /**
     *  第三步：通过Connection对象创建Session(上下文环境对象)会话，用于接收消息
     *  参数配置1为是否启用事务，参数配置2是签收模式，一般我们设置为自动签收 
     */
     Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
     
     /**
      * 第四步：通过session创建Destination对象
      * 指的是一个客户端用来指定生产消息目标和消费消息来源的对象
      * 在PTP模式中，Destination被称作Queue即队列
      */
     Destination destination = session.createQueue("first");
     
     /**
      * 第五步：我们需要通过Session对象创建消息的发送和接收对象（生产者和消费者)，MessageProducer和MessageConsumer
      */
     MessageProducer producer = session.createProducer(null);
     
     /**
      * 第六步：可以使用MessageProducer的setDeliveryMode方法为其设置持久化与非持久化特性（DeliveryMode）
      */
//     producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
     
     /**
      * 第七步：使用JMS规范的TextMessage对象创建数据（通过Session对象），
      * 并用MessageProducer的send方法发送数据，
      * 同理客户端使用receive方法接收数据
      */
     for (int i = 0; i < 100; i++) {
       TextMessage textMessage = session.createTextMessage("测试数据：" + i);
       /**
        * 第一个参数：目标地址
        * 第二个参数：具体的数据信息
        * 第三个参数：传送数据的模式
        * 第四个参数：优先级
        * 第五个参数：消息的过期时间
        */
       producer.send(destination, textMessage);
       TimeUnit.SECONDS.sleep(1);
    }
     
     /**
      * 最后需要手动关闭连接
      */
     if (connection != null) {
      connection.close();
    }

  }

}
