package chat.controllers;

import chat.model.ChatMessage;
import chat.model.Contact;
import chat.repository.ChatMessageRepository;
import chat.repository.ContactRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rozar_000 on 2015-06-21.
 */
@Controller
public class ChatController {

    @Resource
    private ContactRepository contactRepository;

    @Resource
    private ChatMessageRepository chatMessageRepository;

    protected ContactRepository getContactRepository(){
        return this.contactRepository;
    }

    protected ChatMessageRepository getChatMessageRepository(){
        return this.chatMessageRepository;
    }

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String getChat(final Model model){
        model.addAttribute("contacts", getContactRepository().findAll());
        model.addAttribute("contactListMap", getContactListMap());

        return "chat";
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
