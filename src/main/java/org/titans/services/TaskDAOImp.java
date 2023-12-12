package org.titans.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.titans.entities.Category;
import org.titans.entities.Priority;
import org.titans.entities.Task;
import org.titans.util.ConnectionDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.titans.util.ConnectionDB;

public class TaskDAOImp implements TaskDAO {
Connection connection;

    public TaskDAOImp() {
        this.connection = ConnectionDB.getConnectionDB();
    }

    @Override
    public void addTask(Task task) {
        try {
            String query = "INSERT INTO task (name,description,date_creation,priority) VALUES(?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,task.getName());
            preparedStatement.setString(2,task.getDescription());
            preparedStatement.setTimestamp(3,Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(4,task.getPriority().name());

            int i = preparedStatement.executeUpdate();
            if(i == 1){
                System.out.println("Add successfully");
            } else {
                System.out.println("Not Add successfully");
            }





        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Task updateTask(Task t, Integer id) {
        try {

            String query = "update task set  name = ? ,description = ? , date_creation = CURRENT_TIMESTAMP, priority = ?, ref_category = ? where  id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, t.getName());
            preparedStatement.setString(2, t.getDescription());
            preparedStatement.setString(3, t.getPriority().name());
            preparedStatement.setString(4, t.getCategory().getId());
            preparedStatement.setInt(5, id);

            int i = preparedStatement.executeUpdate();
            if (i == 1) {
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


        String deleteQuery = "DELETE FROM task WHERE id= ?";
        if (connection == null) {
            System.out.println("Couldn't get connection to the database");
            return;
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);

            int rowAffected = preparedStatement.executeUpdate();

            if (rowAffected == 1) {
                System.out.println("Task deleted successfully");
            } else {
                System.out.println("Task not Found");
            }

        } catch (SQLException se) {
            se.printStackTrace();

        }

    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();

        try {
            String getAllQuery = "SELECT * FROM task LEFT JOIN category ON task.ref_category = category.ref";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllQuery);
            while (resultSet.next()) {

                Task task = generateTaskFromResultSet(resultSet);
                taskList.add(task);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return taskList;
    }

    @Override
    public List<Task> sortByDate() {

        Statement statement = null; //envoyer des requêtes SQL pré-compilées à une base de données
        ResultSet resultSet = null; //stocker les résultats d'une requête SQL.
        List<Task> tasks = new ArrayList<>();
        try {
            String sql = "SELECT * FROM task LEFT JOIN category ON task.ref_category = category.ref ORDER BY date_creation";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Task task = generateTaskFromResultSet(resultSet);
                tasks.add(task);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    private Task generateTaskFromResultSet(ResultSet resultSet) throws SQLException {
        int taskId = resultSet.getInt("id");
        String taskName = resultSet.getString("name");
        String taskDesc = resultSet.getString("description");
        Date taskDate = resultSet.getDate("date_creation");
        String priority = resultSet.getString("priority");
        Priority taskPriority = Priority.valueOf(priority.toUpperCase());
        String categoryName = resultSet.getString("name_category");
        String categoryRef = resultSet.getString("ref");

        Category category = new Category(categoryRef, categoryName);

        Task task = new Task(
                taskId,
                taskName,
                taskDesc,
                taskDate,
                category,
                taskPriority

        );

        return task;
    }

    @Override
    public List<Task> sortByPriority() {;
        Statement statement = null; //envoyer des requêtes SQL pré-compilées à une base de données
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
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                generateTaskFromResultSet(resultSet);

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
    public void addPriorityToTask(int taskId,Priority priority) {
        try {
            String sql = "UPDATE task set priority = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,priority.name());
            preparedStatement.setInt(2,taskId);
           int i = preparedStatement.executeUpdate();
            if(i ==1 ){
                System.out.println("Update successfully");
            }else {
                System.out.println("Error ");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addCategoryToTask(Category category) {

    }

    @Override
    public List<Task> filterByCategory(Category category) {
        return null;
    }
}
