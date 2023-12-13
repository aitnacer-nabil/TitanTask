package org.titans;



import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.titans.entities.*;
import org.titans.services.impl.CategoryDAOImp;
import org.titans.services.impl.TaskDAOImp;
import org.titans.services.impl.UserDaoImpl;

public class Main {
    public static void main(String[] args) {

        // Create lists for each class

        UserDaoImpl userDao =new UserDaoImpl();
        CategoryDAOImp categoryDAOImp = new CategoryDAOImp();
        TaskDAOImp taskDAOImp = new TaskDAOImp();
        for (User user: userDao.getAllUsers()
             ) {
            System.out.println(user);
        }
        for (Category category: categoryDAOImp.getAllCategory()
        ) {
            System.out.println(category);
        }
        for (Task task: taskDAOImp.getAllTasks()
        ) {
            System.out.println(task);
        }

    }
}

