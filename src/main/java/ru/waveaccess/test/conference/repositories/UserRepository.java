package ru.waveaccess.test.conference.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.waveaccess.test.conference.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByConfirmCode(String confirmCode);
}
