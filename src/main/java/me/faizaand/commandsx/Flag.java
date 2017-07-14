package me.faizaand.commandsx;

/**
 * Annotates a flag argument, which is a boolean that's true if the user types
 * the flag, and false if they don't. As a result, these are always optional.
 * <p>
 * As of now, flags do not have argument support. Instead, you should use an optional argument
 * by setting the default value in the {@link Arg} annotation.
 *
 * @since 1.0
 */
public @interface Flag {

    /**
     * The character name of this flag. For example, if you enter 'f',
     * the user will use this flag by typing '-f'.
     *
     * @return The name of the flag.
     */
    char name();

    /**
     * The description of this flag. This is shown in help menus.
     *
     * @return The description of this flag.
     */
    String desc();

}
