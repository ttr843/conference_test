package ru.waveaccess.test.conference.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rooms")
public class Room {
    @Column(updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return getNumber() == room.getNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber());
    }
}