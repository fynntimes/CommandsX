package me.faizaand.commandsx.validators;

/**
 * Signals that an argument failed validation.
 *
 * @since 1.0
 */
public class ValidatorException extends Exception {

    /**
     * Creates a new ValidationException.
     *
     * @param message The reason why the validation failed. This string will be appended to
     *                a message reading "The argument [arg_name] ...", so you should format it accordingly.
     *                For example, your message should be "must be greater than 1."
     */
    public ValidatorException(String message) {
        super(message);
    }

}
