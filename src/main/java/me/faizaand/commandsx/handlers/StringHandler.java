package me.faizaand.commandsx.handlers;

/**
 * A handler that handles Strings.
 *
 * @since 1.0
 */
public class StringHandler implements Handler<String> {

    // That's pretty straightforward.
    @Override public String handle(String input) throws HandlerException {
        return input;
    }

}
