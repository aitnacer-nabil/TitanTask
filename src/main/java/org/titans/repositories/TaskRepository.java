package org.titans.repositories;

import org.titans.dao.impl.TaskDAOImp;
import org.titans.entities.Priority;
import org.titans.entities.Task;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class TaskRepository {

    TaskDAOImp taskDAOImp = new TaskDAOImp();
    List<Task> tasksList;

    public TaskRepository() {
        this.tasksList = taskDAOImp.getAllTasks();

    }

    public void addTaskRepo(Task task) {
        if(taskDAOImp.addTask(task)){
            tasksList.add(task);
        };
    }

    public List<Task> getTasksList() {
        return tasksList;
    }

    public void updateTaskRepo(String taskId, Task task) throws Exception {
        if (taskDAOImp.updateTask(taskId, task)){
          int index = IntStream.range(0,tasksList.size())
                  .filter(i -> tasksList.get(i).getId().equals(taskId))
                  .findFirst()
                  .orElseThrow(Exception::new);
          tasksList.set(index,task);

        }

    }

    public void deleteTaskRepo(String id, String userId) {
        if(taskDAOImp.deleteTask(id, userId)){
            tasksList.stream().filter(task -> task.getId().equals(userId)).findFirst().ifPresent(tasksList::remove);
        }

    }

    public Task getTaskById(String id) {
        return taskDAOImp.getTaskById(id);

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

        return tasksList.stream()
                .filter(task -> task.getCategory().getNom().equals(nameCategory))
                .collect(Collectors.toList());

    }

    public List<Task> filterByPriorityRepo(Priority priority) {

        return tasksList.stream()
                .filter(task -> task.getPriority().equals(priority))
                .collect(Collectors.toList());

    }
    public List<Task> sortCategoryUserRepo(String id) {

        List<Task> tasks = getTaskByUserIdRepo(id);
        List<Task> sortedTasks = tasks.stream()
                .sorted(Comparator.comparing(task -> task.getCategory().getNom()))
                .collect(Collectors.toList());
        return sortedTasks;
    }
    public List<Task> sortPriorityUserRepo(String id) {

        List<Task> tasks = getTaskByUserIdRepo(id);
        List<Task> sortedTasks = tasks.stream()
                .sorted(Comparator.comparing(Task::getPriority))
                .collect(Collectors.toList());
        return sortedTasks;
    }
    public List<Task> sortDateUserRepo(String id) {

        List<Task> tasks = getTaskByUserIdRepo(id);
        List<Task> sortedTasks = tasks.stream()
                .sorted(Comparator.comparing(Task::getDateCreation))
                .collect(Collectors.toList());
        return sortedTasks;
    }
}


