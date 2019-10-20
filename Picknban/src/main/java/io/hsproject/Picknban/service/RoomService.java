package io.hsproject.Picknban.service;

import io.hsproject.Picknban.dto.RoomDTO;
import io.hsproject.Picknban.exception.ResourceNotFoundException;
import io.hsproject.Picknban.model.Room;
import io.hsproject.Picknban.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public Room createRoom(Room room) {
        return roomRepository.save(onCreate(room));
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room findById(String id) {
        return roomRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id, Room.class));
    }

    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    /*init of tokens on create*/
    private Room onCreate(Room old) {
        Room room = Room.of(old);
        room.setCreatorId(UUID.randomUUID().toString());
        room.setCreatedAt(now());
        room.setExpiresOn(now().plusMinutes(10));
        return room;
    }

    private Room on
}
