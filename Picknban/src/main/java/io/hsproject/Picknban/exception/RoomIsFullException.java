package io.hsproject.Picknban.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RoomIsFullException extends RuntimeException{

    public RoomIsFullException(String id) {
        super("ROOM OF ID " + id + " IS FULL.");
    }
}
