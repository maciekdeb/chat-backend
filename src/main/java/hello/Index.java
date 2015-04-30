package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.config.AbstractJmsListenerEndpoint;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.websocket.server.PathParam;

/**
 * Created by maciej.debowski on 2015-04-30.
 */
@Controller
public class Index {

    @Autowired
    Receiver receiver;

    @Autowired
    ConfigurableApplicationContext context;

    @RequestMapping("/")
    public
    @ResponseBody
    String aaa() {
        return "aaa" + receiver;
    }

    @RequestMapping("/send/{to}/{message}")
    public void send(@PathVariable("to") final String to, @PathVariable("message") final String message) {
        MessageCreator messageCreator = new ChatMessageCreator("Artur", to, message);
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        System.out.println("Sending a new message.");
        jmsTemplate.send("internet-chat", messageCreator);
    }

}
