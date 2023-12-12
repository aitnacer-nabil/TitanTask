package org.titans;


import org.titans.entities.Category;
import org.titans.entities.Priority;
import org.titans.entities.Task;
import org.titans.services.TaskDAOImp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TaskDAOImp taskDAOImp = new TaskDAOImp();
//    Task task = new Task("Enrigster","Write sql query",null,Priority.HAUTE);
//    taskDAOImp.addTask(task);
    taskDAOImp.addPriorityToTask(14,Priority.BASSE);
    }
}