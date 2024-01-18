package entities;

import java.util.ArrayList;
import java.util.List;

public class ListOfCommands {

    private List<Command> commands;

    public ListOfCommands() {
        this.commands = new ArrayList<>();
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void executeCommands() {
        for (Command command : commands) {
            command.execute();
        }
        commands.clear();
    }
    public void clearCommands() {
        commands.clear();
    }
}
