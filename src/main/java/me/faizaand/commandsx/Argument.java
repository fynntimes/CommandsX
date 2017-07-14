package me.faizaand.commandsx;

/**
 * Represents a parsed argument.
 *
 * @since 1.0
 */
public class Argument {

    private String name;
    private String description;
    private String defaultValue;
    private String[] validators;

    public Argument(String name, String description, String defaultValue, String[] validators) {
        this.name = name;
        this.description = description;
        this.defaultValue = defaultValue;
        this.validators = validators;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String[] getValidators() {
        return validators;
    }

}
