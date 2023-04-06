package com.youngzy.todo.util;

import com.youngzy.todo.model.TodoItem;
import com.youngzy.todo.repository.InMemoryRepository;
import com.youngzy.todo.repository.Repository;

import java.util.Collection;

/**
 * @author youngzy
 * @since 2023-04-04
 */
public class CommandLineInputHandler {
    private Repository toDoRepository = new InMemoryRepository();

    public void printOptions() {
        System.out.println("\n--- To Do Application ---");
        System.out.println("Please make a choice:");
        System.out.println("(a)ll items");
        System.out.println("(f)ind a specific item");
        System.out.println("(i)nsert a new item");
        System.out.println("(u)pdate an existing item");
        System.out.println("(d)elete an existing item");
        System.out.println("(e)xit");
    }

    public String readInput() {
        return System.console().readLine("> ");
    }

    public void processInput(CommandLineInput input) {
        if (input == null) {
            handleUnknownInput();
        } else {
            switch (input) {
                case FIND_ALL:
                    printAllToDoItems();
                    break;
                case FIND_BY_ID:
                    printToDoItem();
                    break;
                case INSERT:
                    insertToDoItem();
                    break;
                case UPDATE:
                    updateToDoItem();
                    break;
                case DELETE:
                    deleteToDoItem();
                    break;
                case EXIT:
                    break;
                default:
                    handleUnknownInput();
            }
        }
    }

    private Long askForItemId() {
        System.out.println("Please enter the item ID:");
        String input = readInput();
        return Long.parseLong(input);
    }

    private TodoItem askForNewToDoAction() {
        TodoItem toDoItem = new TodoItem();
        System.out.println("Please enter the name of the item:");
        toDoItem.setName(readInput());
        return toDoItem;
    }

    private void printAllToDoItems() {
        Collection<TodoItem> toDoItems = toDoRepository.findAll();

        if (toDoItems.isEmpty()) {
            System.out.println("Nothing to do. Go relax!");
        } else {
            for (TodoItem toDoItem : toDoItems) {
                System.out.println(toDoItem);
            }
        }
    }

    private void printToDoItem() {
        TodoItem toDoItem = findToDoItem();

        if (toDoItem != null) {
            System.out.println(toDoItem);
        }
    }

    private TodoItem findToDoItem() {
        Long id = askForItemId();
        TodoItem toDoItem = toDoRepository.findById(id);

        if (toDoItem == null) {
            System.err.println("To do item with ID " + id + " could not be found.");
        }

        return toDoItem;
    }

    private void insertToDoItem() {
        TodoItem toDoItem = askForNewToDoAction();
        Long id = toDoRepository.insert(toDoItem);
        System.out.println("Successfully inserted to do item with ID " + id + ".");
    }

    private void updateToDoItem() {
        TodoItem toDoItem = findToDoItem();

        if (toDoItem != null) {
            System.out.println(toDoItem);
            System.out.println("Please enter the name of the item:");
            toDoItem.setName(readInput());
            System.out.println("Please enter the done status the item:");
            toDoItem.setCompleted(Boolean.parseBoolean(readInput()));
            toDoRepository.update(toDoItem);
            System.out.println("Successfully updated to do item with ID " + toDoItem.getId() + ".");
        }
    }

    private void deleteToDoItem() {
        TodoItem toDoItem = findToDoItem();

        if (toDoItem != null) {
            toDoRepository.delete(toDoItem);
            System.out.println("Successfully deleted to do item with ID " + toDoItem.getId() + ".");
        }
    }

    private void handleUnknownInput() {
        System.out.println("Please select a valid option!");
    }
}
