package ru.nightmirror.mypocket.service;

import ru.nightmirror.mypocket.entity.User;

import java.util.Optional;

public interface UserService {
    void save(String username, String password);

    boolean existsByUsername(String username);

    void deleteByUsername(String username);

    Optional<User> findByUsername(String username);
}
