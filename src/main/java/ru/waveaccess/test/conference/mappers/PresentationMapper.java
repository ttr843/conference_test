package ru.waveaccess.test.conference.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.waveaccess.test.conference.dto.PresentationDto;
import ru.waveaccess.test.conference.models.Presentation;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PresentationMapper implements Mapper<Presentation, PresentationDto> {
    private final UserMapper userMapper;

    @Override
    public Presentation fromDto(PresentationDto presentationDto) {
        return Presentation.builder()
                .id(presentationDto.getId())
                .title(presentationDto.getTitle())
                .presenters(presentationDto.getPresenters().stream()
                        .map(userMapper::fromDto)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public PresentationDto toDto(Presentation presentation) {
        return PresentationDto.builder()
                .id(presentation.getId())
                .title(presentation.getTitle())
                .presenters(presentation.getPresenters().stream()
                        .map(userMapper::toDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
