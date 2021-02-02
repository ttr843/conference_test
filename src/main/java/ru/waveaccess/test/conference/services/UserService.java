package ru.waveaccess.test.conference.services;

import ru.waveaccess.test.conference.dto.SignUpDto;
import ru.waveaccess.test.conference.dto.UserDto;

public interface UserService {
    UserDto signUp(SignUpDto signUpDto);

    UserDto saveOrUpdate(UserDto userDto);
}
