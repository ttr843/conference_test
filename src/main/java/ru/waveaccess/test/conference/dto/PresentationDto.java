package ru.waveaccess.test.conference.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PresentationDto {
    private Long id;
    @NotNull(message = "title can't be empty")
    private String title;
    private List<UserDto> presenters;
}
