package me.faizaand.commandsx.handlers.number;

import me.faizaand.commandsx.handlers.Handler;
import me.faizaand.commandsx.handlers.HandlerException;

/**
 * A handler which handles bytes.
 *
 * @since 1.0
 */
public class ByteHandler implements Handler<Byte> {

    @Override public Byte handle(String input) throws HandlerException {
        try {
            return Byte.parseByte(input);
        } catch (NumberFormatException e) {
            throw new HandlerException("@lang[not_number]", input);
        }
    }

}
