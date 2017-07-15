package me.faizaand.commandsx;

import me.faizaand.commandsx.providers.PermissionProvider;

import java.lang.annotation.*;

/**
 * Annotates a method which represents the logic for a command.
 *
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME) @Target(ElementType.METHOD) @Documented
public @interface Command {

    /**
     * The identifier for this command, which specifies the chain of commands that is used to trigger it.
     * <p>
     * For example, setting the identifier to <code>mine reset</code> denotes that this method will
     * be called when the user runs the "reset" sub-command of the "mine" root command. You may have as
     * many sub-commands as you want, and you can even have sub-sub-sub-sub-commands if you so desire, though
     * this is confusing and not advisable.
     * <p>
     * You should also define your inline arguments here. Inline arguments are normally used when creating a
     * commands that deals with the properties of an object. As an example, suppose you've created a plugin that
     * resets block mines. You would want a command that allows the user to easily access a specific mine. An
     * example identifier for this would be <code>mine {mine} reset</code>. Notice that the inline argument's name
     * is placed between {curly brackets} which denotes an inline argument. To access its value, you must add a parameter
     * to your method that is annotated by {@link Arg}, and then give it the same name as what you put inside the brackets.
     *
     * @return The aliases for the command.
     */
    String identifier();

    /**
     * A list of permissions for the command. The user will be allowed to run the command if it
     * has any of these permissions; it does not have to have all of them.
     * <p>
     * Note that if you do not have a {@link PermissionProvider} registered with the library, this
     * property will have no effect.
     *
     * @return The permissions for the command.
     */
    String[] permissions() default "";

    /**
     * A short description of the command, telling the user what it does in short terms.
     * You can define more descriptive help by setting the {@link #longDesc()}.
     *
     * @return A short description of the command.
     */
    String desc();

    /**
     * A long description of the command, telling the user more about what it does and how to use it.
     * If you don't define a long description, CommandsX will just use your {@link #desc()}.
     *
     * @return A long description of the command.
     */
    String longDesc() default "";

}
