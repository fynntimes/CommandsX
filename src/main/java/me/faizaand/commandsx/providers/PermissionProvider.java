package me.faizaand.commandsx.providers;

import java.util.UUID;

/**
 * A user is any entity which may run commands.
 *
 * @since 1.0
 */
public interface PermissionProvider {

    /**
     * Checks whether a user has a certain permission.
     *
     * @param user       The {@link UUID} of the user to perform the check on.
     * @param permission The permission to check.
     * @return true if the user has the permission, false otherwise.
     */
    boolean hasPermission(UUID user, String permission);

}
