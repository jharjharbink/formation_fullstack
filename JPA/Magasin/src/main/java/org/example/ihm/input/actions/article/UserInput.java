package org.example.ihm.input.actions.article;

public interface UserInput<T> {
    T askValue(String message);
}
