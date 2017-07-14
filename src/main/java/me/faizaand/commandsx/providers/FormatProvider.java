package me.faizaand.commandsx.providers;

/**
 * Provides a method for formatting text with colors.
 *
 * @since 1.0
 */
public interface FormatProvider {

    /**
     * Format a string's colors.
     *
     * @param source The unformatted source string.
     * @return The formatted string.
     */
    String format(String source);

}
