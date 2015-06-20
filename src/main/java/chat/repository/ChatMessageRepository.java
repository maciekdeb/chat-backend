package chat.repository;

import chat.model.Contact;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by maciej.debowski on 2015-06-20.
 */

public interface ChatMessageRepository extends CrudRepository<Contact, Long> {

//    List<Contact> findByContact(Contact contact);

}
