package me.faizaand.commandsx.validators;

/**
 * Validates that a Number's value does not exceed the specified value.
 *
 * @since 1.0
 */
public class MaxValidator implements Validator {

    @Override public boolean validate(Object input, String value) throws ValidatorException {
        Number numberInput = (Number) input;

        // Since a double can store the most out of all the number types,
        // and since all the number types can be compared with a double properly,
        // we'll use it here.
        double numberDouble = numberInput.doubleValue();
        double valueDouble = Double.parseDouble(value);

        boolean passed = numberDouble <= valueDouble;
        if (!passed) {
            throw new ValidatorException("must be less than " + value);
        }

        return true;
    }

    @Override public Class[] getTypes() {
        return new Class[] {Number.class};
    }

}
