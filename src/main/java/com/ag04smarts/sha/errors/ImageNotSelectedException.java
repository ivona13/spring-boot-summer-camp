package com.ag04smarts.sha.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ImageNotSelectedException extends RuntimeException{
    public ImageNotSelectedException() {
        super("Image should be selected!");
    }

}
