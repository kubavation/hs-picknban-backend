package io.hsproject.Picknban.dto;

import io.hsproject.Picknban.enums.BanType;
import io.hsproject.Picknban.enums.Class;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    private String id;
    private String creator;
    private String creatorId;
    private String guest;
    private String guestId;
    private LocalDateTime createdAt;
    private LocalDateTime expiresOn;
    private boolean isActive;
    private BanType banType;
    private List<Class> creatorTypes = new ArrayList<>();
    private List<Class> guestTypes = new ArrayList<>();
    private List<Class> creatorBans = new ArrayList<>();
    private List<Class> guestBans = new ArrayList<>();

}
