package io.hsproject.Picknban.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConnectToRoomDTO {

    private String roomId;
    private String userToken;
}
