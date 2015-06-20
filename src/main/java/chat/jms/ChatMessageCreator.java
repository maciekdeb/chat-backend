package chat.jms;

import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by maciej.debowski on 2015-04-30.
 */
public class ChatMessageCreator implements MessageCreator {

    private String from;
    private String to;
    private String messageText;

    private final static String FROM = "from";
    private final static String TO = "to";

    public ChatMessageCreator(String from, String to, String messageText) {
        this.from = from;
        this.to = to;
        this.messageText = messageText;
    }

    @Override
    public Message createMessage(Session session) throws JMSException {
        Message message = session.createTextMessage(messageText);
        message.setStringProperty(FROM, from);
        message.setStringProperty(TO, to);
        return message;
    }
}
