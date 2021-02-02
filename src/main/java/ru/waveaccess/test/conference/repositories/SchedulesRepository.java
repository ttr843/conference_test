package ru.waveaccess.test.conference.repositories;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.waveaccess.test.conference.models.Room;
import ru.waveaccess.test.conference.models.Schedule;

import java.awt.print.Pageable;
import java.util.List;

public interface SchedulesRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByRoom(Room room);
}
