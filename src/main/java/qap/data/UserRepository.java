package qap.data;

import qap.domain.User;

import java.util.Optional;

public interface UserRepository {
    final String MISHKA = "Mishka";
    final String YULIA = "Yulia";
    final String MUHA = "Muha";

    Optional<User> findByUserName(String userName);

    Optional<User> findByIcqNumber(int icqNumber);
}
