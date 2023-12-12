package org.titans.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.titans.entities.Category;
import org.titans.entities.Priority;
import org.titans.entities.Task;
import org.titans.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.titans.util.ConnectionDB;

public class TaskDAOImp implements TaskDAO {

    @Override
    public Task addTask(Task task) {
        return null;
    }

    @Override
    public Task updateTask(Task t, Integer id) {
        try {

            String query ="update task set  name = ? ,description = ? , date_creation = CURRENT_TIMESTAMP, priority = ?, ref_category = ? where  id = ?;";
         PreparedStatement preparedStatement = ConnectionDB.getConnectionDB().prepareStatement(query);
         preparedStatement.setString(1,t.getName());
         preparedStatement.setString(2,t.getDescription());
         preparedStatement.setString(3,t.getPriority().name());
         preparedStatement.setString(4,t.getCategory().getId());
         preparedStatement.setInt(5,id);

         int i = preparedStatement.executeUpdate();
         if (i == 1){
             return t;
         }
         preparedStatement.close();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteTask(Integer id) {

        Connection con = ConnectionDB.getConnectionDB();
        String deleteQuery = "DELETE FROM task WHERE id= ?";
        if (con == null) {
            System.out.println("Couldn't get connection to the database");
            return;
        }

        try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);

            int rowAffected = preparedStatement.executeUpdate();

            if (rowAffected > 0) {
                System.out.println("Task deleted successfully");
            } else {
                System.out.println("Task not Found");
            }

        } catch (SQLException se) {
            se.printStackTrace();

        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    @Override
    public List<Task> getAllTasks() {
        return null;
    }

    @Override
    public List<Task> sortByDate() {
        Connection connexion = ConnectionDB.getConnectionDB();
        PreparedStatement statement = null; //envoyer des requêtes SQL pré-compilées à une base de données
        ResultSet resultSet = null; //stocker les résultats d'une requête SQL.
        List<Task> tasks = new ArrayList<>();
        try {
            String sql = "SELECT * FROM task LEFT JOIN category ON task.ref_category = category.ref_category ORDER BY date_creation";
            statement = connexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                int taskId = resultSet.getInt("id");
                String taskName = resultSet.getString("name");
                String taskDesc = resultSet.getString("description");
                Date taskDate = resultSet.getDate("date_creation");
                String priority =resultSet.getString("priority");
                Priority taskPriority = Priority.valueOf(priority.toUpperCase());
                String categoryName = resultSet.getString("name_category");
                String categoryRef = resultSet.getString("ref_category");

                Category category = new Category(categoryRef,categoryName);

                Task task = new Task();
                task.setId(taskId);
                task.setName(taskName);
                task.setDateCreation(taskDate);
                task.setPriority(taskPriority);
                task.setCategory(category);
                tasks.add(task);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    @Override
    public List<Task> sortByPriority() {
        Connection connexion = ConnectionDB.getConnectionDB();
        PreparedStatement statement = null; //envoyer des requêtes SQL pré-compilées à une base de données
        ResultSet resultSet = null; //stocker les résultats d'une requête SQL.
        List<Task> tasks = new ArrayList<>();
        try {
            String sql = "SELECT * FROM task LEFT JOIN category ON task.ref_category = category.ref_category ORDER BY \n" +
                    "case priority \n" +
                    "when \"haute\" then 1\n" +
                    "when \"moyenne\" then 2\n" +
                    "when \"basse\" then 3\n" +
                    "else 4\n" +
                    "end;";
            statement = connexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                int taskId = resultSet.getInt("id");
                String taskName = resultSet.getString("name");
                String taskDesc = resultSet.getString("description");
                Date taskDate = resultSet.getDate("date_creation");
                String priority =resultSet.getString("priority");
                Priority taskPriority = Priority.valueOf(priority.toUpperCase());
                String categoryName = resultSet.getString("name_category");
                String categoryRef = resultSet.getString("ref_category");

                Category category = new Category(categoryRef,categoryName);

                Task task = new Task();
                task.setId(taskId);
                task.setName(taskName);
                task.setDateCreation(taskDate);
                task.setPriority(taskPriority);
                task.setCategory(category);
                tasks.add(task);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    @Override
    public List<Task> sortByCategory() {
        return null;
    }

    @Override
    public void addPriorityToTask(Priority priority) {

    }

    @Override
    public void addCategoryToTask(Category category) {

    }

    @Override
    public List<Task> filterByCategory(Category category) {
        return null;
    }
}
