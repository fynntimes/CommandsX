package me.faizaand.commandsx.handlers;

/**
 * A handler takes an argument of a certain type and converts it to the type it's assigned using its String value.
 *
 * @since 1.0
 */
public interface Handler<T> {

    /**
     * Converts an input string into the target object.
     *
     * @param input The input string. This is never null.
     * @return The target object.
     * @throws HandlerException if the argument input is malformed.
     */
    T handle(String input) throws HandlerException;

}
