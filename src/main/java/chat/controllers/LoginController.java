package chat.controllers;

import chat.jms.ChatMessageCreator;
import chat.model.ChatMessage;
import chat.model.Contact;
import chat.repository.ContactRepository;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.Date;

/**
 * Created by maciej.debowski on 2015-04-30.
 */
@Controller
public class LoginController {

    @Autowired
    ConfigurableApplicationContext context;

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

}
