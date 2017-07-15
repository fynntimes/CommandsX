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
    private boolean inline;

    public Argument(String name, String description, String defaultValue, String[] validators,
        boolean inline) {
        this.name = name;
        this.description = description;
        this.defaultValue = defaultValue;
        this.validators = validators;
        this.inline = inline;
    }

    public Argument(String name, String description, String defaultValue, String[] validators) {
        this.name = name;
        this.description = description;
        this.defaultValue = defaultValue;
        this.validators = validators;
        this.inline = false;
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

    public boolean isInline() {
        return inline;
    }

    public void setInline(boolean inline) {
        this.inline = inline;
    }

}
