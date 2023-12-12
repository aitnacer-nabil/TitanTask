package org.titans.services;

import org.titans.entities.Category;
import org.titans.entities.Priority;
import org.titans.entities.Task;

import java.util.List;

public class TaskDAOImp implements TaskDAO {
    @Override
    public Task addTask() {
        return null;
    }

    @Override
    public Task updateTask(Task t, Integer id) {
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
