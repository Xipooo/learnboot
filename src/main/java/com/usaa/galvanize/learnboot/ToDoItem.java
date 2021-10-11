package com.usaa.galvanize.learnboot;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ToDoItem {
    @Id
    private long id;
    private String description;

    public String getDescription() {
        return this.description;
    }

    public ToDoItem() {}

    public ToDoItem(long id, String description) {
        this.id = id;
        this.description = description;
    }
}
