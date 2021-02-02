package ru.waveaccess.test.conference.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import ru.waveaccess.test.conference.dto.ScheduleDto;
import ru.waveaccess.test.conference.mappers.ScheduleMapper;
import ru.waveaccess.test.conference.models.Room;
import ru.waveaccess.test.conference.models.Schedule;
import ru.waveaccess.test.conference.repositories.SchedulesRepository;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleMapper scheduleMapper;
    private final RoomService roomService;
    private final SchedulesRepository schedulesRepository;

    @Transactional
    @Override
    public boolean addPresentationToSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = scheduleMapper.fromDto(scheduleDto);
        ZonedDateTime startTime = schedule.getStartTime();
        ZonedDateTime endTime = schedule.getEndTime();
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("wrong time");
        }
        Room room = schedule.getRoom();
        List<Schedule> scheduleList = schedulesRepository.findByRoom(room);
        for (Schedule sch : scheduleList) {
            ZonedDateTime sTime = sch.getStartTime();
            ZonedDateTime eTime = sch.getEndTime();
            if (startTime.isBefore(eTime) && sTime.isBefore(endTime)) {
                throw new IllegalArgumentException("time already in use,choose another");
            }
        }
        schedulesRepository.save(schedule);
        return true;
    }

    @Override
    public List<ScheduleDto> findByRoomNumber(int number) {
        Room room = roomService.findByNumber(number);
        List<Schedule> scheduleList = schedulesRepository
                .findByRoom(room);
        return scheduleList.stream()
                .map(scheduleMapper::toDto)
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public Map<Integer, List<Schedule>> findAllScheduleByRoom() {
        Map<Integer, List<Schedule>> listMap = new HashMap<>();
        List<Room> roomList = roomService.getAll();
        for (Room room : roomList
        ) {
            List<Schedule> scheduleList = schedulesRepository.findByRoom(room);
            listMap.put(room.getNumber(), scheduleList);
        }
        return listMap;
    }


}
