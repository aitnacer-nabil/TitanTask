package org.titans.services;

import org.titans.entities.Category;
import org.titans.entities.Priority;
import org.titans.entities.Task;
import org.titans.util.ConnectionDB;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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

    }

    @Override
    public List<Task> getAllTasks() {
        return null;
    }

    @Override
    public List<Task> sortByDate() {
        return null;
    }

    @Override
    public List<Task> sortByPriority() {
        return null;
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
