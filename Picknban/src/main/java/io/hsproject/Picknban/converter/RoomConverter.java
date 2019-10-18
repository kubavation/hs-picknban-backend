package io.hsproject.Picknban.converter;

import io.hsproject.Picknban.dto.RoomDTO;
import io.hsproject.Picknban.model.Room;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

//todo bean??
public class RoomConverter {

    public static RoomDTO toDTO(@NonNull Room room) {
        return RoomDTO.builder()
                .banType(room.getBanType())
                .createdAt(room.getCreatedAt())
                .expiresOn(room.getExpiresOn())
                .creator(room.getCreator())
                .creatorId(room.getCreatorId())
                .creatorBans(room.getCreatorBans())
                .creatorTypes(room.getCreatorTypes())
                .guest(room.getGuest())
                .guestId(room.getGuestId())
                .guestBans(room.getGuestBans())
                .guestTypes(room.getGuestTypes()).build();
    }


    public static Room toEntity(@NonNull RoomDTO roomDTO) {
        return Room.builder()
                .banType(roomDTO.getBanType())
                .createdAt(roomDTO.getCreatedAt())
                .expiresOn(roomDTO.getExpiresOn())
                .creator(roomDTO.getCreator())
                .creatorId(roomDTO.getCreatorId())
                .creatorBans(roomDTO.getCreatorBans())
                .creatorTypes(roomDTO.getCreatorTypes())
                .guest(roomDTO.getGuest())
                .guestId(roomDTO.getGuestId())
                .guestBans(roomDTO.getGuestBans())
                .guestTypes(roomDTO.getGuestTypes()).build();
    }


}
