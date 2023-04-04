package com.youngzy.todo.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author youngzy
 * @since 2023-04-04
 */
public enum CommandLineInput {
    FIND_ALL('a'),
    FIND_BY_ID('f'),
    INSERT('i'),
    UPDATE('u'),
    DELETE('d'),
    EXIT('e');

    private final static Map<Character, CommandLineInput> INPUTS;

    static {
        INPUTS = new HashMap<Character, CommandLineInput>();

        for (CommandLineInput input : values()) {
            INPUTS.put(input.getShortCmd(), input);
        }
    }

    private final char shortCmd;

    private CommandLineInput(char shortCmd) {
        this.shortCmd = shortCmd;
    }

    public char getShortCmd() {
        return shortCmd;
    }

    public static CommandLineInput getCommandLineInputForInput(char input) {
        return INPUTS.get(input);
    }
}
