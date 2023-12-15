package org.titans.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.titans.entities.*;
import org.titans.dao.TaskDAO;
import org.titans.util.ConnectionDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImp implements TaskDAO {

    HistoryActionDaoImp historyActionDaoImp;


    Connection connection;

    public TaskDAOImp() {
        this.connection = ConnectionDB.getConnectionDB();
        historyActionDaoImp = new HistoryActionDaoImp();
    }

    @Override
    public boolean addTask(Task task) {
        boolean isAdded = false;
        try {
            String query = "INSERT INTO task (task_id, name,description,date_creation,priority,ref_category,ref_user) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, task.getId());
            preparedStatement.setString(2, task.getName());
            preparedStatement.setString(3, task.getDescription());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(5, task.getPriority().name());
            preparedStatement.setString(6, task.getCategory().getId());
            preparedStatement.setString(7, task.getUser_id());

            int i = preparedStatement.executeUpdate();
            if (i == 1) {

                TaskHistoryAction taskHistoryAction = historyActionDaoImp.createTaskHiistoryObj(
                        task.getId(),
                        ActionType.CREATE,
                        Timestamp.valueOf(LocalDateTime.now()),
                        task.getUser_id()
                );
                historyActionDaoImp.insert(taskHistoryAction);


                System.out.println("Add successfully");
                return true;
            } else {
                System.out.println("Not Add successfully");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    @Override
    public boolean updateTask(String taskId, Task task) {

        try {

            String query = "update task set  name = ? ,description = ? , date_creation = CURRENT_TIMESTAMP, priority = ?, ref_category = ? , ref_user = ? where  task_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setString(3, task.getPriority().name());
            preparedStatement.setString(4, task.getCategory().getId());
            preparedStatement.setString(5, task.getUser_id());
            preparedStatement.setString(6, taskId);

            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                TaskHistoryAction taskHistoryAction = historyActionDaoImp.createTaskHiistoryObj(
                        task.getId(),
                        ActionType.UPDATE,
                        Timestamp.valueOf(LocalDateTime.now()),
                        taskId
                );
                historyActionDaoImp.insert(taskHistoryAction);
                preparedStatement.close();
                return true;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean deleteTask(String id, String userId) {

        String deleteQuery = "DELETE FROM task WHERE task_id= ?";
        if (connection == null) {
            System.out.println("Couldn't get connection to the database");
            return false;
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, id);

            int rowAffected = preparedStatement.executeUpdate();

            if (rowAffected == 1) {
                TaskHistoryAction taskHistoryAction = historyActionDaoImp.createTaskHiistoryObj(
                        id,
                        ActionType.DELETE,
                        Timestamp.valueOf(LocalDateTime.now()),
                        userId
                );
                historyActionDaoImp.insert(taskHistoryAction);

                System.out.println("Task deleted successfully");
                return true;
            } else {
                System.out.println("Task not Found");
            }

        } catch (SQLException se) {
            se.printStackTrace();

        }
        return false;
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();

        try {
            String getAllQuery = "SELECT task_id,name,description,date_creation,priority,ref_category,ref_user , category.name_category,user.user_name FROM task LEFT JOIN user ON task.ref_user = user.user_id join category on task.ref_category = category.id_category";
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
    public Task getTaskById(String id) {
        Task task = null;

        try {
            String query = "SELECT task.*, category.* FROM task LEFT JOIN category ON task.ref_category = category.id_category WHERE task_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                task = generateTaskFromResultSet(resultSet);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return task;
    }

    @Override
    public List<Task> getTaskByUserId(String userId) {
        List<Task> tasksList = new ArrayList<>();
        Task task = null;

        try {
            String query = "SELECT task.*, category.* FROM task LEFT JOIN category ON task.ref_category = category.id_category WHERE ref_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                task = generateTaskFromResultSet(resultSet);
                tasksList.add(task);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
            //TODO java.sql.SQLException: Column 'name_category' not found.
        }
        return tasksList;
    }

//    @Override
//    public List<Task> sortByDate() {
//
//        Statement statement = null; //envoyer des requêtes SQL pré-compilées à une base de données
//        ResultSet resultSet = null; //stocker les résultats d'une requête SQL.
//        List<Task> tasks = new ArrayList<>();
//        try {
//            String sql = "SELECT * FROM task LEFT JOIN category ON task.ref_category = category.ref ORDER BY date_creation";
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                Task task = generateTaskFromResultSet(resultSet);
//                tasks.add(task);
//
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return tasks;
//    }

    private Task generateTaskFromResultSet(ResultSet resultSet) throws SQLException {
        String taskId = resultSet.getString("task_id");
        String taskName = resultSet.getString("name");
        String taskDesc = resultSet.getString("description");
        Timestamp taskDate = resultSet.getTimestamp("date_creation");
        Priority taskPriority = Priority.valueOf(resultSet.getString("priority").toUpperCase());
        String categoryName = resultSet.getString("name_category");
        String categoryRef = resultSet.getString("ref_category");
        String taskUserId = resultSet.getString("ref_user");
        Category category = new Category(categoryRef, categoryName);



        String user_name = resultSet.getString("user_name");

        Task task = new Task(taskId, taskName, taskDesc, taskDate, category, taskPriority, taskUserId,user_name);


        return task;
    }


    // @Override

//    public List<Task> sortByPriority() {
//
//        Statement statement = null; //envoyer des requêtes SQL pré-compilées à une base de données
//        ResultSet resultSet = null; //stocker les résultats d'une requête SQL.
//        List<Task> tasks = new ArrayList<>();
//        try {
//            String sql = "SELECT * FROM task LEFT JOIN category ON task.ref_category = category.ref ORDER BY \n"
//                    + "case priority \n"
//                    + "when \"haute\" then 1\n"
//                    + "when \"moyenne\" then 2\n"
//                    + "when \"basse\" then 3\n"
//                    + "else 4\n"
//                    + "end;";
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                Task task = generateTaskFromResultSet(resultSet);
//                tasks.add(task);
//
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return tasks;
//    }

//    public List<Task> sortByPriority() {;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        List<Task> tasks = new ArrayList<>();
//        try {
//            String sql = "SELECT * FROM task LEFT JOIN category ON task.ref_category = category.ref ORDER BY \n" +
//                    "case priority \n" +
//                    "when \"haute\" then 1\n" +
//                    "when \"moyenne\" then 2\n" +
//                    "when \"basse\" then 3\n" +
//                    "else 4\n" +
//                    "end;";
//
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                Task task = generateTaskFromResultSet(resultSet);
//                tasks.add(task);
//
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return tasks;
//    }

//    @Override
//    public List<Task> sortByCategory() {
//        Statement statement = null;
//        ResultSet resultSet = null;
//        List<Task> tasks = new ArrayList<>();
//        try {
//            String sqlSort = "SELECT * FROM task LEFT JOIN category ON task.ref_category = category.ref ORDER BY name_category";
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(sqlSort);
//            while (resultSet.next()) {
//                Task task = generateTaskFromResultSet(resultSet);
//                tasks.add(task);
//
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return tasks;
//    }

//    @Override
//    public void addPriorityToTask(int taskId, Priority priority) {
//        try {
//            String sql = "UPDATE task set priority = ? WHERE id = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, priority.name());
//            preparedStatement.setInt(2, taskId);
//            int i = preparedStatement.executeUpdate();
//            if (i == 1) {
//
//                TaskHistoryAction taskHistoryAction = historyActionDaoImp.createTaskHiistoryObj(
//                        15,
//                        ActionType.UPDATE,
//                        "Nabil"
//                );
//                historyActionDaoImp.insert(taskHistoryAction);
//
//                System.out.println("Update successfully");
//            } else {
//                System.out.println("Error ");
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Override
//    public void addCategoryToTask(int id,String ref_category) {
//              try {
//            String sql = "UPDATE task set ref_category = ? WHERE id = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, ref_category);
//            preparedStatement.setInt(2, id);
//            int i = preparedStatement.executeUpdate();
//            if (i == 1) {
//                System.out.println("Update successfully");
//            } else {
//                System.out.println("Error ");
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//    @Override
//    public List<Task> filterByCategory(String category_name) {
//         List<Task> tasks = new ArrayList<>();
//        try {
//            String sql = "SELECT id,name,description,date_creation,priority,category.name_category,category.ref FROM task JOIN  category ON task.ref_category=category.ref WHERE category.name_category=?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            preparedStatement.setString(1, category_name);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                Task task = generateTaskFromResultSet(resultSet);
//                tasks.add(task);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return tasks;
//    }

}
