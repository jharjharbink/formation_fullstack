package org.example.ihm.input.actions.article;

import java.util.Scanner;

public class IntUserInput implements UserInput<Integer> {
    private final Scanner scanner;

    public IntUserInput(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Integer askValue(String message) {
        while (true) {
            try {
                System.out.println(message);
                int input = scanner.nextInt();
                scanner.nextLine();
                return input;
            } catch (Exception e) {
                System.out.println("Saisie incorrecte. Veuillez entrer un nombre entier valide.");
            }
        }
    }
}
