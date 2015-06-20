package chat.rest;

import chat.jms.ChatMessageCreator;
import chat.model.Contact;
import chat.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.*;

/**
 * Created by maciej.debowski on 2015-04-30.
 */
@RestController
public class RestEndpoint {

    private final String DESTINATION = "internet-chat";

//    @Autowired
//    Receiver receiver;

//    @Autowired
//    SyncReceiver syncReceiver;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ConfigurableApplicationContext context;

    @RequestMapping("/saveContact/{login}")
    public ResponseEntity saveContact(@PathVariable("login") String login) {
        contactRepository.save(new Contact(login));
        System.out.println(contactRepository.findByLogin(login));

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @RequestMapping("/receive/{me}")
    public @ResponseBody String receive(@PathVariable("me") String to) {
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        to = String.format("to='%s'", to);
        return String.valueOf(jmsTemplate.receiveSelectedAndConvert(DESTINATION, to));
    }

    @RequestMapping(value = "/send/{me}/{to}", method = RequestMethod.POST)
    public ResponseEntity send(@PathVariable("me") String me, @PathVariable("to") String to, @RequestBody String message) {
        MessageCreator messageCreator = new ChatMessageCreator(me, to, message);
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        jmsTemplate.send(DESTINATION, messageCreator);
        System.out.println("Sending a new message.");
        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
