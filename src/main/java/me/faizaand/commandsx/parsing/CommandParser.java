package me.faizaand.commandsx.parsing;

import me.faizaand.commandsx.*;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Reads each method in a class that is annotated by a {@link me.faizaand.commandsx.Command} annotation,
 * and then converts it into a list of {@link me.faizaand.commandsx.ParsedCommand}s.
 *
 * @since 1.0
 */
public class CommandParser {

    // Temporarily store each root command that we parse here.
    // This list is cleared each time the parse method completes execution.
    private List<RootCommand> tempRootCommandList = new ArrayList<>();

    public List<ParsedCommand> parse(Object commandObj) {
        List<ParsedCommand> commands = new ArrayList<>();

        Method[] methods = commandObj.getClass().getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Command.class)) {
                ParsedCommand parsedCommand = parseCommandMethod(method);
                commands.add(parsedCommand);
            }
        }

        tempRootCommandList.clear();
        return commands;
    }

    private ParsedCommand parseCommandMethod(Method method) {
        Command annotation = method.getAnnotation(Command.class);

        // Extract each command identifier here
        String[] identifiers = annotation.identifier().split(" ");

        // We're interested in the root command, because we can use the root
        // command to see which children have not yet been registered.
        Optional<RootCommand> rootCommandOptional = tempRootCommandList.stream()
            .filter(rootCommand -> rootCommand.getName().equals(identifiers[0])).findFirst();

        if (!rootCommandOptional.isPresent()) {
            // Not even the root command was registered yet! Let's create it here.
            RootCommand rootCommand =
                new RootCommand(identifiers[0], annotation.desc(), annotation.longDesc(),
                    annotation.permissions());

            if (countSubCommandsInIdentifier(identifiers) == 0) {
                // This is the root command definition.
                // We can do our root registration magic here.
                ArgumentMap argumentMap = parseArguments(method);
                rootCommand.setArguments(argumentMap);

                return rootCommand;
            }

            // This isn't the root command definition, so we should add our sub commands.
        }

        // TODO Parse the sub commands.
        // Ensure that they are all parsed, perhaps with recursion or something.
        // Also, figure out how you're gonna deal with inline arguments.
        // Perhaps the ParsedCommand object can store the indexes where they're present.

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
