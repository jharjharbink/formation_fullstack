package org.example.ihm.input.actions.article;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DateUserInput implements UserInput<Date> {
    private final Scanner scanner;

    public DateUserInput(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Date askValue(String message) {
        String dateString = new StringUserInput(scanner).askValue(message);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = formatter.parse(dateString);
            System.out.println("Converted Date: " + date);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format MM/dd/yyyy.");
            throw new RuntimeException(e);
        }
        return date;
    }
}
