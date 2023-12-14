package org.titans;



import java.util.List;

import org.titans.entities.*;
import org.titans.dao.impl.TaskDAOImp;
import org.titans.util.FileManager;

public class Main {
    public static void main(String[] args) {

        TaskDAOImp taskDAOImp = new TaskDAOImp();
        List<Task> list = FileManager.loadFromJson();
        for (Task task: list
             ) {
            System.out.println(task);
        }

    }

}

