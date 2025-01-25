package qap.service;

import qap.domain.User;
import qap.exception.AuthenticationException;

public interface UserService {

    User authenticate(String username, String password) throws AuthenticationException;
}
