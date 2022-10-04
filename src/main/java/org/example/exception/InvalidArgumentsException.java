package org.example.exception;

import java.io.IOException;

public class InvalidArgumentsException extends IOException {
    public InvalidArgumentsException(String message) {
        super(message);
    }
}
