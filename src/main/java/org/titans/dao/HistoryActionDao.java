package org.titans.dao;

import org.titans.entities.TaskHistoryAction;

import java.util.List;

public interface HistoryActionDao {
    void insert(TaskHistoryAction taskHistoryAction);
    List<TaskHistoryAction> getHistory();
}
