package ru.waveaccess.test.conference.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.waveaccess.test.conference.dto.PresentationDto;
import ru.waveaccess.test.conference.mappers.PresentationMapper;
import ru.waveaccess.test.conference.models.Presentation;
import ru.waveaccess.test.conference.models.User;
import ru.waveaccess.test.conference.repositories.PresentationsRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PresentationServiceImpl implements PresentationService {
    private final PresentationsRepository presentationsRepository;
    private final PresentationMapper mapper;

    @Transactional
    @Override
    public List<Presentation> findAllByPresenter(User presenter) {
        return presentationsRepository.findByPresenters(presenter);
    }


    @Transactional
    @Override
    public PresentationDto saveOrUpdate(PresentationDto presentationDto) {
        return mapper.toDto(presentationsRepository.save(mapper.fromDto(presentationDto)));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        presentationsRepository.delete(presentationsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("presentation not found")));
    }
}
