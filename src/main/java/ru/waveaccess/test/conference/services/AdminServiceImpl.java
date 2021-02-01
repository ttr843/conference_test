package ru.waveaccess.test.conference.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.waveaccess.test.conference.dto.UserDto;
import ru.waveaccess.test.conference.mappers.Mapper;
import ru.waveaccess.test.conference.models.User;
import ru.waveaccess.test.conference.repositories.UsersRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static ru.waveaccess.test.conference.models.Role.LISTENER;
import static ru.waveaccess.test.conference.models.Role.PRESENTER;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UsersRepository usersRepository;
    private final Mapper<User, UserDto> mapper;

    @Transactional
    @Override
    public UserDto changeUsersRole(Long id) {
        User user = usersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));
        if(user.getRole() == LISTENER){
            user.setRole(PRESENTER);
        }else {
            user.setRole(PRESENTER);
        }
        usersRepository.save(user);
        return mapper.toDto(user);
    }


    @Transactional
    @Override
    public void deleteUserById(Long id) {
        usersRepository.delete(usersRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("user not found")));
    }

    @Transactional
    @Override
    public List<UserDto> getAllUsers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return usersRepository.findAll(pageRequest).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
