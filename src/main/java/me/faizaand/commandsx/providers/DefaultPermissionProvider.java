package me.faizaand.commandsx.providers;

import java.util.UUID;

/**
 * A default permission provider, which just allows anyone to do anything.
 *
 * @since 1.0
 */
public class DefaultPermissionProvider implements PermissionProvider {

    @Override public boolean hasPermission(UUID user, String permission) {
        return true;
    }

}
