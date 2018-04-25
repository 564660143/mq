package activemq.spring.queue;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service("consumerService")
public class ConsumerService {
    private JmsTemplate jmsTemplate;

    public TextMessage receive(Destination destination){
        TextMessage textMessage = (TextMessage) jmsTemplate.receive(destination);
        try{
            System.out.println("�Ӷ���" + destination.toString() + "�յ�����Ϣ��\t"
                    + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return textMessage;
    }

    public JmsTemplate getJmsTemplate() {
      return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
      this.jmsTemplate = jmsTemplate;
    }
    
    
}