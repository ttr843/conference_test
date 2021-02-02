package ru.waveaccess.test.conference.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.waveaccess.test.conference.models.Room;
import ru.waveaccess.test.conference.repositories.RoomsRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomsRepository roomsRepository;

    @Transactional
    @Override
    public List<Room> getAll() {
       return roomsRepository.findAll();
    }

    @Transactional
    @Override
    public Room findByNumber(int number) {
        return roomsRepository.findByNumber(number)
                .orElseThrow(() -> new EntityNotFoundException("no room with this number"));
    }
}
