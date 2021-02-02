package ru.waveaccess.test.conference.services;

import ru.waveaccess.test.conference.dto.ScheduleDto;
import ru.waveaccess.test.conference.models.Schedule;

import java.util.List;
import java.util.Map;

public interface ScheduleService {
    boolean addPresentationToSchedule(ScheduleDto scheduleDto);

    List<ScheduleDto> findByRoomNumber(int number);

    Map<Integer, List<Schedule>> findAllScheduleByRoom();
}
