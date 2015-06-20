package chat.model;

import chat.model.ChatMessage;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maciej.debowski on 2015-06-20.
 */

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String login;

    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ChatMessage> messages = new ArrayList<>();

    protected Contact() {
    }

    public Contact(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return String.format("Contact[id=%d, login='%s']", id, login);
    }

}