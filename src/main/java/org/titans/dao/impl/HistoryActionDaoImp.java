package org.titans.dao.impl;

import org.titans.entities.ActionType;
import org.titans.entities.TaskHistoryAction;
import org.titans.dao.HistoryActionDao;
import org.titans.util.ConnectionDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoryActionDaoImp implements HistoryActionDao {
    Connection connection;

    public HistoryActionDaoImp() {
        connection = ConnectionDB.getConnectionDB();
    }

    @Override
    public void insert(TaskHistoryAction taskHistoryAction) {

        try {
        String query = "INSERT INTO  task_action_history (history_id,task_id, action_change,time_modification,user_id)" +
                "VALUES (?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,taskHistoryAction.getHistory_id());
            preparedStatement.setString(2,taskHistoryAction.getTask_id());
            preparedStatement.setString(3,taskHistoryAction.getActionType().name().toUpperCase());
            preparedStatement.setTimestamp(4,Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(5,taskHistoryAction.getUser_id());
            int i = preparedStatement.executeUpdate();
            if(i == 1){
                System.out.println("Insert Succefully history");
            } else {
                System.out.println("Not Insert History");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<TaskHistoryAction> getHistory() {
        List<TaskHistoryAction> taskHistoryActions = new ArrayList<>();
        try {
            String query = "SELECT * FROM task_action_history w";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                TaskHistoryAction taskHistoryAction = new TaskHistoryAction(
                resultSet.getString("history_id"),
                resultSet.getString("task_id"),
                ActionType.valueOf(resultSet.getString("action_change")),
                resultSet.getTimestamp("time_modification"),
                resultSet.getString(""));
                taskHistoryActions.add(taskHistoryAction);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return taskHistoryActions;
    }

    @Override
    public boolean deleteHistory() {
        return false;
    }

    public TaskHistoryAction createTaskHiistoryObj(String taskId, ActionType actionType,Timestamp timestamp, String userName) {
        return new TaskHistoryAction(taskId,actionType,timestamp,userName);
    }
}
