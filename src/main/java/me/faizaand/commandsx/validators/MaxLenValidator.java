package me.faizaand.commandsx.validators;

/**
 * Validates that a String's length does not exceed the specified value.
 *
 * @since 1.0
 */
public class MaxLenValidator implements Validator {
    @Override public boolean validate(Object input, String value) throws ValidatorException {
        int valueInt = Integer.parseInt(value);

        boolean passed = ((String) input).length() <= valueInt;
        if (!passed) {
            throw new ValidatorException("length must be less than " + value);
        }
        return true;
    }

    @Override public Class[] getTypes() {
        return new Class[] {String.class};
    }
}
