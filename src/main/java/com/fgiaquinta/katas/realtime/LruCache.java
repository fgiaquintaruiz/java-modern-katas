package com.fgiaquinta.katas.realtime;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * <h2>Statement</h2>
 * Implement a generic cache with a fixed capacity. {@code get} returns the value for the key or
 * {@code null}, and marks the key as recently used. {@code put} inserts or updates a key and evicts
 * the least recently used key when the capacity is exceeded. Both operations must be O(1). Bonus:
 * write three JUnit 5 tests covering capacity 2, correct eviction, and updating an existing key.
 *
 * <h2>Concepts</h2>
 * Hash lookup plus recency ordering.
 *
 * <h2>Internals</h2>
 *  1. Only the before and after links of the key1 entry change, so it goes to the most recent position; the hash bucket stays put
 *  2. Adds the key3 to the list, moves that key to the bottom which is the recently used and then executes the removeEldestEntry which ends up removing the key2 which was in the
 *      least recent position
 *  3. It receives key2 because the put first adds the new element so the size of the list becomes 3 which is greater than the capacity 2 so key2 is removed
 * <h2>Anchor</h2>
 *  Like when you are in a queue without numbers and you ask the people before you who is last, then a clerk comes and
 *  tells you that the person before you is now someone else.
 *
 * <h2>Log</h2>
 * {@code docs/log/realtime-lru-cache.md}
 *
 * @param <K> key type
 * @param <V> value type
 */
public final class LruCache<K, V> {

    private final LinkedHashMap<K, V> cache;

    public LruCache(int capacity) {
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    public V get(K key) {
        return cache.get(key);
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }
}
