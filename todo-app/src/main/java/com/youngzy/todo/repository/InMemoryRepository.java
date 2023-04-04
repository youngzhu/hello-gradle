package com.youngzy.todo.repository;

import com.youngzy.todo.model.TodoItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author youngzy
 * @since 2023-04-04
 */
public class InMemoryRepository implements Repository {
    private AtomicLong currentId = new AtomicLong();
    private ConcurrentMap<Long, TodoItem> todoList = new ConcurrentHashMap<>();

    @Override
    public List<TodoItem> findAll() {
        List<TodoItem> todoItems = new ArrayList<>(todoList.values());
        Collections.sort(todoItems);
        return todoItems;
    }

    @Override
    public TodoItem findById(Long id) {
        return todoList.get(id);
    }

    @Override
    public Long insert(TodoItem todoItem) {
        Long id = currentId.incrementAndGet();
        todoItem.setId(id);
        todoList.putIfAbsent(id, todoItem);
        return id;
    }

    @Override
    public void update(TodoItem todoItem) {
        todoList.replace(todoItem.getId(), todoItem);
    }

    @Override
    public void delete(TodoItem todoItem) {
        todoList.remove(todoItem.getId());
    }
}
