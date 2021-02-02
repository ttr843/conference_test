package ru.waveaccess.test.conference.controllers;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.waveaccess.test.conference.api.SchedulesController;
import ru.waveaccess.test.conference.dto.ScheduleDto;
import ru.waveaccess.test.conference.services.ScheduleService;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@RequiredArgsConstructor
public class ApiSchedulesControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private ScheduleService scheduleService;

    @BeforeEach
    public void setUp(){
        final SchedulesController schedulesController = new SchedulesController(scheduleService);
        mockMvc = MockMvcBuilders.standaloneSetup(schedulesController).build();
        when(scheduleService.findByRoomNumber(1)).thenReturn(getScheduleDtoList());
    }

    @Test
    public void getScheduleByRoomNumberTest() throws Exception {
        mockMvc.perform(get("/api/schedule/room/1")).andDo(print())
                .andExpect(status().isOk())
                 .andExpect(jsonPath(".startTime").value(getScheduleDtoList().get(0).getStartTime()))
                .andExpect(jsonPath(".endTime").value(getScheduleDtoList().get(0).getEndTime()));
    }


    private List<ScheduleDto> getScheduleDtoList(){
        return  Collections.singletonList(ScheduleDto.builder()
                .id(1L)
                .roomNumber(1)
                .presentationId(3L)
                .startTime("2020-02-01T01:33:00.032686+03:00")
                .endTime("2020-02-01T01:55:00.032686+03:00")
                .build());
    }
}
