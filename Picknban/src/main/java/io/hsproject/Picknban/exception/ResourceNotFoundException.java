package io.hsproject.Picknban.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String id, Class<?> clazz) {
        super( format("RESOURCE WITH ID %s NOT FOUND OF CLASS %s", id, clazz.toString()) );
    }
}
