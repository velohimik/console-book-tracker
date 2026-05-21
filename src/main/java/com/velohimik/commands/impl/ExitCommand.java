package com.velohimik.commands.impl;

import com.velohimik.commands.MenuCommand;
import com.velohimik.exceptions.ExitApplicationException;

public class ExitCommand implements MenuCommand {

    @Override
    public String execute() {
        throw new ExitApplicationException("Goodbye!");
    }

    @Override
    public String getDescription() {
        return "Exit;";
    }
}
