package com.fgiaquinta.katas.realtime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests are written by the learner during the timed exercise.
 */
class LruCacheTest {


    @Test
    @DisplayName("evicts the first key when a third key is put into a cache of capacity two")
    void put_beyondCapacityTwo_evictsFirstKey() {
        LruCache<String, String> lruCache = new LruCache<>(2);
        lruCache.put("key1", "value1");
        lruCache.put("key2", "value2");

        lruCache.put("key3", "value3");

        assertThat(lruCache.get("key1")).isNull();
        assertThat(lruCache.get("key2")).isNotNull();
        assertThat(lruCache.get("key3")).isNotNull();
    }

    @Test
    @DisplayName("keeps the key that was read and evicts the other one on the third put")
    void get_beforeThirdPut_keepsReadKeyAndEvictsOther() {
        LruCache<String, String> lruCache = new LruCache<>(2);
        lruCache.put("key1", "value1");
        lruCache.put("key2", "value2");
        lruCache.get("key1");

        lruCache.put("key3", "value3");

        assertThat(lruCache.get("key2")).isNull();
        assertThat(lruCache.get("key1")).isNotNull();
        assertThat(lruCache.get("key3")).isNotNull();
    }

    @Test
    @DisplayName("updates the value of an existing key without evicting anything")
    void put_existingKey_updatesValueWithoutEviction() {
        LruCache<String, String> lruCache = new LruCache<>(2);
        lruCache.put("key1", "value1");
        lruCache.put("key2", "value2");

        lruCache.put("key2", "valueXXX");

        assertThat(lruCache.get("key1")).isNotNull();
        assertThat(lruCache.get("key2")).isEqualTo("valueXXX");
    }
}
