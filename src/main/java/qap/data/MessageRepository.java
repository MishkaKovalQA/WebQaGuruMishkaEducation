package qap.data;

import qap.domain.Message;
import qap.domain.User;

import java.util.List;

public interface MessageRepository {

    void sendMessage(User user, Message message);

    List<Message> getAllMessages(User user);
}
