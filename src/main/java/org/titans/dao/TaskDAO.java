package org.titans.dao;

import org.titans.entities.Task;

import java.util.List;

public interface TaskDAO {
    boolean addTask(Task task);
    boolean updateTask(String taskId,Task t);
    boolean deleteTask(String id ,String userId);
    List<Task> getAllTasks();
    Task getTaskById(String id);
    List<Task> getTaskByUserId (String userId);
//    List<Task> sortByDate();
//
//    List<Task> sortByPriority();
//    List<Task> sortByCategory();
//    void addPriorityToTask(int taskId,Priority priority);
//    void addCategoryToTask(int id,String ref_category);
//
//    List <Task> filterByCategory(String category_name);


}
