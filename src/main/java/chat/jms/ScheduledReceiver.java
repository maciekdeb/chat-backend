package chat.jms;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.Lifecycle;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.net.www.http.HttpClient;

import javax.management.Notification;

//@Component
public class ScheduledReceiver {

    @Autowired
    ConfigurableApplicationContext context;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        DefaultMessageListenerContainer a = (DefaultMessageListenerContainer) context.getBean("messageListenerContainer");
        System.out.println(a.getMessageListener());
    }
}