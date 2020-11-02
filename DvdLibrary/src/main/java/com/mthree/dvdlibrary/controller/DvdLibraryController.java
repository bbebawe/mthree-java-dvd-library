/*
 * @Copyright 2020 Beshoy Bebawe.
 */
package com.mthree.dvdlibrary.controller;

import com.mthree.dvdlibrary.dao.DvdLibraryDaoException;
import com.mthree.dvdlibrary.dto.Dvd;
import com.mthree.dvdlibrary.ui.DvdLibrayView;
import java.util.List;
import com.mthree.dvdlibrary.dao.DvdLibraryDaoFileImpl;

/**
 * Handles application operations by using application view and dao
 *
 * @author beshoy
 */
public class DvdLibraryController {

    private DvdLibrayView view;
    private DvdLibraryDaoFileImpl dao;

    public DvdLibraryController(DvdLibrayView view, DvdLibraryDaoFileImpl dao) {
        this.view = view;
        this.dao = dao;
    }

    /**
     * Runs application and call methods based on menu selection input
     *
     * @throws DvdLibraryDaoException
     */
    public void run() throws DvdLibraryDaoException {
        boolean keepGoing = true;
        int menuSelection = 0;
        loadData();
        while (keepGoing) {
            menuSelection = getMenuSelection();
            switch (menuSelection) {
                case 1:
                    addDvd();
                    break;
                case 2:
                    removeDvd();
                    break;
                case 3:
                    editDvd();
                    break;
                case 4:
                    listDvds();
                    break;
                case 5:
                    searchDvd();
                    break;
                case 6:
                    listAllReleasesByNYear();
                    break;
                case 7:
                    listAllMovesByMpaa();
                    break;
                case 8:
                    listAllMovesByDirector();
                    break;
                case 9:
                    listAllMovesByStudio();
                    break;
                case 10:
                    findNewestDvd();
                    break;
                case 11:
                    findOldestDvd();
                    break;
                case 12:
                    findAverageDvdsAge();
                    break;
                case 0:
                    saveData();
                    keepGoing = false;
                    exitMessage();
                    break;
                default:
                    unknownCommand();
            }
        }
    }

    /**
     * Handles the display of application menu
     *
     * @return integer represents user choice
     */
    private int getMenuSelection() {
        view.displaySubmenuBanner("Main Menu");
        // return user choice
        return view.printMenuAndGetSelection();
    }

    /**
     * Handles add DVD operation
     *
     * @throws DvdLibraryDaoException
     */
    private void addDvd() throws DvdLibraryDaoException {
        view.displaySubmenuBanner("Add DVD");
        // used to check if dvd already exsits in collection
        String dvdTitle = view.getDvdTitleChoice();
        Dvd exsitedDvd = dao.getDvd(dvdTitle);
        if (exsitedDvd == null) {
            // this also includes MPAA rating validation
            Dvd newDvd = view.getNewDvdInfo(dvdTitle);
            dao.addDvd(newDvd);
            view.displayCreateDvdSuccessBanner(newDvd);
        } else {
            view.displayDvdExists(dvdTitle);
        }
        handleNextOperation("Add");
    }

    /**
     * Handles remove DVD operation
     *
     * @throws DvdLibraryDaoException
     */
    private void removeDvd() throws DvdLibraryDaoException {
        view.displaySubmenuBanner("Remove DVD");
        String dvdTitle = view.getDvdTitleChoice();
        // used to check if dvd exists so it can be removed
        Dvd exsitedDvd = dao.getDvd(dvdTitle);
        if (exsitedDvd != null) {
            Dvd removedDvd = dao.removeDvd(dvdTitle);
            view.displayRemoveResult(removedDvd);
        } else {
            view.displayDvdNotFound(dvdTitle);
        }
        handleNextOperation("Remove");

    }

    /**
     * Handles edit DVD operation
     *
     * @throws DvdLibraryDaoException
     */
    private void editDvd() throws DvdLibraryDaoException {
        view.displaySubmenuBanner("Edit DVD");
        String dvdTitle = view.getDvdTitleChoice();
        // used to check if dvd exsits before it can be edited
        Dvd exsitedDvd = dao.getDvd(dvdTitle);
        if (exsitedDvd != null) {
            String newDvdTitle = view.getNewDvdTitle();
            // validate that there are no dvd with same title as new title
            Dvd dvdWithSameTitle = dao.getDvd(newDvdTitle);
            // if null or title is same as new title then update 
            if (dvdWithSameTitle == null || dvdWithSameTitle.getTitle().equals(dvdTitle)) {
                Dvd newDvdInfo = view.getNewDvdInfo(newDvdTitle);
                // check if user made any updates or no
                if (dao.isIdenticalDvd(exsitedDvd, newDvdInfo)) {
                    view.displayNoUpdateMade();
                } else {
                    dao.editDvd(dvdTitle, newDvdInfo);
                    view.displayUpdateResult(newDvdInfo);
                }
            } else {
                view.displayDvdWithSameTitle(newDvdTitle);
            }
        } else {
            view.displayDvdNotFound(dvdTitle);
        }
        handleNextOperation("Edit");
    }

    /**
     * Handles list DVDs operation
     *
     * @throws DvdLibraryDaoException
     */
    private void listDvds() throws DvdLibraryDaoException {
        view.displaySubmenuBanner("List DVDs");
        List<Dvd> dvdList = dao.listAllDvds();
        view.displayDvdsList(dvdList);
    }

    /**
     * Handles search DVD operation
     *
     * @throws DvdLibraryDaoException
     */
    private void searchDvd() throws DvdLibraryDaoException {
        view.displaySubmenuBanner("Search DVDs");
        String dvdTitle = view.getDvdTitleChoice();
        // used to check if dvd exsits before it can be displayed
        Dvd dvd = dao.getDvd(dvdTitle);
        if (dvd != null) {
            view.displayDvd(dvd);
        } else {
            view.displayDvdNotFound(dvdTitle);
        }
    }

    /**
     * Prompts user for next operation and handle calling the appropriate method
     *
     * @param operation operation string
     * @throws DvdLibraryDaoException
     */
    private void handleNextOperation(String operation) throws DvdLibraryDaoException {
        String next = view.getNextOperation(operation);
        if (next.equals("y")) {
            switch (operation) {
                case "Add":
                    addDvd();
                    break;
                case "Remove":
                    removeDvd();
                    break;
                case "Edit":
                    editDvd();
            }
        }
    }

    /**
     * Handles unknown command operation
     */
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    /**
     * Handles exit operation
     */
    private void exitMessage() {
        view.displayExitBanner();
    }

    /**
     * Handles load data operation
     *
     * @throws DvdLibraryDaoException
     */
    private void loadData() throws DvdLibraryDaoException {
        dao.loadCollection();
    }

    /**
     * Handles save data operation
     *
     * @throws DvdLibraryDaoException
     */
    private void saveData() throws DvdLibraryDaoException {
        dao.writeCollection();
    }

    private void listAllReleasesByNYear() {
        view.displaySubmenuBanner("List All Releases Sice N Year");
        int year = view.getReleaseYear();
        List<Dvd> allReleases = dao.getAllReleasesSinceNYears(year);
        view.displayDvdsList(allReleases);
    }

    private void listAllMovesByMpaa() {
        view.displaySubmenuBanner("List All Movies By MPAA");
        String mpaa = view.getMpaaRatings();
        List<Dvd> allReleases = dao.getAllDvdsByMpaa(mpaa);
        view.displayDvdsList(allReleases);
    }

    private void listAllMovesByDirector() {
        view.displaySubmenuBanner("List All Movies By Director");
        String director = view.getDirectorName();
        List<Dvd> allReleases = dao.getAllDvdsByDirector(director);
        view.displayDvdsList(allReleases);
    }

    private void listAllMovesByStudio() {
        view.displaySubmenuBanner("List All Movies By Studio");
        String studio = view.getStudioName();
        List<Dvd> allReleases = dao.getAllDvdsByStudio(studio);
        view.displayDvdsList(allReleases);
    }

    private void findNewestDvd() {
        view.displaySubmenuBanner("Find Newest Dvd");
        Dvd newestDvd = dao.getNewestDvd();
        view.displayDvd(newestDvd);
    }

    private void findOldestDvd() {
        view.displaySubmenuBanner("Find Oldest Dvd");
        Dvd oldestDvd = dao.getOldestDvd();
        view.displayDvd(oldestDvd);
    }

    private void findAverageDvdsAge() {
        view.displaySubmenuBanner("Find Average DVDs Age");
       double  averageAge = dao.getAverageAgeofDvds();
        view.display(averageAge);
    }

}
