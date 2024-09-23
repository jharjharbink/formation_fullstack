package org.example.ihm.input.actions.article;

import java.util.Scanner;

public class StringUserInput implements UserInput<String> {
    private final Scanner scanner;

    public StringUserInput(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String askValue(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}