package me.faizaand.commandsx;

import java.lang.annotation.*;

/**
 * Annotates the argument that represents the command's sender.
 *
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface Sender {
}
