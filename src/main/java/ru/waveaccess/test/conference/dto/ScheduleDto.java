package ru.waveaccess.test.conference.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.waveaccess.test.conference.models.Presentation;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {
    private Long id;
    private int roomNumber;
    private Long presentationId;
    private String startTime;
    private String endTime;
    private PresentationDto presentationDto;
}
