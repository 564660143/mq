package activemq.spring.listener;

import java.util.Date;

import javax.jms.Message;
import javax.jms.MessageListener;

public class QueueMessageListener implements MessageListener {

  public void onMessage(Message message) {
    System.out.println(new Date() +  "收到消息：" + message);
  }

}
