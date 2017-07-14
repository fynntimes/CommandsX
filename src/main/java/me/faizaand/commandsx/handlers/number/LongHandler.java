package me.faizaand.commandsx.handlers.number;

import me.faizaand.commandsx.handlers.Handler;
import me.faizaand.commandsx.handlers.HandlerException;

/**
 * A handler which handles longs.
 *
 * @since 1.0
 */
public class LongHandler implements Handler<Long> {

    @Override public Long handle(String input) throws HandlerException {
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new HandlerException("@lang[not_number]", input);
        }
    }

}
