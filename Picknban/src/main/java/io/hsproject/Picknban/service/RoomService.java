package io.hsproject.Picknban.service;

import io.hsproject.Picknban.dto.RoomDTO;
import io.hsproject.Picknban.model.Room;
import io.hsproject.Picknban.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;


    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }
}
