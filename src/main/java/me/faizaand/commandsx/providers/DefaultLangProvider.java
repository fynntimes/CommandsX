package me.faizaand.commandsx.providers;

import java.util.HashMap;
import java.util.Map;

/**
 * The default language string provider. You can change the output for the command
 * library by implementing your own {@link LangProvider}.
 *
 * @since 1.0
 */
public class DefaultLangProvider implements LangProvider {

    private Map<String, String> languageStrings = new HashMap<>();

    public DefaultLangProvider() {
        // TODO
    }

    @Override public String getLanguageString(String name) {
        return null;
    }

}
