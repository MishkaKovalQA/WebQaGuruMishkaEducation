package qap.data;

import qap.domain.User;
import qap.service.SecurityService;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class MockUserRepository implements UserRepository {

    final String password = "password";

    @Override
    public Optional<User> findByUserName(String userName) {
        try {
            User user1 = new User(MISHKA, 5001, new SecurityService().calculateHash(password));
            User user2 = new User(YULIA, 5002, new SecurityService().calculateHash(password));
            User user3 = new User(MUHA, 5003, new SecurityService().calculateHash(password));
            user1.addUsesToContactList(user2, user3);
            if (userName.equals(MISHKA)) {
                return Optional.of(user1);
            } else {
                return Optional.empty();
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findByIcqNumber(int icqNumber) {
        return Optional.empty();
    }
}
