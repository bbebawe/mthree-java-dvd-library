/*
 * @Copyright 2020 Beshoy Bebawe.
 */
package com.mthree.dvdlibrary.dao;

import com.mthree.dvdlibrary.dto.Dvd;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author beshoy
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    ClassLoader classLoader = getClass().getClassLoader();
    private Map<String, Dvd> collection = new HashMap<>();
    public static final String COLLECTION_FILE = "collection.txt";
    public static final String DELIMITER = "::";

    @Override
    public Dvd getDvd(String dvdTitle) throws DvdLibraryDaoException {
        loadCollection();
        return collection.get(dvdTitle);
    }

    @Override
    public Dvd addDvd(Dvd dvd) throws DvdLibraryDaoException {
        Dvd newDvd = collection.put(dvd.getTitle(), dvd);
        writeCollection();
        return newDvd;
    }

    @Override
    public Dvd removeDvd(String dvdTitle) throws DvdLibraryDaoException {
        Dvd removedDvd = collection.remove(dvdTitle);
        writeCollection();
        return removedDvd;
    }

    @Override
    public Dvd editDvd(String orginalDvdTitle, Dvd newDvdInfo) throws DvdLibraryDaoException {
        Dvd edittedDvd = null;
        // remove original dvd and create new entry with new data
        collection.remove(orginalDvdTitle);
        edittedDvd = collection.put(newDvdInfo.getTitle(), newDvdInfo);
        writeCollection();
        return edittedDvd;
    }

    @Override
    public boolean isIdenticalDvd(Dvd originalDvd, Dvd newDvd) {
        return originalDvd.getTitle().equals(newDvd.getTitle())
                && originalDvd.getDate().equals(newDvd.getDate())
                && originalDvd.getMPAARating().equals(newDvd.getMPAARating())
                && originalDvd.getDirectorName().equals(newDvd.getDirectorName())
                && originalDvd.getStudio().equals(newDvd.getStudio())
                && originalDvd.getUserRating().equals(newDvd.getUserRating());
    }

    @Override
    public List<Dvd> listAllDvds() throws DvdLibraryDaoException {
        loadCollection();
        return new ArrayList<Dvd>(collection.values());
    }

    @Override
    public Dvd unmarshallDvd(String dvdAsText) {
        // dvd line structure
        // __________________________________________________
        // |      |      |      |          |        |        |
        // |title | date | MPAA | direcotr | studio | rating |
        // |      |      |      |          |        |        |
        // ---------------------------------------------------
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        // create Dvd object and set its state using tokens stored in the file
        Dvd dvdFromFile = new Dvd();
        // Index 0 - title
        dvdFromFile.setTitle(dvdTokens[0]);
        // Index 1 - release date
        dvdFromFile.setDate(LocalDate.parse(dvdTokens[1]));
        // Index 2 - MPAARating
        dvdFromFile.setMPAARating(dvdTokens[2]);
        // Index 4 - directorName
        dvdFromFile.setDirectorName(dvdTokens[3]);
        // Index 5 - studio
        dvdFromFile.setStudio(dvdTokens[4]);
        // Index 6 - userRating
        dvdFromFile.setUserRating(dvdTokens[5]);
        // return Dvd object
        return dvdFromFile;
    }

    @Override
    public void loadCollection() throws DvdLibraryDaoException {
        Scanner scanner;
        try {
            File file = new File(COLLECTION_FILE);

            scanner = new Scanner(
                    new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException(
                    "Could not load collection data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentDvd holds the most recent dvd unmarshalled
        Dvd currentDvd;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            // unmarshall the line into a Dvd object if the line is not empty
            // condition added to avoid exception when trying to parse empty line
            if (!currentLine.isEmpty()) {
                currentDvd = unmarshallDvd(currentLine);
                // dvd title is used as key in the HashMap
                collection.put(currentDvd.getTitle(), currentDvd);
            }
        }
        scanner.close();
    }

    @Override
    public String marshallDvd(Dvd dvd) {
        // create String to represnt dvd object
        String dvdAsText = dvd.getTitle() + DELIMITER;
        dvdAsText += dvd.getDate() + DELIMITER;
        dvdAsText += dvd.getMPAARating() + DELIMITER;
        dvdAsText += dvd.getDirectorName() + DELIMITER;
        dvdAsText += dvd.getStudio() + DELIMITER;
        dvdAsText += dvd.getUserRating();
        // dvd object as string
        return dvdAsText;
    }

    @Override
    public void writeCollection() throws DvdLibraryDaoException {
        PrintWriter out;
        try {
            File file = new File(COLLECTION_FILE);

            out = new PrintWriter(file);
        } catch (IOException e) {
            throw new DvdLibraryDaoException(
                    "Could not save collection data.", e);
        }
        String dvdAsText;
        // create list of dvd objects to be wrriten to the file
        List<Dvd> dvdList = new ArrayList(collection.values());
        for (Dvd currentDvd : dvdList) {
            // turn a Student into a String
            dvdAsText = marshallDvd(currentDvd);
            // write the Student object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    @Override
    public List<Dvd> getAllReleasesSinceNYears(int year) {
        List<Dvd> allReleases = collection.values().stream()
                .filter((p) -> p.getDate().getYear() >= year)
                .collect(Collectors.toList());
        return allReleases;
    }

    public List<Dvd> getAllDvdsByMpaa(String mpaa) {
        List<Dvd> allReleases = collection.values().stream()
                .filter((p) -> p.getMPAARating().equals(mpaa))
                .collect(Collectors.toList());
        return allReleases;
    }

    public List<Dvd> getAllDvdsByDirector(String director) {
        List<Dvd> allReleases = collection.values().stream()
                .filter((p) -> p.getDirectorName().equals(director))
                .sorted(Comparator.comparing(Dvd::getMPAARating))
                .collect(Collectors.toList());
        return allReleases;
    }

    public List<Dvd> getAllDvdsByStudio(String studio) {
        List<Dvd> allReleases = collection.values().stream()
                .filter((p) -> p.getStudio().equals(studio))
                .collect(Collectors.toList());
        return allReleases;
    }

    public Dvd getNewestDvd() {
        Dvd newestDvd = collection.values().stream()
                .max(Comparator.comparing(Dvd::getDate)).get();
        return newestDvd;
    }

    public Dvd getOldestDvd() {
        Dvd oldestDvd = collection.values().stream()
                .min(Comparator.comparing(Dvd::getDate)).get();
        return oldestDvd;
    }

    public double getAverageAgeofDvds() {
        double average = collection.values().stream()
                .mapToInt((p) -> p.getDate().until(LocalDate.now()).getYears()).average().getAsDouble();

        return average;
    }

}
