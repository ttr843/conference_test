package ru.waveaccess.test.conference.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.waveaccess.test.conference.models.Presentation;
import ru.waveaccess.test.conference.models.User;

import java.util.List;

public interface PresentationsRepository extends JpaRepository<Presentation,Long> {
    List<Presentation> findByPresenters(User presenter);
}
