package me.faizaand.commandsx.handlers.number;

import me.faizaand.commandsx.handlers.Handler;
import me.faizaand.commandsx.handlers.HandlerException;

/**
 * A handler which handles shorts.
 *
 * @since 1.0
 */
public class ShortHandler implements Handler<Short> {

    @Override public Short handle(String input) throws HandlerException {
        try {
            return Short.parseShort(input);
        } catch (NumberFormatException e) {
            throw new HandlerException("@lang[not_number]", input);
        }
    }

}
