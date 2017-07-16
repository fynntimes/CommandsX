package me.faizaand.commandsx.providers;

/**
 * A default format provider, which does no formatting.
 *
 * @since 1.0
 */
public class DefaultFormatProvider implements FormatProvider {

    @Override public String format(String source) {
        return source;
    }

}
