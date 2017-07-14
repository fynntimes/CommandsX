package me.faizaand.commandsx.handlers.number;

import me.faizaand.commandsx.handlers.Handler;
import me.faizaand.commandsx.handlers.HandlerException;

/**
 * A handler which handles doubles.
 *
 * @since 1.0
 */
public class DoubleHandler implements Handler<Double> {

    @Override public Double handle(String input) throws HandlerException {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new HandlerException("@lang[not_number]", input);
        }
    }

}
