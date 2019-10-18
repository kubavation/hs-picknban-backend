package io.hsproject.Picknban.service;

import io.hsproject.Picknban.dto.RoomDTO;
import io.hsproject.Picknban.model.Room;
import io.hsproject.Picknban.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room findById(String id) {
        return roomRepository.findById(id)
                    .orElseThrow(RuntimeException::new);
    }
}
