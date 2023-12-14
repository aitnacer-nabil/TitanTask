package org.titans.controllers;

import org.titans.entities.*;
import org.titans.dao.impl.TaskDAOImp;
import org.titans.repositories.CategoryRepository;
import org.titans.repositories.HistoriqueRepository;
import org.titans.repositories.TaskRepository;
import org.titans.repositories.UserRepository;

import java.security.cert.CRL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


public class ConsoleController {
    Scanner scanner;
    UserLogin userLogin = new UserLogin();
    TaskRepository taskRepository = new TaskRepository();
    HistoriqueRepository historiqueRepository = new HistoriqueRepository();
    CategoryRepository categoryRepository = new CategoryRepository();
    UserRepository userRepository = new UserRepository();


    public ConsoleController() {
        scanner = new Scanner(System.in);
    }

    public void LoginMenu() throws Exception {
        System.out.println("========================================");
        System.out.println("Gestion des Tâches ");
        System.out.println("Login : ");
        System.out.print("Entrer  email : ");
        String email = scanner.nextLine().trim();
        System.out.print("Entrer  password : ");
        String password = scanner.next().trim();
        System.out.println("========================================");

        User user = userLogin.Login(email,password);
        if (user == null) {

            System.out.println("Error email or password not correct");
            LoginMenu();
            return;
        }
        if (user.getRole() == Role.ADMIN) {
            displayMenuAdmin(user);
        } else {
            displayMenuUser(user);
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


        displayTasks(taskRepository.getTaskByUserIdRepo(id));

    }

    private void displayMenuAdmin(User user) throws Exception {
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
            scanner.nextLine();
            switch (option) {
                case 1:
                    User user1 = createUserFromInput();
                    userRepository.createUser(user1);

                    break;
                case 2:
                    Category category = createCategoryFromInput();
                    categoryRepository.addCategoryRep(category);

                    break;
                case 3:
                    List<User> users = userRepository.getAllUsers();
                    List<Category> categories = categoryRepository.getCategoryList();
                    //TODO Hide user password and role
                    //TODO category chould get category id from database
                    createTaskFromInput(users,categories);

                    break;
                case 4:
                    // Afficher la liste des utilisateurs

                    displayUser(userRepository.getAllUsers());
                    break ;
                case 5 :

                    displayCatigory(categoryRepository.getCategoryList());
                    break;
                case 6 :

                    displayTasks(taskRepository.getTasksList());
                    break ;
                case 7:

                    displayHistory(historiqueRepository.getHistoryList());
                    break ;
                case 8:

                    updateUSer(userRepository.getAllUsers());

                    break ;
                case 9:

                    updateTask(taskRepository.getTasksList());

                    break;
                case 10:

                    deleteTask(user);
                case 11:


            }
        }


    }

    private void deleteTask(User user) {
        displayTasks(taskRepository.getTasksList());
        System.out.print("Sélectionner un identifiant : ");
        String id = scanner.nextLine();
        taskRepository.deleteTaskRepo(id,user.getId());




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
        System.out.println();
        System.out.print("\t- Saisir le nom de la catégorie");
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
        String id = getUserId(users);

        User user = userRepository.getUserById(id);
        if (user == null) {
            System.out.println("utilisateur n exist pas");
            return null;
        }
        System.out.print("Choisir une catégorie par identifiant :");
        displayCatigory(categories);
        String categoryId = scanner.nextLine();

        Category category = categoryRepository.getCategoryBy(categoryId);
        if (category == null) {
            System.out.println("category n exist pas");
            return null;
        }
        System.out.print("insérer le titre de la tâche :");
        String title = scanner.nextLine();
        System.out.print("Insérer la description");
        String description = scanner.nextLine();
        Priority priority = choosePriority();
        Task task = new Task(title, description, Timestamp.valueOf(LocalDateTime.now()), category, priority, user.getId());
          taskRepository.addTaskRepo(task);
          return task;

    }

    private String getUserId(List<User> users) {
        System.out.println("\t choisire un  utilisateurs a prtir la list:");
        //TODO display list
        displayUser(users);
        System.out.print("Choisir un utilisateur par identifiant id :");
        String id = scanner.nextLine();
        return id;
    }


    private User updateUSer(List<User> users) throws Exception {
        String id =getUserId(users);

       User user = userRepository.getUserById(id);
       if (user == null) {
           System.out.println("utilisateur n exist pas");
           return null;
       }
       User user1 = createUserFromInput();
        userRepository.updateUser(id,user1);
       return user1;

   }
   private Task updateTask(List<Task> tasks) throws Exception {
        displayTasks(tasks);
       System.out.print("Sélectionner un identifiant : ");
       String id = scanner.nextLine();
       Task task = taskRepository.getTaskById(id);
       if(task == null){
           System.out.println("Task introuvable");
           return null;
       }
      Task updatedTask = createTaskFromInput(userRepository.getAllUsers(),categoryRepository.getCategoryList());
       taskRepository.updateTaskRepo(id,updatedTask);
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


