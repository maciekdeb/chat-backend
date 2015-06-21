package chat.rest;

import chat.jms.ChatMessageCreator;
import chat.model.ChatMessage;
import chat.model.Contact;
import chat.repository.ChatMessageRepository;
import chat.repository.ContactRepository;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.*;

/**
 * Created by maciej.debowski on 2015-04-30.
 */
@RestController
public class RestEndpoint {

    private final String DESTINATION = "internet-chat";

    @Resource
    ContactRepository contactRepository;

    @Autowired
    ConfigurableApplicationContext context;

    @Resource
    ChatMessageRepository chatMessageRepository;

    public ConfigurableApplicationContext getContext() {
        return context;
    }

    public ContactRepository getContactRepository() {
        return contactRepository;
    }

    public ChatMessageRepository getChatMessageRepository() {
        return chatMessageRepository;
    }

    @RequestMapping("/saveContact/{login}")
    public ResponseEntity saveContact(@PathVariable("login") String login) {
        if (contactRepository != null) {
            List<Contact> contacts = contactRepository.findByLogin(login);
            if (contacts != null && !contacts.isEmpty()){
                return new ResponseEntity<String>(HttpStatus.CONFLICT);
            }else{
                contactRepository.save(new Contact(login));
                return new ResponseEntity<String>(HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<String>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @RequestMapping("/getContacts")
    public Map<String, Integer> getContacts() {

        return getContactListMap();
    }

    @RequestMapping("/receive/{me}")
    public ChatMessage receive(@PathVariable("me") String to) {
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        Message message = jmsTemplate.receiveSelected(DESTINATION, String.format("to='%s'", to));

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setIsIncoming(true);
        chatMessage.setIsRead(false);
        try {
            chatMessage.setDate(new Date(message.getJMSTimestamp()));
            chatMessage.setContact(new Contact(message.getStringProperty("from")));
            chatMessage.setContent(((ActiveMQTextMessage) message).getText());
        } catch (JMSException e) {
            return null;
        }
        return chatMessage;
    }

    @RequestMapping(value = "/send/{me}/{to}", method = RequestMethod.POST)
    public ResponseEntity send(@PathVariable("me") String me, @PathVariable("to") String to, @RequestBody String message) {
        MessageCreator messageCreator = new ChatMessageCreator(me, to, message);
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        jmsTemplate.send(DESTINATION, messageCreator);
        System.out.println("Sending a new message.");
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    private Map<String, Integer> getContactListMap(){
        Map<String, Integer> contactListMap = new HashMap<String, Integer>();

        List<Contact> contacts = getContactRepository().findAll();
        for(Contact contact : contacts){

            if(contact != null){
                List<ChatMessage> chatMessages = getChatMessageRepository().findByIsIncomingTrueAndIsReadFalseAndContact(contact);
                if(chatMessages != null) {
                    contactListMap.put(contact.getLogin(), chatMessages.size());
                }
            }
        }
        return contactListMap;
    }

}
