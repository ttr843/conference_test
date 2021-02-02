package ru.waveaccess.test.conference.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.waveaccess.test.conference.dto.ScheduleDto;
import ru.waveaccess.test.conference.services.ScheduleService;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class SchedulesController {
    private final ScheduleService scheduleService;


    @GetMapping("/api/schedule/room/{number}")
    public List<ScheduleDto> getByRoom(@PathVariable("number") Integer number) {
        return scheduleService.findByRoomNumber(number);
    }

}
