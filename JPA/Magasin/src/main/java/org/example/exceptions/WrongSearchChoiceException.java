package org.example.exceptions;

public class WrongSearchChoiceException extends RuntimeException{
    public WrongSearchChoiceException(String message) {
        System.out.println(message);
    }
}
