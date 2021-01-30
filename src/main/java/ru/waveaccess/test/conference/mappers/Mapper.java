package ru.waveaccess.test.conference.mappers;

public interface Mapper<T,E> {
    T fromDto(E e);
    E toDto(T t);
}
