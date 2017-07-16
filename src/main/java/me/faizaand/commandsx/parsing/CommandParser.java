package me.faizaand.commandsx.parsing;

import me.faizaand.commandsx.*;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * Reads each method in a class that is annotated by a {@link me.faizaand.commandsx.Command} annotation,
 * and then converts it into a list of {@link me.faizaand.commandsx.ParsedCommand}s.
 *
 * @since 1.0
 */
public class CommandParser {

    /**
     * Looks through each method in a class for {@link Command} annotations. If they're present,
     * they're registered as commands automatically.
     *
     * @param commandObj The object containing the command methods.
     * @return A list containing the {@link ParsedCommand}s.
     */
    public List<ParsedCommand> parse(Object commandObj) {
        List<ParsedCommand> commands = new ArrayList<>();

        Method[] methods = commandObj.getClass().getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Command.class)) {
                List<ParsedCommand> parsedCommand = parseCommandMethod(method);
                commands.addAll(parsedCommand);
            }
        }

        return commands;
    }

    private List<ParsedCommand> parseCommandMethod(Method method) {
        method.setAccessible(true);

        Command annotation = method.getAnnotation(Command.class);
        List<ParsedCommand> commands = new ArrayList<>();

        // Extract each command identifier here
        String[] identifiers = annotation.identifier().split(" ");

        ParsedCommand currentParent = null;

        for (int i = 0; i < identifiers.length; i++) {
            String identifier = identifiers[i];

            if (i == 0) {
                currentParent =
                    new RootCommand(identifier, annotation.desc(), annotation.longDesc(),
                        annotation.permissions());
                commands.add(currentParent);
            } else {
                currentParent =
                    new ParsedCommand(identifier, annotation.desc(), annotation.longDesc(),
                        annotation.permissions(), currentParent);
                commands.add(currentParent);
            }

            if ((identifiers.length - 1) == i) {
                // This is our subcommand's definition.
                // We can do our registration magic here.
                currentParent.setCommandMethod(method);

                ArgumentMap argumentMap = parseArguments(method);
                currentParent.setArguments(argumentMap);
            }

        }

        return commands;
    }

    private ArgumentMap parseArguments(Method method) {
        ArgumentMap map = new ArgumentMap();
        Parameter[] parameters = method.getParameters();

        int index = 0;
        for (Parameter parameter : parameters) {
            if (parameter.isAnnotationPresent(Arg.class)) {
                Arg annotation = parameter.getAnnotation(Arg.class);

                Argument argument =
                    new Argument(annotation.name(), annotation.desc(), annotation.def(),
                        annotation.validators());
                map.addArgument(parameter.getType(), argument);
            } else if (parameter.isAnnotationPresent(Flag.class)) {
                Flag annotation = parameter.getAnnotation(Flag.class);

                Argument argument =
                    new Argument(String.valueOf(annotation.name()), annotation.desc(), "false",
                        new String[] {});
                map.addArgument(Boolean.class, argument);
            } else if (parameter.isAnnotationPresent(InlineArg.class)) {
                InlineArg annotation = parameter.getAnnotation(InlineArg.class);

                InlineArgument argument = new InlineArgument(annotation.name(), annotation.desc(),
                    annotation.validators());
                argument.setIndex(index);
                map.addArgument(parameter.getType(), argument);
            }

            index++;
        }

        return map;
    }

}
