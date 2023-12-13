package org.titans;


import org.titans.entities.Category;
import org.titans.entities.Priority;
import org.titans.entities.Task;
import org.titans.services.TaskDAOImp;
import org.titans.util.Utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(Utils.GenerateId());
        System.out.println(Utils.GenerateId());
        System.out.println(Utils.GenerateId());
        System.out.println(Utils.GenerateId());
        System.out.println(Utils.GenerateId());
    }
}