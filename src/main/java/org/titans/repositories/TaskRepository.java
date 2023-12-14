package org.titans.repositories;

import org.titans.dao.TaskDAO;
import org.titans.dao.impl.TaskDAOImp;
import org.titans.entities.Priority;
import org.titans.entities.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class TaskRepository {

    TaskDAOImp taskDAOImp = new TaskDAOImp();
    List<Task> tasksList;

    public TaskRepository() {
        this.tasksList = taskDAOImp.getAllTasks();

    }

    public void addTaskRepo(Task task) {
        taskDAOImp.addTask(task);
    }

    public List<Task> getAllTasksRepo() {
        tasksList = taskDAOImp.getAllTasks();
        return tasksList;

    }

    public Task updateTaskRepo(String taskId, Task task) {
        taskDAOImp.updateTask(taskId, task);
        return task;
    }

    public void deleteTaskRepo(String id, String userId) {
        taskDAOImp.deleteTask(id, userId);
        tasksList.stream().filter(task -> task.getId().equals(userId)).findFirst().ifPresent(tasksList::remove);
    }

    public Task getTaskById(String id) {
        Task task = taskDAOImp.getTaskById(id);
        return task;
    }

    public List<Task> getTaskByUserIdRepo(String userId) {
        return taskDAOImp.getTaskByUserId(userId);

    }

    public List<Task> sortByPriorityRepo() {
        List<Task> tasksSorted = tasksList.stream()
                .sorted(Comparator.comparing(Task::getPriority))
                .collect(Collectors.toList());
        return tasksSorted;
    }

    public List<Task> sortByDateRepo() {

        List<Task> tasksSorted = tasksList.stream()
                .sorted(Comparator.comparing(Task::getDateCreation))
                .collect(Collectors.toList());
        return tasksSorted;
    }

    public List<Task> sortByCategoryRepo() {

        List<Task> tasksSorted = tasksList.stream()
                .sorted(Comparator.comparing(task -> task.getCategory().getNom()))
                .collect(Collectors.toList());
        return tasksSorted;
    }

    public List<Task> filterByCategoryRepo(String nameCategory) {

        List<Task> tasksFiltred = tasksList.stream()
                .filter(task -> task.getCategory().getNom().equals(nameCategory))
                .collect(Collectors.toList());
        return tasksFiltred;
    }

    public List<Task> filterByPriorityRepo(Priority priority) {

        List<Task> tasksFiltred = tasksList.stream()
                .filter(task -> task.getPriority().equals(priority))
                .collect(Collectors.toList());
        return tasksFiltred;
    }
}


