/*
 * @Copyright 2020 Beshoy Bebawe.
 */
package com.mthree.dvdlibrary.ui;

import com.mthree.dvdlibrary.dto.Dvd;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author beshoy
 */
public class DvdLibrayView {
    
    private UserIO io;
    private int minMenuSelection;
    private int maxMenuSelection;
    
    public DvdLibrayView(UserIO io, int minMenuSelection, int maxMenuSelection) {
        this.io = io;
        this.minMenuSelection = minMenuSelection;
        this.maxMenuSelection = maxMenuSelection;
    }

    /**
     * Print menu options to console using UserIO interface
     *
     * @return Integer - user choice
     */
    public int printMenuAndGetSelection() {
        io.print("1. Add DVD to Collection");
        io.print("2. Remove DVD from Collection");
        io.print("3. Edit Existing DVD");
        io.print("4. List all DVDs");
        io.print("5. Search DVD by Title");
        io.print("6. List All Releases in the Last N years");
        io.print("7. Find all the movies with a givenMPAA rating");
        io.print("8. Find all the movies by a given director");
        io.print("9. Find all the movies released by a particular studio");
        io.print("10. Find the newest movie in your collection");
        io.print("11. Find the oldest movie in your collection");
        io.print("12. Find the average age of the movies in the collection");
        io.print("0. Exit");
        return io.readInt("Please select from the above choices.", minMenuSelection, maxMenuSelection);
    }

    /**
     * Displays text to console
     *
     * @param banner String - text
     */
    public void displaySubmenuBanner(String banner) {
        io.print("=== " + banner + " ===");
    }

    /**
     * Prompts user for DVD title
     *
     * @return String - DVD title
     */
    public String getDvdTitleChoice() {
        return io.readString("Please enter the DVD title.");
    }

    /**
     * Displays DVD exists message to console
     *
     * @param dvdTitle blank
     */
    public void displayDvdExists(String dvdTitle) {
        io.print("DVD " + dvdTitle + " already exists.");
        io.readEnter("Please hit enter to continue.");
    }

    /**
     * Prompts to get new DVD information, but not the title
     *
     * @param dvdTitle String - DVD title
     * @return Object - DVD object created using user input
     */
    public Dvd getNewDvdInfo(String dvdTitle) {
        Dvd newDvd = new Dvd();
        LocalDate date = io.readDate("Enter release date? ");
        String MPAARating = io.readMpaaRating("Enter MPAA rating? ");
        String directorName = io.readString("Enter director name? ");
        String studio = io.readString("Enter studio name? ");
        String userRating = io.readString("Enter you rating? ");
        newDvd.setTitle(dvdTitle);
        newDvd.setDate(date);
        newDvd.setMPAARating(MPAARating);
        newDvd.setDirectorName(directorName);
        newDvd.setStudio(studio);
        newDvd.setUserRating(userRating);
        return newDvd;
    }

    /**
     * Prompts to get new DVD information as well as the title
     *
     * @return Object - DVD object created using user input
     */
    public Dvd getNewDvdInfo() {
        Dvd newDvd = new Dvd();
        String dvdTitle = io.readString("Enter dvd new title");
        LocalDate date = io.readDate("Enter release date? ");
        String MPAARating = io.readMpaaRating("Enter MPAA rating? ");
        String directorName = io.readString("Enter director name? ");
        String studio = io.readString("Enter studio name? ");
        String userRating = io.readString("Enter you rating? ");
        newDvd.setTitle(dvdTitle);
        newDvd.setDate(date);
        newDvd.setMPAARating(MPAARating);
        newDvd.setDirectorName(directorName);
        newDvd.setStudio(studio);
        newDvd.setUserRating(userRating);
        return newDvd;
    }

    /**
     * Displays successfully created message including title
     *
     * @param newDvd newly created DVD object
     */
    public void displayCreateDvdSuccessBanner(Dvd newDvd) {
        io.print("DVD " + newDvd.getTitle() + " successfully created.");
        io.readEnter("Please hit enter to continue");
    }

    /**
     * Displays successfully removed message including title
     *
     * @param removedDvd removed DVD object
     */
    public void displayRemoveResult(Dvd removedDvd) {
        io.print("DVD " + removedDvd.getTitle() + " successfully removed.");
        io.readEnter("Please hit enter to continue.");
    }

    /**
     * Displays does not exist message including title
     *
     * @param dvdTitle DVD title
     */
    public void displayDvdNotFound(String dvdTitle) {
        io.print("DVD " + dvdTitle + " does not exist.");
        io.readEnter("Please hit enter to continue.");
    }

    /**
     * Prompt user to enter new DVD title
     *
     * @return String - new DVD title
     */
    public String getNewDvdTitle() {
        return io.readString("Enter new DVD title");
    }

    /**
     * Displays successfully updated message with title
     *
     * @param newDvdInfo updated DVD object
     */
    public void displayUpdateResult(Dvd newDvdInfo) {
        io.print("DVD " + newDvdInfo.getTitle() + " successfully updated.");
        io.readEnter("Please hit enter to continue.");
    }

    /**
     * Displays same DVD title exists
     *
     * @param newDvdTitle new DVD title
     */
    public void displayDvdWithSameTitle(String newDvdTitle) {
        io.print("There is a DVD with same title, please try again using diffrent update title");
        io.readEnter("Please hit enter to continue.");
    }

    /**
     * Displays no update message
     */
    public void displayNoUpdateMade() {
        io.print("Seems that the two DVDs are identical. No update made");
        io.readEnter("Please hit enter to continue.");
    }

    /**
     * Displays information for each DVD object in the list or no DVDs message
     * if list empty
     *
     * @param dvdList DVDs list
     */
    public void displayDvdsList(List<Dvd> dvdList) {
        // condtion is used to handle when collection file is empty
        if (!dvdList.isEmpty()) {
            // this will act as data header
            io.print("[title, release date, MPAA rating, director name, studio, user rating]");
            for (Dvd currentDvd : dvdList) {
                String dvdInfo = String.format("%s, %s, %s, %s, %s, %s",
                        currentDvd.getTitle(),
                        currentDvd.getDate(),
                        currentDvd.getMPAARating(),
                        currentDvd.getDirectorName(),
                        currentDvd.getStudio(),
                        currentDvd.getUserRating());
                io.print(dvdInfo);
            }
            io.readEnter("Please hit enter to continue.");
        } else {
            io.print("no DVDs found in the list, please add new DVD first");
            io.readEnter("Please hit enter to continue.");
        }
        
    }

    /**
     * Display DVD information to console
     *
     * @param dvd DVD object
     */
    public void displayDvd(Dvd dvd) {
        io.print("DVD Title: " + dvd.getTitle());
        io.print("Release Date: " + dvd.getDate());
        io.print("MPAA Rating: " + dvd.getMPAARating());
        io.print("Director: " + dvd.getDirectorName());
        io.print("Studio: " + dvd.getStudio());
        io.print("Director: " + dvd.getUserRating());
        io.readEnter("Please hit enter to continue.");
    }

    /**
     * Display exist banner
     */
    public void displayExitBanner() {
        io.print("=== Exit ===");
        io.print("Good Bye.");
    }

    /**
     * Display unknown command banner
     */
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command.");
    }
    
    public String getNextOperation(String operation) {
        io.print("Do you want to " + operation + " another DVD?");
        return io.readNextOpration("y = Yes\nn = Main Menu");
    }
    
    public int getReleaseYear() {
        return io.readYear("Enter the year");
    }
    
    public String getMpaaRatings() {
        return io.readMpaaRating("Enter MPAA ratings");
    }
    
    public String getDirectorName() {
        return io.readString("Enter Director Name");
    }
    
    public String getStudioName() {
        return io.readString("Enter Studio Name");
    }
    
    public void display(double averageAge) {
        io.print("The Average Age of DVDs is " + averageAge + " years");
    }
}
