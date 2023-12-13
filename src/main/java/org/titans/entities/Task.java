package org.titans.entities;

import org.titans.util.Utils;

import java.sql.Timestamp;
import java.util.Date;

public class Task {
    private String id ;
    private String name;
    private String description;
    private Date dateCreation;
    private Category category;
    private Priority priority;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    private String user_id ;


    public Task(String name, String description, Date dateCreation, Category category, Priority priority, String user_id) {
       this.id = Utils.GenerateId();
        this.name = name;
        this.description = description;
        this.dateCreation = dateCreation;
        this.category = category;
        this.priority = priority;
        this.user_id = user_id;
    }







    public String getId() {
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

    public void setId(String id) {
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
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
