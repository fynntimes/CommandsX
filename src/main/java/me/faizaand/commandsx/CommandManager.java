package me.faizaand.commandsx;

import me.faizaand.commandsx.handlers.BooleanHandler;
import me.faizaand.commandsx.handlers.Handler;
import me.faizaand.commandsx.handlers.HandlerException;
import me.faizaand.commandsx.handlers.StringHandler;
import me.faizaand.commandsx.handlers.number.*;
import me.faizaand.commandsx.parsing.CommandParser;
import me.faizaand.commandsx.providers.*;
import me.faizaand.commandsx.validators.Validator;
import me.faizaand.commandsx.validators.ValidatorException;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Manages the registration and execution of CommandsX commands.
 *
 * @since 1.0
 */
public class CommandManager {

    private List<RegisteredCommand> commands = new ArrayList<>();

    private BidiMap<Class<?>, Handler> handlerMap = new DualHashBidiMap<>();
    private HashMap<String, Validator> validatorMap = new HashMap<>();
    private CommandParser parser = new CommandParser();

    private FormatProvider formatProvider = new DefaultFormatProvider();
    private LangProvider langProvider = new DefaultLangProvider();
    private PermissionProvider permissionProvider = new DefaultPermissionProvider();

    public CommandManager() {
        registerDefaultHandlers();
    }

    // ---------------
    //   Handlers
    // ---------------

    /**
     * Register a {@link Handler} for a type.
     *
     * @param type    The type to register this for.
     * @param handler The handler.
     * @throws IllegalArgumentException if the handler for this type is already registered
     */
    public void registerHandler(Class<?> type, Handler handler) {
        if (getHandlerForType(type).isPresent()) {
            throw new IllegalArgumentException(
                "handler for type " + type.getName() + " is already registered");
        }
        handlerMap.put(type, handler);
    }

    public Optional<Handler> getHandlerForType(Class<?> type) {
        return Optional.ofNullable(handlerMap.get(type));
    }

    public Optional<Class<?>> getTypeForHandler(Handler handler) {
        return Optional.ofNullable(handlerMap.getKey(handler));
    }

    private void registerDefaultHandlers() {
        registerHandler(Boolean.class, new BooleanHandler());
        registerHandler(String.class, new StringHandler());
        registerHandler(Byte.class, new ByteHandler());
        registerHandler(Double.class, new DoubleHandler());
        registerHandler(Float.class, new FloatHandler());
        registerHandler(Integer.class, new IntHandler());
        registerHandler(Long.class, new LongHandler());
        registerHandler(Short.class, new ShortHandler());
    }

    // ---------------
    //   Validators
    // ---------------

    /**
     * Register a {@link Validator}.
     *
     * @param name      The validator's identifier.
     * @param validator The validator.
     */
    public void registerValidator(String name, Validator validator) {
        validatorMap.put(name, validator);
    }

    public Optional<Validator> getValidatorByName(String name) {
        return Optional.ofNullable(validatorMap.get(name));
    }

    // ---------------
    //   Providers
    // ---------------

    public FormatProvider getFormatProvider() {
        return formatProvider;
    }

    public LangProvider getLangProvider() {
        return langProvider;
    }

    public PermissionProvider getPermissionProvider() {
        return permissionProvider;
    }

    // ---------------
    //   Commands
    // ---------------

    public void register(Object obj) {
        List<ParsedCommand> parsedCommands = parser.parse(obj);
        for (ParsedCommand parsedCommand : parsedCommands) {
            commands.add(new RegisteredCommand(parsedCommand, obj));
        }
    }

    public void runCommand(Object sender, String input) {
        String[] parts = input.split(" ");

        // Iterate through it backwards until we find where the command starts
        for (int i = (parts.length - 1); i >= 0; i--) {
            if (getCommandByName(parts[i]).isPresent()) {
                // This is a command
                RegisteredCommand registeredCommand = getCommandByName(parts[i]).get();
                ParsedCommand command = registeredCommand.getCommand();

                String[] args = getArguments(parts, i);
                List<Object> castedArgs = new ArrayList<>();

                int index = 0;
                for (Map.Entry<Class<?>, Argument> classArgumentEntry : command.getArguments()
                    .getMap().entrySet()) {
                    Class<?> type = classArgumentEntry.getKey();
                    Argument argument = classArgumentEntry.getValue();

                    String value;
                    if (index == args.length) {
                        // We've reached the max index, which means there are no
                        // more arguments to use. So, we'll have to use the default values.
                        value = argument.getDefaultValue();
                    } else {
                        value = args[index];
                    }

                    // TODO WAHHH INLINE ARGUMENTS AREN'T WORKING
                    // Find a new way to register, keep track of, and set them.
                    // Remembre the @InlineArg, InlineArgument, CommandParser, and CommandManager classes

                    if (argument instanceof InlineArgument) {
                        value = processInlineArgument(type, (InlineArgument) argument, args);
                    }

                    Object converted;
                    try {
                        converted = runHandlers(type, value);
                    } catch (HandlerException e) {
                        e.printStackTrace();
                        continue;
                    }

                    if (!runValidators(argument, converted)) {
                        return;
                    }

                    castedArgs.add(converted);
                }

                try {
                    command.getCommandMethod().setAccessible(true);
                    command.getCommandMethod()
                        .invoke(registeredCommand.getOwner(), castedArgs.toArray());
                    return;
                } catch (IllegalAccessException e) {
                    System.err.println(
                        "Could not access command method " + command.getCommandMethod().getName()
                            + ". Is it visible?");
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    System.err.println("An error occurred while performing this command.");
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Split the arguments from the commands in a command input string.
     *
     * @param parts            The parts of the command input string, split by spaces.
     * @param lastCommandIndex The last index that a command is present.
     * @return A string array containing the arguments.
     */
    private String[] getArguments(String[] parts, int lastCommandIndex) {
        // Let's put the arguments in another array
        // This array is initialized to the expected length minus the last command index.
        // TODO Do we need to add one here? Why?
        String[] args = new String[parts.length - (lastCommandIndex + 1)];

        // If there are no arguments, there's really no point in doing this.
        if (args.length > 0) {

            // We'll copy over the arguments from the parts array[lastCommandIndex + 1] to the
            // arguments array from [0].
            System.arraycopy(parts, lastCommandIndex + 1, args, 0, args.length);

        }

        return args;
    }

    /**
     * Process an inline argument.
     *
     * @return The inline argument's value.
     */
    private String processInlineArgument(Class<?> type, InlineArgument argument, String[] args) {
        return args[argument.getIndex()];
    }

    /**
     * Looks for the appropriate handlers for an argument and runs it on a value.
     *
     * @param type  The type of the argument.
     * @param value The argument's value.
     * @return The object, casted to the correct type.
     * @throws HandlerException If the handler failed to convert due to an incorrect type.
     */
    private Object runHandlers(Class<?> type, String value) throws HandlerException {
        Optional<Handler> handler = getHandlerForType(type);
        if (!handler.isPresent()) {
            System.err.println(
                "No handler exists for type " + type.getName() + ". You should register one.");
            return null;
        }

        return handler.get().handle(value);
    }

    private boolean runValidators(Argument argument, Object converted) {
        String[] validatorQueries = argument.getValidators();
        for (String query : validatorQueries) {
            String[] parts = query.split(" ");
            String name = parts[0];
            if (name.equals("")) {
                continue;
            }
            String value = parts[1];

            Optional<Validator> validator = getValidatorByName(name);
            if (!validator.isPresent()) {
                System.err.println("No validator exists by the name " + name);
                continue;
            }

            try {
                validator.get().validate(converted, value);
            } catch (ValidatorException e) {
                System.err.println("Argument [" + argument.getName() + "] " + e.getMessage());
                return false;
            }

        }

        return true;
    }

    private boolean classArrayContains(Class<?> needle, Class[] haystack) {
        for (Class hay : haystack) {
            if (needle.getName().equals(hay.getName())) {
                return true;
            }
        }
        return false;
    }

    public Optional<RegisteredCommand> getCommandByName(String name) {
        for (RegisteredCommand command : commands) {
            if (command.getCommand().getName().equals(name)) {
                return Optional.ofNullable(command);
            }
        }
        return Optional.empty();
    }

}
