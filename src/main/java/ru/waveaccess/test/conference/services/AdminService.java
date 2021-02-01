package ru.waveaccess.test.conference.services;

import ru.waveaccess.test.conference.dto.UserDto;

import java.util.List;

public interface AdminService {
    UserDto changeUsersRole(Long id);

    void deleteUserById(Long id);

    List<UserDto> getAllUsers(int page, int size);
}
