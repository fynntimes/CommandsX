package me.faizaand.commandsx;

import java.util.List;

/**
 * A root command is the first in the series of commands defined in a command identifier.
 * This is different from a {@link ParsedCommand} because it is what is registered to the server's
 * command map and in the help menu.
 *
 * @since 1.0
 */
public class RootCommand extends ParsedCommand {

    // We're only using this class for identification purposes.
    // As of now, the only difference is that the parent command is always null.

    public RootCommand(String name, String description, String longDescription,
        String[] permissions, ArgumentMap arguments, List<ParsedCommand> children) {
        super(name, description, longDescription, permissions, arguments, null, children);
    }

    public RootCommand(String name, String description, String longDescription,
        String[] permissions) {
        super(name, description, longDescription, permissions, null);
    }

}
