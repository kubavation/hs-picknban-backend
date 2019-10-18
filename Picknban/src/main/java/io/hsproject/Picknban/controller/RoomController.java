package io.hsproject.Picknban.controller;

import io.hsproject.Picknban.converter.RoomConverter;
import io.hsproject.Picknban.dto.RoomDTO;
import io.hsproject.Picknban.model.Room;
import io.hsproject.Picknban.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static io.hsproject.Picknban.converter.RoomConverter.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final SimpMessageSendingOperations messagingTemplate;


    @GetMapping("/rooms")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>( toDTOCollection(roomService.findAll()), OK );
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<RoomDTO> findById(@PathVariable String id) {
        return new ResponseEntity<>( toDTO(roomService.findById(id)), OK );
    }

    @PostMapping("/rooms")
    public ResponseEntity<RoomDTO> create(@RequestBody RoomDTO roomDTO){
        return new ResponseEntity<>( toDTO( roomService.createRoom(toEntity(roomDTO)) ), CREATED );
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<RoomDTO> update(@RequestBody RoomDTO roomDTO) {
        return new ResponseEntity<>( toDTO( roomService.updateRoom(toEntity(roomDTO))), OK );
    }

    @MessageMapping("/rooms/{roomId}")
    @Synchronized //todo ?
    public void send(@DestinationVariable Long roomId, @Payload RoomDTO roomDTO) {
        this.messagingTemplate.convertAndSend("/rooms/" + roomId,
                toDTO( roomService.updateRoom(toEntity(roomDTO))) );
    }

}
