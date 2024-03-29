package io.hsproject.Picknban.model;


import io.hsproject.Picknban.enums.BanType;
import io.hsproject.Picknban.enums.Class;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "ROOM")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    private String id;

    private String creator;
    private String creatorId;

    private String guest;
    private String guestId;

    private LocalDateTime createdAt;
    private LocalDateTime expiresOn;
    private boolean isActive;

    private BanType banType;

    //todo other class?
    private List<Class> creatorTypes = new ArrayList<>();
    private List<Class> guestTypes = new ArrayList<>();

    private List<Class> creatorBans = new ArrayList<>();
    private List<Class> guestBans = new ArrayList<>();


    public static Room of(final Room room) {
        return Room.builder()
                .createdAt(room.createdAt)
                .creatorTypes(room.creatorTypes)
                .creatorBans(room.creatorBans)
                .banType(room.banType)
                .creator(room.creator)
                .creatorId(room.creatorId)
                .id(room.id)
                .isActive(room.isActive)
                .expiresOn(room.expiresOn)
                .guestTypes(room.guestTypes)
                .guestBans(room.guestBans)
                .guest(room.guest)
                .guestId(room.guestId)
                .build();
    }

}
