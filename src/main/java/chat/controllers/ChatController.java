package chat.controllers;

import chat.repository.ContactRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by rozar_000 on 2015-06-21.
 */
@Controller
public class ChatController {

    @Resource
    private ContactRepository contactRepository;

    protected ContactRepository getContactRepository(){
        return this.contactRepository;
    }

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String getChat(final Model model){
        model.addAttribute("contacts", getContactRepository().findAll());
        
        return "chat";
    }



}
