package me.faizaand.commandsx;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Maps parameter types to arguments.
 *
 * @since 1.0
 */
public class ArgumentMap {

    private LinkedHashMap<Class<?>, Argument> argumentMap;

    /**
     * Initialize a blank argument map.
     */
    public ArgumentMap() {
        this.argumentMap = new LinkedHashMap<>();
    }

    /**
     * Initialize an argument map with a previous set of arguments.
     *
     * @param argumentMap The pre-defined argument map.
     */
    public ArgumentMap(LinkedHashMap<Class<?>, Argument> argumentMap) {
        this.argumentMap = argumentMap;
    }

    /**
     * Adds an argument to this argument map.
     *
     * @param type The type of this argument parameter. It is important that this is correct,
     *             because later on, this argument will be injected into the command method as this type.
     * @param arg  The parsed {@link Argument}.
     */
    public void addArgument(Class<?> type, Argument arg) {
        this.argumentMap.put(type, arg);
    }

    /**
     * Returns the {@link Argument} that matches the type specified.
     *
     * @param type The type of the argument to get.
     * @return An optional containing the {@link Argument}. If there is no argument by that type,
     * this will be an empty Optional.
     */
    public Optional<Argument> getArgumentOfType(Class<?> type) {
        return Optional.ofNullable(argumentMap.getOrDefault(type, null));
    }

    public Optional<Argument> getArgumentOfName(String name) {
        return argumentMap.values().stream().filter(argument -> argument.getName().equals(name))
            .findFirst();
    }

    /**
     * Returns the type of a certain argument.
     *
     * @param argName The argument's name. This is case sensitive, and should be as defined in {@link Arg#name()}.
     * @return An optional containing the type. If there was no argument by that name, this will be an empty optional.
     */
    public Optional<Class<?>> getTypeOfArgument(String argName) {
        Set<Map.Entry<Class<?>, Argument>> entries = argumentMap.entrySet();

        Optional<Map.Entry<Class<?>, Argument>> entryOptional = entries.stream()
            .filter(classArgumentEntry -> classArgumentEntry.getValue().getName().equals(argName))
            .findFirst();

        return entryOptional
            .flatMap(classArgumentEntry -> Optional.ofNullable(classArgumentEntry.getKey()));
    }

    public int getMinimumArgs() {
        int min = 0;
        for (Argument argument : argumentMap.values()) {
            if (!argument.getDefaultValue().equals("")) {
                min++;
            }
        }

        return min;
    }

    public int getMaximumArgs() {
        return argumentMap.size();
    }

    public LinkedHashMap<Class<?>, Argument> getMap() {
        return argumentMap;
    }

    @Override public String toString() {
        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<Class<?>, Argument>> entries = argumentMap.entrySet();
        for (Map.Entry<Class<?>, Argument> entry : entries) {
            builder.append(entry.getKey()).append(" -> ").append(entry.getValue().getName())
                .append(" (").append(entry.getValue().getDescription()).append(")");
            if (entry.getValue() instanceof InlineArgument) {
                builder.append(" |INLINE|");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
