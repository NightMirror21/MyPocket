package ru.nightmirror.mypocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nightmirror.mypocket.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    void deleteByUsername(String username);

    Optional<User> findByUsername(String username);
}
