package me.faizaand.commandsx;

/**
 * Represents a command that has been registered in the {@link CommandManager}.
 * This is mostly for internal use.
 *
 * @since 1.0
 */
public class RegisteredCommand {

    private ParsedCommand command;
    private Object owner;

    public RegisteredCommand(ParsedCommand command, Object owner) {
        this.command = command;
        this.owner = owner;
    }

    public ParsedCommand getCommand() {
        return command;
    }

    public Object getOwner() {
        return owner;
    }

}
