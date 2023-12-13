package org.titans.entities;

import java.sql.Timestamp;
import java.util.Date;

public class Task {
    private int id ;
    private String name;
    private String description;
    private Date dateCreation;
    private Category category;
    private Priority priority;
    public Task() {
    }
    public Task(int id, String name, String description, Date dateCreation, Category category, Priority priority) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreation = dateCreation;
        this.category = category;
        this.priority = priority;
    }
    public Task(String name, String description, Category category, Priority priority) {

        this.name = name;
        this.description = description;
        this.category = category;
        this.priority = priority;

    }


    public Task(String name, String description, Timestamp timestamp, Priority priority) {
    }

    public Task(String task1, String desc, Timestamp valueOf, Object o, Priority haute) {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public Category getCategory() {
        return category;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateCreation=" + dateCreation +
                ", category=" + category +
                ", priority=" + priority +
                '}';
    }
}
