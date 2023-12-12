package org.titans;

import org.titans.entities.Category;
import org.titans.entities.Priority;
import org.titans.entities.Task;
import org.titans.services.TaskDAOImp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        TaskDAOImp taskDAOImp = new TaskDAOImp();
        Task task = new Task("2","HEllo",
                "World", Timestamp.valueOf(LocalDateTime.now())
                ,new Category("dev","development"),
                Priority.HAUTE);
        System.out.println(taskDAOImp.updateTask(task,2));
    }
}