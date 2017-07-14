package me.faizaand.commandsx;

import java.util.ArrayList;
import java.util.List;

/**
 * A sub command is a child of either a {@link RootCommand} or another sub command.
 *
 * @since 1.0
 */
public class ParsedCommand {

    private String name;
    private String description;
    private String longDescription;
    private String[] permissions;
    private ArgumentMap arguments;
    private ParsedCommand parent;
    private List<ParsedCommand> children;

    public ParsedCommand(String name, String description, String longDescription,
        String[] permissions, ArgumentMap arguments, ParsedCommand parent,
        List<ParsedCommand> children) {
        this.name = name;
        this.description = description;
        this.longDescription = longDescription;
        this.permissions = permissions;
        this.arguments = arguments;
        this.parent = parent;
        this.children = children;
    }

    /**
     * Initializes a sub command, but leaves the argument map and children list empty.
     */
    public ParsedCommand(String name, String description, String longDescription,
        String[] permissions, ParsedCommand parent) {
        this.name = name;
        this.description = description;
        this.longDescription = longDescription;
        this.permissions = permissions;
        this.arguments = new ArgumentMap();
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public ArgumentMap getArguments() {
        return arguments;
    }

    public void setArguments(ArgumentMap arguments) {
        this.arguments = arguments;
    }

    public ParsedCommand getParent() {
        return parent;
    }

    public void setParent(ParsedCommand parent) {
        this.parent = parent;
    }

    public List<ParsedCommand> getChildren() {
        return children;
    }

}
