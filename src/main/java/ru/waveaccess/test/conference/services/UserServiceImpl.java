package ru.waveaccess.test.conference.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.waveaccess.test.conference.dto.SignUpDto;
import ru.waveaccess.test.conference.dto.UserDto;
import ru.waveaccess.test.conference.mappers.Mapper;
import ru.waveaccess.test.conference.models.Role;
import ru.waveaccess.test.conference.models.State;
import ru.waveaccess.test.conference.models.User;
import ru.waveaccess.test.conference.repositories.UsersRepository;
import ru.waveaccess.test.conference.utils.annotations.MailType;
import ru.waveaccess.test.conference.utils.annotations.SendMail;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper<User, UserDto> mapper;


    @SendMail(mailType = MailType.CONFIRM)
    @Transactional
    @Override
    public UserDto signUp(SignUpDto signUpDto) {
        usersRepository.findByEmail(signUpDto.getEmail())
                .ifPresent(user -> {
                    throw new BadCredentialsException("User already present");
                });
        User user = User.builder()
                .role(Role.LISTENER)
                .email(signUpDto.getEmail())
                .firstName(signUpDto.getFirstName())
                .lastName(signUpDto.getLastName())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .confirmCode(UUID.randomUUID().toString())
                .state(State.NOT_CONFIRMED)
                .build();
        usersRepository.save(user);
        return mapper.toDto(user);
    }

    @Override
    public UserDto saveOrUpdate(UserDto userDto) {
        User user = mapper.fromDto(userDto);
        return mapper.toDto(usersRepository.save(user));
    }
}
