package io.hsproject.Picknban.controller;

import io.hsproject.Picknban.converter.RoomConverter;
import io.hsproject.Picknban.dto.RoomDTO;
import io.hsproject.Picknban.model.Room;
import io.hsproject.Picknban.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.hsproject.Picknban.converter.RoomConverter.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/rooms")
    public ResponseEntity<RoomDTO> create(@RequestBody RoomDTO roomDTO){
        return new ResponseEntity<>( toDTO( roomService.createRoom(toEntity(roomDTO)) ), CREATED );
    }

    @GetMapping("/rooms")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>( toDTOCollection(roomService.findAll()), OK );
    }
}
