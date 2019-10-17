package io.hsproject.Picknban.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "ROOM")
@Data
@NoArgsConstructor
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

}
