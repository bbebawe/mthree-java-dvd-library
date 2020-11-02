/*
 * @Copyright 2020 Beshoy Bebawe.
 */
package com.mthree.dvdlibrary.ui;

import java.time.LocalDate;

/**
 * Collection of methods used to handle and validate user input from console
 *
 * @author beshoy
 */
public interface UserIO {

    /**
     * prints message to console
     *
     * @param msg String - message
     */
    void print(String msg);

    /**
     * prints message to the console and reads Integer
     *
     * @param prompt String message
     * @return Integer - number from user input
     */
    int readInt(String prompt);

    /**
     * prints message to the console and reads Integer and read number between
     * min and max value
     *
     * @param prompt String message
     * @param min Integer - min number allowed
     * @param max Integer - max number allowed
     * @return Integer - number from user input between min and max values
     */
    int readInt(String prompt, int min, int max);

    /**
     * prompt user for input using prompt message
     *
     * @param prompt String - message
     * @return String - user input
     */
    String readString(String prompt);

    /**
     * prompt for MPAA rating value
     *
     * @param prompt String - message
     * @return String - MPAA rating value
     */
    String readMpaaRating(String prompt);

    /**
     * Prompts user to enter date
     *
     * @param prompt prompt message
     * @return date in a formatted string
     */
    LocalDate readDate(String prompt);

    /**
     * Prompts user for next operation
     *
     * @param prompt next operation banner
     * @return user choice as string
     */
    String readNextOpration(String prompt);

    /**
     * prompts user to hit enter to continue
     *
     * @param prompt prompt
     * @return String - blank input
     */
    String readEnter(String prompt);

    int readYear(String pronpt);
}
