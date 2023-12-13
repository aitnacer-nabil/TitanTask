package org.titans.services;

import org.titans.entities.Category;
import org.titans.entities.Priority;
import org.titans.entities.Task;

import java.util.List;

public interface TaskDAO {
    void addTask(Task task);
    Task updateTask(Task t,Integer id);
    void deleteTask(Integer id );
    List<Task> getAllTasks();
    List<Task> sortByDate();

    List<Task> sortByPriority();
    List<Task> sortByCategory();
    void addPriorityToTask(int taskId,Priority priority);
    void addCategoryToTask(int id,String ref_category);

    List <Task> filterByCategory(String category_name);


}
