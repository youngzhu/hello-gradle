package com.youngzy.todo;

import com.youngzy.todo.util.CommandLineInput;
import com.youngzy.todo.util.CommandLineInputHandler;

/**
 * @author youngzy
 * @since 2023-04-04
 */
public class TodoApp {
    public static final char DEFAULT_INPUT = '\u0000';

    public static void main(String[] args) {
        CommandLineInputHandler commandLineInputHandler = new CommandLineInputHandler();
        char command = DEFAULT_INPUT;

        while (CommandLineInput.EXIT.getShortCmd() != command) {
            commandLineInputHandler.printOptions();
            String input = commandLineInputHandler.readInput();
            char[] inputChars = input.length() == 1 ? input.toCharArray() : new char[]{DEFAULT_INPUT};
            command = inputChars[0];
            CommandLineInput commandLineInput = CommandLineInput.getCommandLineInputForInput(command);
            commandLineInputHandler.processInput(commandLineInput);
        }
    }
}
