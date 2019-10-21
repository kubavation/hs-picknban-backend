package io.hsproject.Picknban.service;

import io.hsproject.Picknban.dto.BanDTO;
import io.hsproject.Picknban.dto.ConnectToRoomDTO;
import io.hsproject.Picknban.dto.RoomDTO;
import io.hsproject.Picknban.enums.Class;
import io.hsproject.Picknban.enums.UserType;
import io.hsproject.Picknban.exception.ResourceNotFoundException;
import io.hsproject.Picknban.exception.RoomIsFullException;
import io.hsproject.Picknban.model.Room;
import io.hsproject.Picknban.repository.RoomRepository;
import io.hsproject.Picknban.util.TokenGeneratorUtils;
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

    public Room updateBans(final BanDTO banDTO) {
        return roomRepository.findById(banDTO.getRoomId())
                .map(r -> setBansBasedOnToken(r, banDTO))
                .orElseThrow(RuntimeException::new);
    }

    private Room setBansBasedOnToken(final Room room, final BanDTO banDTO) {
       switch (TokenGeneratorUtils.resolveFromToken(banDTO.getUserToken())) {
           case CREATOR:
               room.setCreatorBans(banDTO.getBans()); break;
           case GUEST:
               room.setGuestBans(banDTO.getBans()); break;
           default:
               break;
       }

       return roomRepository.save(room);
    }

    public Room ifPossibleJoinRoom(@NonNull final ConnectToRoomDTO connectDTO) {
        return Optional.ofNullable(connectDTO.getUserToken())
                .map((gId) -> tryToJoinRoomAsGuest(connectDTO.getRoomId(), gId))
                .orElseGet(() -> tryToJoinRoomAndCreateGuest(connectDTO));
    }

    private Room tryToJoinRoomAsGuest(String roomId, String guestId) {
        return roomRepository.findById(roomId)
                .filter(r -> guestId.equals(r.getGuestId()))
                .orElseThrow(RuntimeException::new);
    }

    private Room tryToJoinRoomAndCreateGuest(final ConnectToRoomDTO connectDTO) {
        if (isRoomHasEmptySpot(connectDTO.getRoomId())) {
            return roomRepository.findById(connectDTO.getRoomId())
                    .map(r -> roomRepository.save(onGuestConnect(r, connectDTO.getClasses())))
                    .orElseThrow(() -> new ResourceNotFoundException(connectDTO.getRoomId(), Room.class));

        } else {
            throw new RoomIsFullException(connectDTO.getRoomId());
        }
    }

    /*init of tokens on create*/
    private Room onCreate(Room old) {
        Room room = Room.of(old);
        room.setId(UUID.randomUUID().toString());
        room.setCreatorId(TokenGeneratorUtils.generate(room.getId(), UserType.CREATOR));
        room.setCreatedAt(now());
        room.setExpiresOn(now().plusMinutes(10));
        return room;
    }

    /*init of token on guest connect*/
    private Room onGuestConnect(Room old, List<Class> types) {
        Room room = Room.of(old);
        room.setGuestId(TokenGeneratorUtils.generate(room.getId(), UserType.GUEST));
        room.setGuestTypes(types);
        return room;
    }
}
