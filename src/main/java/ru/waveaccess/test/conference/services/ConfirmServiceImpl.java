package ru.waveaccess.test.conference.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.waveaccess.test.conference.models.State;
import ru.waveaccess.test.conference.models.User;
import ru.waveaccess.test.conference.repositories.UsersRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ConfirmServiceImpl implements ConfirmService {
    private final UsersRepository usersRepository;

    @Override
    public boolean confirm(String confirmCode) {
        Optional<User> userOptional = usersRepository.findByConfirmCode(confirmCode);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setState(State.CONFIRMED);
            usersRepository.save(user);
            return true;
        }
        return false;

    }
}
