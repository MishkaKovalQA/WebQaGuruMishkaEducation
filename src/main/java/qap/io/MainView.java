package qap.io;

import qap.domain.User;

public interface MainView extends View {

    void showError(Throwable t);

    void showMainFrame(User user);

    void startMessaging(User user);
}

