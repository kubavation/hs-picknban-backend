package io.hsproject.Picknban.dto;

import io.hsproject.Picknban.enums.Class;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BanDTO {

    private List<Class> bans;
    private String roomId;
    private String userToken;

}
