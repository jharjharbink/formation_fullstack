package org.example.exceptions;

public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        System.out.println(message);
    }
}
