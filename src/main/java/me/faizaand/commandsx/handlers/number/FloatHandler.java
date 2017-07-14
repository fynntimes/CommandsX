package me.faizaand.commandsx.handlers.number;

import me.faizaand.commandsx.handlers.Handler;
import me.faizaand.commandsx.handlers.HandlerException;

/**
 * A handler which handles floats.
 *
 * @since 1.0
 */
public class FloatHandler implements Handler<Float> {

    @Override public Float handle(String input) throws HandlerException {
        try {
            return Float.parseFloat(input);
        } catch (NumberFormatException e) {
            throw new HandlerException("@lang[not_number]", input);
        }
    }

}
