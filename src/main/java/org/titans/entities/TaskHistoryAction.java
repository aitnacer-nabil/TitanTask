package org.titans.entities;

import java.sql.Timestamp;

public class TaskHistoryAction {
    int id;
    int task_id;
    ActionType actionType ;

    public int getId() {
        return id;
    }

    public TaskHistoryAction( int task_id, ActionType actionType, Timestamp timeModification, String userName) {

        this.task_id = task_id;
        this.actionType = actionType;
        this.timeModification = timeModification;
        this.userName = userName;
    }

    public TaskHistoryAction() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Timestamp getTimeModification() {
        return timeModification;
    }

    public void setTimeModification(Timestamp timeModification) {
        this.timeModification = timeModification;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    Timestamp timeModification;
    String userName;

}
