package me.faizaand.commandsx;

import java.lang.annotation.*;

/**
 * Annotates an inline argument.
 *
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME) @Target(ElementType.PARAMETER) @Documented
public @interface InlineArg {

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
     * The validator queries for this argument. The format should be <code>[validator=value][validator=value]...</code>.
     * You may see the {@link me.faizaand.commandsx.validators} package for more information.
     *
     * @return The validator queries for this argument.
     */
    String[] validators() default "";

}
