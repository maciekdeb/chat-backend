package chat.repository;

import java.util.List;

import chat.model.Contact;
import org.springframework.data.repository.CrudRepository;
/**
 * Created by maciej.debowski on 2015-06-20.
 */

public interface ContactRepository extends CrudRepository<Contact, Long> {

    List<Contact> findByLogin(String login);

}
