package me.faizaand.commandsx.util;

/**
 * Represents an object which contains a cache that can be invalidated.
 * @since 1.0
 */
public interface Cacheable {

    /**
     * Invalidate, or clear out, the cache.
     */
    void invalidateCache();

}
