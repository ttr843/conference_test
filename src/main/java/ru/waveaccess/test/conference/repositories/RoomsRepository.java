package ru.waveaccess.test.conference.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.waveaccess.test.conference.models.Room;

import java.util.Optional;

public interface RoomsRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByNumber(int number);
}
