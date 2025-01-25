package qap.data;

import qap.domain.Message;
import qap.domain.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MockMessageRepository implements MessageRepository {

    @Override
    public void sendMessage(User user, Message message) {

    }

    @Override
    public List<Message> getAllMessages(User user) {
        //new User(MISHKA, 5001, new SecurityService().calculateHash(password))
        // Yulia 5002
        // Muha 5003

        Message msgFromYulia0 = new Message("Привет от Юли", 5002, 5001, yesterdayDate());
        Message msgFromYulia1 = new Message("Как дела?", 5002, 5001, yesterdayDate());
        return List.of(msgFromYulia0, msgFromYulia1);
    }

    private Date yesterdayDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_WEEK, -1);
        return cal.getTime();
    }

    public static void main(String[] args) {
        MockMessageRepository mockMessageRepository = new MockMessageRepository();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(mockMessageRepository.yesterdayDate()));
    }
}
