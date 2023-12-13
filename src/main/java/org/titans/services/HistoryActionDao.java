package org.titans.services;

import org.titans.entities.TaskHistoryAction;

import java.util.List;

public interface HistoryActionDao {
    void insert(TaskHistoryAction taskHistoryAction);
    List<TaskHistoryAction> getHistory();
}
