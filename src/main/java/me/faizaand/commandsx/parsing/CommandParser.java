package me.faizaand.commandsx.parsing;

import me.faizaand.commandsx.*;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads each method in a class that is annotated by a {@link me.faizaand.commandsx.Command} annotation,
 * and then converts it into a list of {@link me.faizaand.commandsx.ParsedCommand}s.
 *
 * @since 1.0
 */
public class CommandParser {

    public List<ParsedCommand> parse(Object commandObj) {
        List<ParsedCommand> commands = new ArrayList<>();

        Method[] methods = commandObj.getClass().getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Command.class)) {
                ParsedCommand parsedCommand = parseCommandMethod(method);
                commands.add(parsedCommand);
            }
        }

        return commands;
    }

    private ParsedCommand parseCommandMethod(Method method) {
        Command annotation = method.getAnnotation(Command.class);

        // Extract each command identifier here
        String[] identifiers = annotation.identifier().split(" ");

        ParsedCommand currentParent = null;
        for (int i = 0; i < identifiers.length; i++) {
            String identifier = identifiers[i];
            if (isInlineArgumentIdentifier(identifier)) {
                continue; // for now
            }

            if (i == 0) {
                currentParent =
                    new RootCommand(identifier, annotation.desc(), annotation.longDesc(),
                        annotation.permissions());
            }
            currentParent = new ParsedCommand(identifier, annotation.desc(), annotation.longDesc(),
                annotation.permissions(), currentParent);
            if (countSubCommandsInIdentifier(identifiers) == i) {
                // This is our subcommand's definition.
                // We can do our registration magic here.
                ArgumentMap argumentMap = parseArguments(method);
                currentParent.setArguments(argumentMap);

                return currentParent;
            }

        }

        return null;
    }

    private ArgumentMap parseArguments(Method method) {
        ArgumentMap map = new ArgumentMap();
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            if (parameter.isAnnotationPresent(Arg.class)) {
                Arg annotation = parameter.getAnnotation(Arg.class);

                Argument argument =
                    new Argument(annotation.name(), annotation.desc(), annotation.def(),
                        annotation.validators());
                map.addArgument(parameter.getType(), argument);
            }
        }

        return map;
    }

    /**
     * Counts the sub commands that are present in an array of command identifier.
     * This will exclude the root command (i.e. one is subtracted from the count).
     * This is useful to filter out the inline arguments.
     *
     * @param identifiers The array of identifiers, split at the " " space.
     * @return The count of sub commands.
     */
    private int countSubCommandsInIdentifier(String[] identifiers) {
        int count = 0;

        // Inline arguments are defined with {curly brackets}.
        // These are counted as arguments and not sub commands.
        // So, we'll filter them out here.
        for (String identifier : identifiers) {
            if (!isInlineArgumentIdentifier(identifier)) {
                count++;
            }
        }

        // We subtract one here to disregard the root command.
        return count - 1;
    }

    /**
     * Checks whether an identifier is a definition for an inline argument.
     * Inline arguments are put between {curly brackets}, and so we check for this here
     * using the regular expression <code>\{(.*?}</code>.
     *
     * @param identifier The identifier to check.
     * @return true if it's an inline argument identifier, and false if it's not.
     */
    private boolean isInlineArgumentIdentifier(String identifier) {
        return identifier.matches("\\{(.*?)}");
    }

}
