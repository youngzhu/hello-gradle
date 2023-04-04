package com.youngzy.todo.repository;

import com.youngzy.todo.model.TodoItem;

import java.util.List;

/**
 * @author youngzy
 * @since 2023-04-04
 */
public interface Repository {
    List<TodoItem> findAll();
    TodoItem findById(Long id);
    Long insert(TodoItem todoItem);
    void update(TodoItem todoItem);
    void delete(TodoItem todoItem);
}

