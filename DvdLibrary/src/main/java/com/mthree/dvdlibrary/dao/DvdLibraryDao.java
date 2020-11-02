/*
 * @Copyright 2020 Beshoy Bebawe.
 */
package com.mthree.dvdlibrary.dao;

import com.mthree.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 * Collection of methods used to handle Data Access
 *
 * @author beshoy
 */
public interface DvdLibraryDao {

    /**
     * Adds DVD object to collection map
     *
     * @param dvd DVD object
     * @return previous DVD object associated with the key or null if was no
     * mapping
     * @throws DvdLibraryDaoException
     */
    public Dvd addDvd(Dvd dvd) throws DvdLibraryDaoException;

    /**
     * Removes DVD object from collection map
     *
     * @param dvdTitle title of DVD object to be removed
     * @return previous DVD object associated with the key or null if was no
     * mapping
     * @throws DvdLibraryDaoException
     */
    public Dvd removeDvd(String dvdTitle) throws DvdLibraryDaoException;

    /**
     * Get DVD object from collection map
     *
     * @param dvdTitle title of DVD object to be returned
     * @return the value of DVD object of null if was no mapping for the key
     * @throws DvdLibraryDaoException
     */
    public Dvd getDvd(String dvdTitle) throws DvdLibraryDaoException;

    /**
     * Remove DVD object from collection and add new object with new values
     *
     * @param orginalDvdTitle DVD object to be removed from collection
     * @param newDvdInfo DVD Object to be added to collection
     * @return the value of DVD object of null if was no mapping for the key
     * @throws DvdLibraryDaoException
     */
    public Dvd editDvd(String orginalDvdTitle, Dvd newDvdInfo) throws DvdLibraryDaoException;

    /**
     * Gets list of all DVDs in collection
     *
     * @return list of DVDS or null
     * @throws DvdLibraryDaoException
     */
    public List<Dvd> listAllDvds() throws DvdLibraryDaoException;

    public List<Dvd> getAllReleasesSinceNYears(int year);
    /**
     * Checks if two DVDs are identical and have same attribute values
     *
     * @param originalDvd first DVD object to compare
     * @param oldDvd second DVD object to compare
     * @return true if two objects identical otherwise false
     */
    boolean isIdenticalDvd(Dvd originalDvd, Dvd oldDvd);

    /**
     * Get DVD object from String
     *
     * @param dvdAsText String representation for DVD object
     * @return DVD object from string
     * @throws DvdLibraryDaoException
     */
    public Dvd unmarshallDvd(String dvdAsText) throws DvdLibraryDaoException;

    /**
     * Loads data into application
     *
     * @throws DvdLibraryDaoException
     */
    public void loadCollection() throws DvdLibraryDaoException;

    /**
     * Convert DVD object to string
     *
     * @param dvd DVD object to be converted
     * @return String representation for DVD object
     * @throws DvdLibraryDaoException
     */
    public String marshallDvd(Dvd dvd) throws DvdLibraryDaoException;

    /**
     * Saves application data
     *
     * @throws DvdLibraryDaoException
     */
    public void writeCollection() throws DvdLibraryDaoException;
}
