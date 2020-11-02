/*
 * @Copyright 2020 Beshoy Bebawe.
 */
package com.mthree.dvdlibrary.dto;

import java.time.LocalDate;

/**
 * DVD class
 *
 * @author beshoy
 */
public class Dvd {

    private String title;
    private LocalDate date;
    private String MPAARating;
    private String directorName;
    private String studio;
    private String userRating;

    public Dvd() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMPAARating() {
        return MPAARating;
    }

    public void setMPAARating(String MPAARating) {
        this.MPAARating = MPAARating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

}
