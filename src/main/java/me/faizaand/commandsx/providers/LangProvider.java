package me.faizaand.commandsx.providers;

/**
 * Provides the language strings used for @lang queries.
 *
 * @since 1.0
 */
public interface LangProvider {

    /**
     * Returns the language string for a certain identifier.
     *
     * @param name The name (identifier) of the language string.
     * @return The language string.
     */
    String getLanguageString(String name);

}
