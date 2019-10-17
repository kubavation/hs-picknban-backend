package io.hsproject.Picknban.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ROOM")
@Data
@NoArgsConstructor
public class Room {

    @Id
    private String id;
}
