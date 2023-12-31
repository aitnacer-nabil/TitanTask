package org.titans.entities;

import org.titans.util.Utils;

import java.sql.Timestamp;

public class TaskHistoryAction {
    String history_id;
    String task_id;
    ActionType actionType ;
    Timestamp timeModification;
    String user_id;


    public TaskHistoryAction( String task_id, ActionType actionType, Timestamp timeModification, String user_id) {
        this.history_id = Utils.GenerateId();
        this.task_id = task_id;
        this.actionType = actionType;
        this.timeModification = timeModification;
        this.user_id = user_id;
    }
    public TaskHistoryAction(  String history_id,String task_id, ActionType actionType, Timestamp timeModification, String user_id) {
        this.history_id = history_id;
        this.task_id = task_id;
        this.actionType = actionType;
        this.timeModification = timeModification;
        this.user_id = user_id;
    }
    public String getHistory_id() {
        return history_id;
    }

    public void setHistory_id(String history_id) {
        this.history_id = history_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "TaskHistoryAction{" +
                "history_id='" + history_id + '\'' +
                ", task_id='" + task_id + '\'' +
                ", actionType=" + actionType +
                ", timeModification=" + timeModification +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
