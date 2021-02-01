package ru.waveaccess.test.conference.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.waveaccess.test.conference.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByConfirmCode(String confirmCode);
    @Modifying
    @Query(value = "update conference.users set state = 'LOCKED' where email = :email",nativeQuery = true)
    void lock(String email);
}
