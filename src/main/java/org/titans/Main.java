package org.titans;



import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.titans.entities.*;
import org.titans.services.impl.CategoryDAOImp;
import org.titans.services.impl.TaskDAOImp;
import org.titans.services.impl.UserDaoImpl;
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

