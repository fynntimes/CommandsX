package me.faizaand.commandsx;

import java.lang.annotation.*;

/**
 * Annotates an argument for a command method.
 *
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface Arg {

    /**
     * The name of this argument, which should be concise yet descriptive. It should not contain spaces.
     *
     * @return The name of this argument.
     */
    String name();

    /**
     * The description of this argument. It is recommended that it does not exceed a sentence.
     * This is only showed in help screens.
     *
     * @return The description of this argument.
     */
    String desc() default "";

    /**
     * The default value for this argument, in String form. If the user does not provide this argument,
     * it will be parsed as if this default value was entered by the user.
     * <p>
     * Arguments are considered to be required unless a default value is provided.
     *
     * @return The default value for this argument.
     */
    String def() default "";

    /**
     * The validator queries for this argument. The format should be <code>[validator=value][validator=value]...</code>.
     * You may see the {@link me.faizaand.commandsx.validators} package for more information.
     *
     * @return The validator queries for this argument.
     */
    String[] validators() default "";

}
