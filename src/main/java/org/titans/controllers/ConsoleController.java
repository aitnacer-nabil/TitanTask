package org.titans.controllers;

import org.titans.entities.*;
import org.titans.dao.impl.TaskDAOImp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


public class ConsoleController {
    Scanner scanner;


    public ConsoleController() {
        scanner = new Scanner(System.in);
    }

    public void LoginMenu() {
        System.out.println("========================================");
        System.out.println("Gestion des Tâches ");
        System.out.println("Login : ");
        System.out.print("Entrer  email : ");
        String email = scanner.nextLine();
        System.out.print("Entrer  password : ");
        String password = scanner.next();
        System.out.println("========================================");
        //TODO call login method
        User testUSer = new User("YouDa", "sara@example.com", "admin_password", Role.USER);
        if (testUSer == null) {
            //TODO display message error
        }
        if (testUSer.getRole() == Role.ADMIN) {
            displayMenuAdmin(testUSer);
        } else {
            displayMenuUser(testUSer);
        }
    }

    private void displayMenuUser(User user) {
        System.out.println("User : " + user.getUsername());
        option:
        while (true){

            System.out.println("1. Afficher toutes mes tasks");
            System.out.println("2. Trier la liste ");


            int option = scanner.nextInt();
            switch (option){
                case 1:
                    displayTasksByUserId(user.getId() );
                    break ;
                case 2:
                    sort();
                    //TODO complete code
            }

        }
    }

    private void sort() {
        System.out.println("choisis ");


    }

    private void displayTasksByUserId(String id) {
        System.out.println("La liste des Tasks");

        //TODO getlist of task By user
        displayTasks(new ArrayList<>());

    }

    private void displayMenuAdmin(User user) {
        System.out.println("Admin : " + user.getUsername());
        option:
        while (true) {
            System.out.println("1. Créer un utilisateur");
            System.out.println("2. Créer une catégorie");
            System.out.println("3. Créer une tâche");
            System.out.println("4. Afficher la liste des utilisateurs");
            System.out.println("5. Afficher la liste des catégories");
            System.out.println("6. Afficher la liste des tâches");
            System.out.println("7. Afficher l'historique");
            System.out.println("8. Mettre à jour un utilisateur");
            System.out.println("9. Mettre à jour une tâche");
            System.out.println("10. Supprimer une tâche");
            System.out.println("11. importer un fichier");
            System.out.println("12. Exporter vers un fichier JSON");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    User user1 = createUserFromInput();
                    //TODO ajoute user to list and db
                    break;
                case 2:
                    createCategoryFromInput();
                    //TODO category to list and db
                    break;
                case 3:
                    //TODO list of category and users
                    createTaskFromInput(new ArrayList<>(),new ArrayList<>());
                    break;
                case 4:
                    // Afficher la liste des utilisateurs
                    //TODO ajoute list users
                    displayUser(new ArrayList<>());
                    break ;
                case 5 :
                    //TODO ajoute category list
                    displayCatigory(new ArrayList<>());
                    break;
                case 6 :
                    //TODO ajoute list tasks
                    displayTasks(new ArrayList<>());
                    break ;
                case 7:
                    //TODO ajout list of historique
                    displayHistory(new ArrayList<>());
                    break ;
                case 8:
                    //TODO get list of user
                    updateUSer(new ArrayList<>());
                    break ;
                case 9:
                    //TODO get list of task
                    updateTask(new ArrayList<>());
                    break;
                case 10:
                    //TODO get list of tasks;
                    deleteTask();
                case 11:











            }
        }


    }

    private void deleteTask() {
        displayTasks(new ArrayList<>());
        System.out.print("Sélectionner un identifiant : ");
        String id = scanner.nextLine();


        //TODO get delete method and send the id


    }

    private User createUserFromInput() {
        User user = new User();
        System.out.println("\t- Définir le nom :");
        String nom = scanner.next();
        System.out.println("\t- Définir l'email :");
        String email = scanner.next();
        System.out.println("\t- Définir le mot de passe");
        String password = scanner.next();
        Role role = chooseRole();
        user.setUsername(nom);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        return user;


    }

    private Category createCategoryFromInput() {

        System.out.println("\t- Saisir le nom de la catégorie");
        String nom = scanner.nextLine();
        Category category = new Category(nom);
        return category;
    }

    private Role chooseRole() {
        Role role = Role.USER;
        System.out.println("Choisir un Role");
        System.out.println("1 :  Admin");
        System.out.println("2 :  User");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                role = Role.ADMIN;
                break;
            default:
                role = Role.USER;
        }
        return role;

    }

    private Task createTaskFromInput(List<User> users, List<Category> categories) {
        getUserId(users);
        //TODO Get USER BY ID
        User user = null;
        if (user == null) {
            System.out.println("utilisateur n exist pas");
            return null;
        }
        System.out.print("Choisir une catégorie par identifiant :");
        displayCatigory(categories);
        String categoryId = scanner.nextLine();
        //TODO GET CATEGORY BY ID
        Category category = null;
        if (category == null) {
            System.out.println("category n exist pas");
            return null;
        }
        System.out.print("insérer le titre de la tâche :");
        String title = scanner.nextLine();
        System.out.print("Insérer la description");
        String description = scanner.nextLine();
        Priority priority = choosePriority();
        //TODO CREATE TASK;
        return new Task(title, description, Timestamp.valueOf(LocalDateTime.now()), category, priority, user.getId());
    }

    private String getUserId(List<User> users) {
        System.out.println("\t choisire un  utilisateurs a prtir la list:");
        //TODO display list
        displayUser(users);
        System.out.print("Choisir un utilisateur par identifiant id :");
        String id = scanner.nextLine();
        return id;
    }


    private User updateUSer(List<User> users){
        String id =getUserId(users);
       //TODO Get USER BY ID
       User user = null;
       if (user == null) {
           System.out.println("utilisateur n exist pas");
           return null;
       }
       User user1 = createUserFromInput();
       //TODO cal repository update send id and user1
       return user1;

   }
   private Task updateTask(List<Task> tasks){
        displayTasks(tasks);
       System.out.print("Sélectionner un identifiant : ");
       String id = scanner.nextLine();
       //TODO get task by id
       Task task = null;
       if(task == null){
           System.out.println("Task introuvable");
           return null;
       }
       //TODO get List users and task
      Task updatedTask = createTaskFromInput(new ArrayList<>(),new ArrayList<>());
       //TODO cal update task send task.taskid and updated task
       return  updatedTask;


   }

    private void displayUser(List<User> users) {
        users.stream().forEach(System.out::println);
    }

    private void displayCatigory(List<Category> categories) {
        categories.stream().forEach(System.out::println);
    }
    private void displayTasks(List<Task> tasks) {
        tasks.stream().forEach(System.out::println);
    }
    private void displayHistory(List<TaskHistoryAction> taskHistoryActionList) {
        taskHistoryActionList.stream().forEach(System.out::println);
    }
    private Priority choosePriority() {
        System.out.println("Sélectionner la priorité : ");
        System.out.println("1 Haute");
        System.out.println("2 Moyenne");
        System.out.println("3 Basse");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                return Priority.HAUTE;
            case 2:
                return Priority.MOYENNE;
        }

        return Priority.BASSE;
    }
}


