package hello;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by maciej.debowski on 2015-06-20.
 */

public interface ChatMessageRepository extends CrudRepository<Contact, Long> {

//    List<Contact> findByContact(Contact contact);

}
