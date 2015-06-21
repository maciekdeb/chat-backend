package chat.jms;

import chat.model.ChatMessage;
import chat.model.Contact;
import chat.repository.ChatMessageRepository;
import chat.repository.ContactRepository;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledReceiver {

    private final String DESTINATION = "internet-chat";
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Autowired
    private ConfigurableApplicationContext context;

    @Resource
    private ChatMessageRepository chatMessageRepository;

    @Resource
    private ContactRepository contactRepository;

    @Scheduled(fixedRate = 5000)
    public void receiveAndSaveMessage() {
        if (login != null && !login.isEmpty()) {
            System.out.println("Scheduled task fired... for " + login);
            JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
            jmsTemplate.setReceiveTimeout(5000);
            Message message = jmsTemplate.receiveSelected(DESTINATION, String.format("to='%s'", login));

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setIsIncoming(true);
            chatMessage.setIsRead(false);
            try {
                chatMessage.setDate(new Date(message.getJMSTimestamp()));
//                Contact contact = contactRepository.findByLogin(message.getStringProperty("from"));
//                if (contact != null) {
//                    chatMessage.setContact(contact.get(0));
//                }
                chatMessage.setContent(((ActiveMQTextMessage) message).getText());
                chatMessageRepository.save(chatMessage);
            } catch (JMSException e) {
                System.out.println("Exception " + e);
            }
            System.out.println("Message received.");
        } else {
            System.out.println("Scheduled task fired...");
        }
    }
}
