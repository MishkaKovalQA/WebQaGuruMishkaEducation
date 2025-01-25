package qap.service;

import qap.domain.User;
import qap.exception.AuthenticationException;
import qap.io.LoginView;
import qap.io.MainView;

public class Application {

    private final LoginView loginView;
    private final MainView mainview;

    public Application(LoginView loginView, MainView mainview) {
        this.loginView = loginView;
        this.mainview = mainview;
    }

    public void run() {
        try {
            User user = loginView.doLogin();
            mainview.showMainFrame(user);
            mainview.startMessaging(user);

        } catch (AuthenticationException e) {
            mainview.showError(e);
            run();
        }
    }
}
