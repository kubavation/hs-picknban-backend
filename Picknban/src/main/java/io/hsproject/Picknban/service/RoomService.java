package io.hsproject.Picknban.service;

import io.hsproject.Picknban.dto.RoomDTO;
import io.hsproject.Picknban.exception.ResourceNotFoundException;
import io.hsproject.Picknban.exception.RoomIsFullException;
import io.hsproject.Picknban.model.Room;
import io.hsproject.Picknban.repository.RoomRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
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

    public boolean isRoomHasEmptySpot(String id) {
       return roomRepository.findById(id)
                .map(r -> r.getGuestId() == null)
                .orElseThrow(() -> new ResourceNotFoundException(id, Room.class));
    }


    //todo uuid generator with prrefixes
    public Room ifPossibleJoinRoom(@NonNull String roomId, String guestId) {
        return Optional.ofNullable(guestId)
                .map((gId) -> tryToJoinRoomAsGuest(roomId, gId))
                .orElseGet(() -> tryToJoinRoomAndCreateGuest(roomId));
    }

    private Room tryToJoinRoomAsGuest(String roomId, String guestId) {
        return roomRepository.findById(roomId)
                .filter(r -> guestId.equals(r.getGuestId()))
                .orElseThrow(RuntimeException::new);
    }

    private Room tryToJoinRoomAndCreateGuest(String roomId) {
        if (isRoomHasEmptySpot(roomId)) {
            return roomRepository.findById(roomId)
                    .map(r -> {
                        r.setGuestId(UUID.randomUUID().toString());
                        return roomRepository.save(r);
                    }).orElseThrow(() -> new ResourceNotFoundException(roomId, Room.class));

        } else {
            throw new RoomIsFullException(roomId);
        }
    }

    /*init of tokens on create*/
    private Room onCreate(Room old) {
        Room room = Room.of(old);
        room.setCreatorId(UUID.randomUUID().toString());
        room.setCreatedAt(now());
        room.setExpiresOn(now().plusMinutes(10));
        return room;
    }

    /*init of token on guest connect*/
    private Room onGuestConnect(Room old) {
        Room room = Room.of(old);
        room.setGuestId(UUID.randomUUID().toString());
        return room;
    }
}
