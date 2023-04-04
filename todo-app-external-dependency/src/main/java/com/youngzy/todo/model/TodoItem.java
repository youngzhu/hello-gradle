package com.youngzy.todo.model;

/**
 * @author youngzy
 * @since 2023-04-04
 */
public class TodoItem implements Comparable<TodoItem> {

    private Long id;
    private String name;
    private boolean completed;

    @Override
    public int compareTo(TodoItem o) {
        return this.id.compareTo(o.id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
