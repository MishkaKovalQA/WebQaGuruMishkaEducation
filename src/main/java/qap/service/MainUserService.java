package qap.service;

import qap.data.UserRepository;
import qap.domain.User;
import qap.exception.AuthenticationException;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Optional;

public class MainUserService implements UserService {

    private final SecurityService sc;
    private final UserRepository userRepository;

    public MainUserService(SecurityService sc, UserRepository userRepository) {
        this.sc = sc;
        this.userRepository = userRepository;
    }

    @Override
    public User authenticate(String username, String password) throws AuthenticationException {
        Optional<User> byUserName = userRepository.findByUserName(username);
        if (byUserName.isPresent()) {
            User user = byUserName.get();
            try {
                byte[] calculatedHash = sc.calculateHash(password);
                if (Arrays.equals(calculatedHash, user.getPasswordHash())) {
                    return user;
                } else {
                    throw new AuthenticationException("Invalid password");
                }
            } catch (NoSuchAlgorithmException e) {
                throw new AuthenticationException("Error calculating hash", e);
            }
        } else {
            throw new AuthenticationException("Not found user by name: " + username);
        }
    }
}
