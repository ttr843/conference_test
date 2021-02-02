package ru.waveaccess.test.conference.dto;

import lombok.*;
import ru.waveaccess.test.conference.models.Role;
import ru.waveaccess.test.conference.models.State;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private State state;
    private String confirmCode;



}
