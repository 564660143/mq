package activemq.spring.test;


import java.util.Date;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.TextMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import activemq.spring.queue.ConsumerService;
import activemq.spring.queue.ProducerService; 

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试  
@ContextConfiguration("classpath:mq/spring-ActiveMQ.xml") //加载配置文件      
public class TestSpringActiveMQ {
  
  @Resource(name = "demoQueueDestination")
  private Destination destination;
  
  //队列消息生产者
  @Resource(name = "producerService")
  private ProducerService producer;

  //队列消息消费者
  @Resource(name = "consumerService")
  private ConsumerService consumer;

  @Test
  public void test() {
    System.out.println("测试");
  }

  @Test
  public void testSendMessage() {
    producer.sendMessage("测试发送消息：" + new Date());
  }
  
  @Test
  public void testReceiveMessage() {
    TextMessage tm = consumer.receive(destination);
    System.out.println("接收到消息：" + tm);
  }
  
  public static void main(String[] args) {
    BeanFactory bf = new ClassPathXmlApplicationContext("classpath:mq/spring-ActiveMQ.xml");
    ConsumerService consumerService = (ConsumerService)bf.getBean("consumerService");
    System.out.println(consumerService);
    
    
  }

}
