package org.titans;



import java.util.List;

import org.titans.controllers.ConsoleController;

import org.titans.entities.Category;
import org.titans.entities.Priority;
import org.titans.entities.Task;
import org.titans.services.CategoryDAOImp;
import org.titans.services.TaskDAOImp;
import org.titans.util.Utils;

public class Main {
    public static void main(String[] args) {

    ConsoleController consoleController = new ConsoleController();
    consoleController.MainMenu();
    
   
  

    }
}

