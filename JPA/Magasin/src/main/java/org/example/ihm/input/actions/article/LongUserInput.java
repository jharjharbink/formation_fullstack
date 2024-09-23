package org.example.ihm.input.actions.article;

import java.util.Scanner;

public class LongUserInput implements UserInput<Long> {
    private final Scanner scanner;

    public LongUserInput(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Long askValue(String message) {
        while (true) {
            try {
                System.out.println(message);
                long input = scanner.nextLong();
                scanner.nextLine();
                return input;
            } catch (Exception e) {
                System.out.println("Saisie incorrecte. Veuillez entrer un nombre entier valide.");
            }
        }
    }
}

