package me.faizaand.commandsx.validators;

/**
 * A validator puts restraints on an argument to ensure that it's acceptable.
 * For example, a validator may put a limit on how low a number can be.
 *
 * @since 1.0
 */
public interface Validator {

    /**
     * Performs the validation on the user's input.
     * The input object is already of the type of the parameter in the command, since the
     * {@link me.faizaand.commandsx.handlers.Handler} runs first. You just have to cast it.
     *
     * @param input The input object. Cast this to whatever you're validating.
     * @param value The value that was set in the validator query.
     * @return true if the validation succeeds, and false otherwise.
     * @throws ValidatorException If the argument fails validation.
     */
    boolean validate(Object input, String value) throws ValidatorException;

    /**
     * Returns the types that this validator may run on.
     * If the parameter is not one of these types, the validator will be ignored and a
     * warning will be printed.
     *
     * @return An array containing the types that the validator may run on.
     */
    Class[] getTypes();

}
