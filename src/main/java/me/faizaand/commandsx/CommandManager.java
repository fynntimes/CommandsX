package me.faizaand.commandsx;

import me.faizaand.commandsx.handlers.Handler;
import me.faizaand.commandsx.parsing.CommandParser;
import me.faizaand.commandsx.providers.*;
import me.faizaand.commandsx.validators.Validator;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Manages the registration and execution of CommandsX commands.
 *
 * @since 1.0
 */
public class CommandManager {

    private BidiMap<Class<?>, Handler> handlerMap = new DualHashBidiMap<>();
    private HashMap<String, Validator> validatorMap = new HashMap<>();
    private CommandParser parser = new CommandParser();

    private FormatProvider formatProvider = new DefaultFormatProvider();
    private LangProvider langProvider = new DefaultLangProvider();
    private PermissionProvider permissionProvider = new DefaultPermissionProvider();

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
        List<ParsedCommand> commands = parser.parse(obj);
        // TODO Put these somewhere
    }

}
