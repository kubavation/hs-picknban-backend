package io.hsproject.Picknban.controller;

import io.hsproject.Picknban.converter.RoomConverter;
import io.hsproject.Picknban.dto.RoomDTO;
import io.hsproject.Picknban.model.Room;
import io.hsproject.Picknban.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static io.hsproject.Picknban.converter.RoomConverter.toEntity;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;


    @PostMapping("/rooms")
    public ResponseEntity<Room> create(@RequestBody RoomDTO roomDTO){
        return new ResponseEntity<>( roomService.createRoom(toEntity(roomDTO)), CREATED );
    }
}
