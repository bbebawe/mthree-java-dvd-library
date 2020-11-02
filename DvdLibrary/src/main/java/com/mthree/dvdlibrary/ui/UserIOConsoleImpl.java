/*
 * @Copyright 2020 Beshoy Bebawe.
 */
package com.mthree.dvdlibrary.ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Used to handle user input from console implements {@link #UserIO()}
 *
 * @author beshoy
 */
public class UserIOConsoleImpl implements UserIO {

    final private Scanner console = new Scanner(System.in);
    private String[] mpaaValues;

    public UserIOConsoleImpl(String[] mpaaValues) {
        this.mpaaValues = mpaaValues;
    }

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        String userInput = null;
        boolean validInput = false;
        do {
            print(prompt);
            userInput = console.nextLine();
            if (userInput.isBlank()) {
                print("Input can not be blank, please try again");
            } else {
                validInput = true;
            }
        } while (!validInput);

        return userInput;
    }

    @Override
    public int readInt(String prompt) {
        String stringValue = null;
        int number = 0;
        boolean validInput = false;
        do {
            try {
                stringValue = readString(prompt);
                number = Integer.parseInt(stringValue);
                validInput = true;
            } catch (NumberFormatException e) {
                print(stringValue + " is not valid input, please try again");
            }
        } while (!validInput);
        return number;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int number = 0;
        do {
            number = readInt(prompt);
            if (number < min || number > max) {
                // message is printed because readInt() will not print if input is number but out off range
                print(number + " is not valid input, please try again");
            }
        } while (number < min || number > max);
        return number;
    }

    @Override
    public String readMpaaRating(String prompt) {
        String mpaaRating = null;
        String userInput = null;
        boolean validMpaaValue = false;
        do {
            print(prompt);
            print("Rating Values: " + Arrays.toString(mpaaValues));
            userInput = console.nextLine();
            for (String value : mpaaValues) {
                if (userInput.equals(value)) {
                    mpaaRating = userInput;
                    validMpaaValue = true;
                    break;
                }
            }
            if (!validMpaaValue) {
                print(userInput + " is not a valud rating value. please try again");
            }
        } while (!validMpaaValue);

        return mpaaRating;
    }

    @Override
    public LocalDate readDate(String prompt) {
        Scanner in = new Scanner(System.in);
        LocalDate releaseDate = null;
        String dateFormat = "yyyy-mm-dd";
        String userInput = null;
        boolean validDate = false;

        do {
            try {
                System.out.println("Enter release Date in the following format " + dateFormat);
                userInput = in.nextLine();
                // throws DateTimeParseException is incoming date is invalid format
                releaseDate = LocalDate.parse(userInput);
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println(userInput + " is invalid date, please try again");
            }
        } while (!validDate);
        return releaseDate;
    }

    @Override
    public String readEnter(String prompt) {
        print(prompt);
        return console.nextLine();
    }

    @Override
    public String readNextOpration(String prompt) {
        String userInput = null;
        boolean validInput = false;
        do {
            print(prompt);
            userInput = console.nextLine();
            if (userInput.toLowerCase().equals("y") || userInput.toLowerCase().equals("n")
                    || userInput.toLowerCase().equals("yes") || userInput.toLowerCase().equals("no")) {
                validInput = true;
            } else {
                print("Invalid input, please choose y or n");
            }
        } while (!validInput);

        return userInput;
    }

    public int readYear(String prompt) {
        Scanner in = new Scanner(System.in);
        LocalDate releaseDate = null;
        String dateFormat = "yyyy";
        String userInput = null;
        boolean validDate = false;

        do {
            try {
                System.out.println("Enter release year in the following format " + dateFormat);
                userInput = in.nextLine();
                // throws DateTimeParseException is incoming date is invalid format
                // "-01-01" addes to try to parse year from user input year
                releaseDate = LocalDate.parse(userInput + "-01-01");
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println(userInput + " is invalid year, please try again");
            }
        } while (!validDate);
        return releaseDate.getYear();
    }

}
