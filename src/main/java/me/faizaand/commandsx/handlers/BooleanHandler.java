package me.faizaand.commandsx.handlers;

/**
 * A handler that handles booleans.
 *
 * @since 1.0
 */
public class BooleanHandler implements Handler<Boolean> {

    @Override public Boolean handle(String input) throws HandlerException {
        return Boolean.parseBoolean(input);
    }

}
