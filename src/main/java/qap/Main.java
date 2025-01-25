package qap;

import qap.data.MockMessageRepository;
import qap.data.MockUserRepository;
import qap.io.SwingLoginView;
import qap.io.SwingMainView;
import qap.service.Application;
import qap.service.MainUserService;
import qap.service.SecurityService;

public class Main {

    public static void main(String[] args) {
        //dependency injection
        new Application(
                new SwingLoginView(
                        new MainUserService(
                                new SecurityService(),
                                new MockUserRepository()
                        )
                ),
                new SwingMainView(
                        new MockMessageRepository()
                )
        ).run();
    }
}
