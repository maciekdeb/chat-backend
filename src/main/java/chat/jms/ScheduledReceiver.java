package chat.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.scheduling.annotation.Scheduled;

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