package ru.waveaccess.test.conference.services;

import ru.waveaccess.test.conference.dto.PresentationDto;
import ru.waveaccess.test.conference.models.Presentation;
import ru.waveaccess.test.conference.models.User;

import java.util.List;

public interface PresentationService {
    List<Presentation> findAllByPresenter(User presenter);

    PresentationDto saveOrUpdate(PresentationDto presentationDto);

    void deleteById(Long id);
}
