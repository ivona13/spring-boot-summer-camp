package com.ag04smarts.sha.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class ApiError {
    private final Date timestamp;
    private final int status;
    private final String error;
    private final List<String> messages = new ArrayList<>();

    public ApiError(String error, int status) {
        this.error = error;
        this.status = status;
        this.timestamp = new Date();
    }

    public ApiError(List<FieldError> errors) {
        timestamp = new Date();
        status = HttpStatus.BAD_REQUEST.value();
        error = HttpStatus.BAD_REQUEST.name();
        for (FieldError error : errors) {
            messages.add(error.getDefaultMessage());
        }
    }

    @Override
    public String toString() {
        return "timestamp=" + timestamp + "\n" +
                "status=" + status + "\n" +
                "error='" + error + '\n' +
                "messages=" + messages;
    }
}
