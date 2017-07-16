package me.faizaand.commandsx;

/**
 * Represents a parsed inline argument.
 *
 * @since 1.0
 */
public class InlineArgument extends Argument {

    private String name;
    private String description;
    private String[] validators;
    private int index;

    public InlineArgument(String name, String description,
        String[] validators) {
        super(name, description, "", validators);
        this.name = name;
        this.description = description;
        this.validators = validators;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getValidators() {
        return validators;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
