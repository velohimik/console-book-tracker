package com.velohimik.command.impl;

import com.velohimik.command.MenuCommand;

public class ExitCommand implements MenuCommand {

    private static final String EXIT = "Exit;";

    @Override
    public void execute() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return EXIT;
    }
}
