/*
 * @Copyright 2020 Beshoy Bebawe.
 */
package com.mthree.dvdlibrary.dao;

/**
 * Exception class
 * @author beshoy
 */
public class DvdLibraryDaoException extends Exception {

    public DvdLibraryDaoException(String message) {
        super(message);
    }

    public DvdLibraryDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
