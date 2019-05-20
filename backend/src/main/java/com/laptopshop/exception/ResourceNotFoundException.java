package com.laptopshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(Class entity, Number id) {
        super(entity.getSimpleName() + " with id: " + id + " not found");
    }

}
