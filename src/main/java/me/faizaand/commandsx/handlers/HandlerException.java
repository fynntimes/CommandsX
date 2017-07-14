package me.faizaand.commandsx.handlers;

/**
 * Signals that an error occurred while handling an argument. This
 * could be due to the input being malformed.
 *
 * @since 1.0
 */
public class HandlerException extends Exception {

    private String input;

    /**
     * Create a handler exception.
     *
     * @param message The message to show the user. This should preferably be in @lang[key] format for localization support.
     * @param input The input from the argument.
     */
    public HandlerException(String message, String input) {
        super(message);
        this.input = input;
    }

    public String getInput() {
        return input;
    }

}
