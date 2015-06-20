package chat.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.management.Notification;

/**
 * Created by maciek on 17.05.15.
 */
@Component
public class SyncReceiver {

    @Autowired
    ConfigurableApplicationContext context;

    public Notification receive() {
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        return (Notification) jmsTemplate.receiveAndConvert("internet-chat");
    }

}