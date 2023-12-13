package org.titans.services;

import org.titans.entities.ActionType;
import org.titans.entities.Task;
import org.titans.entities.TaskHistoryAction;
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
        String query = "INSERT INTO  task_action_history (task_id, action_change,time_modification,user_name)" +
                "VALUES (?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,taskHistoryAction.getTask_id());
            preparedStatement.setString(2,taskHistoryAction.getActionType().name().toUpperCase());
            preparedStatement.setTimestamp(3,Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(4,taskHistoryAction.getUserName());
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
            String query = "SELECT * FROM task_action_history";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                TaskHistoryAction taskHistoryAction = new TaskHistoryAction();
                taskHistoryAction.setId(resultSet.getInt("id"));
                taskHistoryAction.setTask_id(resultSet.getInt("task_id"));
                taskHistoryAction.setActionType(ActionType.valueOf(resultSet.getString("action_change")));
                taskHistoryAction.setTimeModification(resultSet.getTimestamp("time_modification"));
                taskHistoryAction.setUserName(resultSet.getString("user_name"));
                taskHistoryActions.add(taskHistoryAction);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return taskHistoryActions;
    }
    public TaskHistoryAction createTaskHiistoryObj(int taskId, ActionType actionType, String userName) {
        return new TaskHistoryAction(taskId, actionType, Timestamp.valueOf(LocalDateTime.now()), userName);
    }
}
