package ru.waveaccess.test.conference.mappers;


import org.springframework.stereotype.Component;
import ru.waveaccess.test.conference.dto.UserDto;
import ru.waveaccess.test.conference.models.User;

@Component
public class UserMapper implements Mapper<User,UserDto> {
    public User fromDto(UserDto userDto) {
        return User.builder()
        .state(userDto.getState())
        .confirmCode(userDto.getConfirmCode())
        .lastName(userDto.getLastName())
        .firstName(userDto.getFirstName())
        .email(userDto.getEmail())
        .role(userDto.getRole())
        .id(userDto.getId()).build();
    }

    public UserDto toDto(User user){
        return UserDto.builder()
                .confirmCode(user.getConfirmCode())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .state(user.getState())
                .id(user.getId())
                .build();
    }
}

