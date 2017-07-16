package me.faizaand.commandsx;

import java.lang.reflect.Method;
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
    private Method commandMethod;

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

        // We're a child of our parent, let's let it know!
        if (this.parent != null) {
            this.parent.getChildren().add(this);
        }
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

    public List<ParsedCommand> getChildren() {
        return children;
    }

    public ParsedCommand getParent() {
        return parent;
    }

    public void setParent(ParsedCommand parent) {
        this.parent = parent;
    }

    public Method getCommandMethod() {
        return commandMethod;
    }

    public void setCommandMethod(Method commandMethod) {
        this.commandMethod = commandMethod;
    }

    @Override public boolean equals(Object obj) {
        return (obj instanceof ParsedCommand) && ((ParsedCommand) obj).name.equals(name);
    }

    @Override public int hashCode() {
        return name.hashCode();
    }

}
