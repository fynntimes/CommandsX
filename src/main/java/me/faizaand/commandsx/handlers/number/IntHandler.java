package me.faizaand.commandsx.handlers.number;

import me.faizaand.commandsx.handlers.Handler;
import me.faizaand.commandsx.handlers.HandlerException;

/**
 * A handler which handles ints.
 *
 * @since 1.0
 */
public class IntHandler implements Handler<Integer> {

    @Override public Integer handle(String input) throws HandlerException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new HandlerException("@lang[not_number]", input);
        }
    }

}
