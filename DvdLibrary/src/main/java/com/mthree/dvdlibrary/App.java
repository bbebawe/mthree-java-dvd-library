/*
 * @Copyright 2020 Beshoy Bebawe.
 */
package com.mthree.dvdlibrary;

import com.mthree.dvdlibrary.controller.DvdLibraryController;
import com.mthree.dvdlibrary.dao.DvdLibraryDaoException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * App that stores information about a DVD collection.
 *
 * @author beshoy
 */
public class App {

    public static void main(String[] args) throws DvdLibraryDaoException {
//        final String[] MPAA_VALUES = {"G", "PG", "PG-13", "R", "NC-17"};
//        final int MIN_MENU_SELECTION = 0;
//        final int MAX_MENU_SELECTION = 12;
//        // inject project dependency
//        UserIO myIo = new UserIOConsoleImpl(MPAA_VALUES);
//        DvdLibrayView view = new DvdLibrayView(myIo, MIN_MENU_SELECTION, MAX_MENU_SELECTION);
//        DvdLibraryDaoFileImpl dao = new DvdLibraryDaoFileImpl();
//        DvdLibraryController controller = new DvdLibraryController(view, dao);
//        controller.run();

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        DvdLibraryController controller
                = ctx.getBean("controller", DvdLibraryController.class);
        controller.run();
    }
}
