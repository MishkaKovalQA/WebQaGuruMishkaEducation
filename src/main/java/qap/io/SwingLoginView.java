package qap.io;

import qap.domain.User;
import qap.exception.AuthenticationException;
import qap.service.UserService;

import javax.swing.*;

public class SwingLoginView implements LoginView {

    private final UserService userService;

    public SwingLoginView(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User doLogin() throws AuthenticationException {
        String username = showInputPopup("Enter username: ");
        String password = showInputPopup("Enter password: ");
        return userService.authenticate(username, password);
    }

    private String showInputPopup(String message) {
        return (String) JOptionPane.showInputDialog(
                null,
                message,
                APP_NAME,
                JOptionPane.QUESTION_MESSAGE,
                DEER_ICON,
                null,
                null
        );
    }
}
