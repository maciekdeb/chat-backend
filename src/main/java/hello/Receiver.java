package hello;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

//@Component(value = "receiverJmsBean")
public class Receiver {

    @Autowired
    ConfigurableApplicationContext context;

//    @JmsListener(destination = "internet-chat", selector = "to='Maciek'")
    public void receiveMessage(Message message) {
        System.out.println("Received : " + message);
        context.close();
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
    }
}
