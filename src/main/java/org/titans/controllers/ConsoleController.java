package org.titans.controllers;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.titans.entities.Priority;
import org.titans.entities.Task;
import org.titans.services.TaskDAOImp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.List;


public class ConsoleController {
    Scanner scanner;
    ArrayList<Task> tasks;
    String userName = "root";
    String password = "root";

    TaskDAOImp taskDAOImp = new TaskDAOImp();


    public ConsoleController() {
        scanner = new Scanner(System.in);
        tasks = new ArrayList<>();
        Task t1 = new Task(1, "task1", "desc", Timestamp.valueOf(LocalDateTime.now()), null, Priority.HAUTE);
        Task t2 = new Task(2, "task2", "desc", Timestamp.valueOf(LocalDateTime.now()), null, Priority.BASSE);
        Task t3 = new Task(3, "task3", "desc", Timestamp.valueOf(LocalDateTime.now()), null, Priority.MOYENNE);
        Task t4 = new Task(4, "task4", "desc", Timestamp.valueOf(LocalDateTime.now()), null, Priority.HAUTE);
        Task t5 = new Task(5, "task5", "desc", Timestamp.valueOf(LocalDateTime.now()), null, Priority.BASSE);
        Task t6 = new Task(6, "task6", "desc", Timestamp.valueOf(LocalDateTime.now()), null, Priority.HAUTE);
        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);
        tasks.add(t4);
        tasks.add(t5);
        tasks.add(t6);
    }

    public void addTaskInput() {

        ArrayList<Task> tasksList = new ArrayList<>();

        System.out.println("Name Task :");
        String name = scanner.next();

        System.out.println("Description Task :");
        String description = scanner.next();

        System.out.println("Priority Task :");
        System.out.println("tapez 1 pour HAUTE tapez 2 pour Moyenne Tapez 3 pour BASSE");
        Priority priority = Priority.BASSE;

        int choix = scanner.nextInt();
        switch (choix) {
            case 1:
                priority = Priority.HAUTE;
                break;
            case 2:
                priority = Priority.MOYENNE;
                break;
            case 3:
                priority = Priority.BASSE;
                break;
            default:
                break;
        }

        Task task = new Task();

        task.setName(name);
        task.setDescription(description);
        task.setDateCreation(Timestamp.valueOf(LocalDateTime.now()));
        task.setPriority(priority);
        taskDAOImp.addTask(task);
        System.out.println(task);
    }

    public void getAllTasksInput() {
        List<Task> taskList;
        taskList = taskDAOImp.getAllTasks();
        System.out.println("La liste des taches :");

        for (Task t : taskList
        ) {
            System.out.println(t);
        }
    }

    public void sortByDateInput() {
        List<Task> taskList;
        taskList = taskDAOImp.sortByDate();
        System.out.println("** list of tasks sorted by date **");

        for (Task t : taskList
        ) {
            System.out.println(t);
        }
    }

    public void sortByPriorityInput() {
        List<Task> taskList = new ArrayList<>();
        taskList = taskDAOImp.sortByPriority();
        System.out.println("** list of tasks sorted by priority **");

        for (Task t : taskList
        ) {
            System.out.println(t);
        }
    }

    public void sortByCategoryInput() {
        List<Task> taskList;
        taskList = taskDAOImp.sortByCategory();
        System.out.println("** list of tasks sorted by category **");

        for (Task t : taskList
        ) {
            System.out.println(t);
        }
    }

    public int login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez le nom de l'utilisateur ");
        String user = scanner.next();


        if (user.equalsIgnoreCase(userName)) {
            System.out.println("Entrez le mot de passe ");
            String password = scanner.next();
            if (password.equals(password))
                return 1;
            else
                System.out.println(" mot de passe incorrect");
            return 0;
        } else {
            System.out.println(" utilisateur  incorrect");
            return 0;
        }
    }

    public int validInputInt(String message) {
        String input;
        int number;

        do {
            System.out.print("Entrez un nombre : ");
            input = scanner.next();
            if (!input.matches("\\d+")) {
                System.out.println(message);
            }
        } while (!input.matches("\\d+"));

        return Integer.parseInt(input);
    }

    public void MainMenu() {


        String mainMenuMessage = "* To Add Task click 1:\n" +
                "* To display AllTask click 2 \n" +
                "* To display the list of tasks sorted by date click 3 \n" +
                "* To display the list of tasks sorted by priority click 4 \n" +
                "* To display the list of tasks sorted by category click 5 \n" +
                "* To exit click 6 \n";


        option:
        while (true) {
            System.out.println("************************************************");
            System.out.println("\t \t \t \t Tasks Manager");
            System.out.println("************************************************");
            System.out.println(mainMenuMessage);

            int option = validInputInt(mainMenuMessage);
            switch (option) {
                case 1:
                    addTaskInput();
                    MainMenu();
                    ;
                    break;
                case 2:
                    getAllTasksInput();
                    MainMenu();
                    ;
                    break;
                case 3:
                    sortByDateInput();
                    MainMenu();
                    ;
                    break;
                case 4:
                    sortByPriorityInput();
                    MainMenu();
                    ;
                    break;
                case 5:
                    sortByCategoryInput();
                    MainMenu();
                    ;
                    break;
                case 6:
                    break option;
                default:
                    break;

            }
        }

    }
}


