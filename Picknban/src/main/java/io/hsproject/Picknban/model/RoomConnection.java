package io.hsproject.Picknban.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ROOM_CONNECTION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomConnection {

    @Id
    private String roomId;

    private String creatorId;
    private String guestId;
}
