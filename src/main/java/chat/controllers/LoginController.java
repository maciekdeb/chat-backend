package chat.controllers;

import chat.jms.ChatMessageCreator;
import chat.jms.ScheduledReceiver;
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

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by maciej.debowski on 2015-04-30.
 */
@Controller
public class LoginController {

    @Autowired
    private ConfigurableApplicationContext context;

    @Resource
    private ScheduledReceiver scheduledReceiver;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String renderLogin() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String submitLogin(HttpServletResponse response, @RequestParam String login) {
        //store in cookie
        response.addCookie(new Cookie("LOGIN", login));

        //save login name for jms listener
        if (scheduledReceiver != null) {
            System.out.println("I am setting login inside the scheduled task...");
            scheduledReceiver.setLogin(login);
        }

        return "redirect:/chat";
    }

}
