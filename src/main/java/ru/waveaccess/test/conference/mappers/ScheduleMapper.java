package ru.waveaccess.test.conference.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.waveaccess.test.conference.dto.ScheduleDto;
import ru.waveaccess.test.conference.models.Schedule;
import ru.waveaccess.test.conference.repositories.PresentationsRepository;
import ru.waveaccess.test.conference.repositories.RoomsRepository;
import ru.waveaccess.test.conference.services.PresentationService;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class ScheduleMapper implements Mapper<Schedule, ScheduleDto> {
    private final PresentationsRepository presentationsRepository;
    private final RoomsRepository roomsRepository;
    private final PresentationMapper presentationMapper;

    @Override
    public Schedule fromDto(ScheduleDto scheduleDto) {
        return Schedule.builder()
                .id(scheduleDto.getId())
                .room(roomsRepository.findByNumber(scheduleDto.getRoomNumber())
                        .orElseThrow(() -> new EntityNotFoundException("Room with this number did not found")))
                .presentation(presentationsRepository.findById(scheduleDto.getPresentationId())
                        .orElseThrow(() -> new EntityNotFoundException("Presentation with this id did not found")))
                .startTime(ZonedDateTime.parse(scheduleDto.getStartTime()))
                .endTime(ZonedDateTime.parse(scheduleDto.getEndTime()))
                .build();
    }

    @Override
    public ScheduleDto toDto(Schedule schedule) {
        return ScheduleDto.builder()
                .id(schedule.getId())
                .presentationId(schedule.getPresentation().getId())
                .roomNumber(schedule.getRoom().getNumber())
                .startTime(schedule.getStartTime().toString())
                .endTime(schedule.getEndTime().toString())
                .presentationDto(presentationMapper.toDto(schedule.getPresentation()))
                .build();
    }
}
