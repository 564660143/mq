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

@RunWith(SpringJUnit4ClassRunner.class) //ʹ��junit4���в���  
@ContextConfiguration("classpath:mq/spring-ActiveMQ.xml") //���������ļ�      
public class TestSpringActiveMQ {
  
  @Resource(name = "demoQueueDestination")
  private Destination destination;
  
  //������Ϣ������
  @Resource(name = "producerService")
  private ProducerService producer;

  //������Ϣ������
  @Resource(name = "consumerService")
  private ConsumerService consumer;

  @Test
  public void test() {
    System.out.println("����");
  }

  @Test
  public void testSendMessage() {
    producer.sendMessage("���Է�����Ϣ��" + new Date());
  }
  
  @Test
  public void testReceiveMessage() {
    TextMessage tm = consumer.receive(destination);
    System.out.println("���յ���Ϣ��" + tm);
  }
  
  public static void main(String[] args) {
    BeanFactory bf = new ClassPathXmlApplicationContext("classpath:mq/spring-ActiveMQ.xml");
    ConsumerService consumerService = (ConsumerService)bf.getBean("consumerService");
    System.out.println(consumerService);
    
    
  }

}
