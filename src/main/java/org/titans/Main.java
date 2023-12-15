package org.titans;



import java.util.List;

import org.titans.controllers.ConsoleController;
import org.titans.entities.*;
import org.titans.dao.impl.TaskDAOImp;
import org.titans.util.FileManager;

public class Main {
    public static void main(String[] args) throws Exception {

        ConsoleController consoleController = new ConsoleController();
        consoleController.LoginMenu();
    }

}

