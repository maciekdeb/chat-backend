package hello;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by maciej.debowski on 2015-06-20.
 */

@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date date;
    private String content;
    private boolean isRead;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    private Boolean isIncoming;

    public ChatMessage() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Boolean getIsIncoming() {
        return isIncoming;
    }

    public void setIsIncoming(Boolean isIncoming) {
        this.isIncoming = isIncoming;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + id +
                ", date=" + date +
                ", content='" + content + '\'' +
                ", isRead=" + isRead +
                ", contact=" + contact +
                ", isIncoming=" + isIncoming +
                '}';
    }
}
