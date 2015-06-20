
package hello;

import java.io.File;
import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.AbstractJmsListenerEndpoint;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.AbstractMessageListenerContainer;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.FileSystemUtils;

@SpringBootApplication
@EnableScheduling
@EnableJms
public class Application {

    private static final String JMS_BROKER_URL = "tcp://localhost:61616";

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(JMS_BROKER_URL);
    }

    public static void main(String[] args) {
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    }

}
