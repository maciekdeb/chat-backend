package chat.repository;

import chat.model.ChatMessage;
import chat.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by maciej.debowski on 2015-06-20.
 */

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByContact(Contact contact);
    List<ChatMessage> findByIsIncomingTrueAndIsReadFalseAndContact(Contact contact);

}
