package org.example.ihm.input.actions.article;

import java.util.Scanner;

public class DoubleUserInput implements UserInput<Double> {
    private final Scanner scanner;

    public DoubleUserInput(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Double askValue(String message) {
        while (true) {
            try {
                System.out.println(message);
                String input = scanner.nextLine();
                return Double.parseDouble(input.replace(',', '.')); // Remplace les virgules par des points si nécessaire
            } catch (NumberFormatException e) {
                System.out.println("Saisie incorrecte. Veuillez entrer un nombre décimal valide.");
            }
        }
    }
}
