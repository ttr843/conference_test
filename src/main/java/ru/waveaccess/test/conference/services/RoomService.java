package ru.waveaccess.test.conference.services;

import ru.waveaccess.test.conference.models.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAll();

    Room findByNumber(int number);
}
