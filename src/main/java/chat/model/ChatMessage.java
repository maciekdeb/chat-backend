package chat.model;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatMessage that = (ChatMessage) o;

        if (id != that.id) return false;
        if (isRead != that.isRead) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (contact != null ? !contact.equals(that.contact) : that.contact != null) return false;
        return !(isIncoming != null ? !isIncoming.equals(that.isIncoming) : that.isIncoming != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (isRead ? 1 : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (isIncoming != null ? isIncoming.hashCode() : 0);
        return result;
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
