package qap.io;

import qap.domain.User;
import qap.exception.AuthenticationException;

public interface LoginView extends View {

    User doLogin() throws AuthenticationException;
}
