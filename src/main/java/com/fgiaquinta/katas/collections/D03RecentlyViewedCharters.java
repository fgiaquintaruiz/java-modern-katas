package com.fgiaquinta.katas.collections;

import com.fgiaquinta.katas.domain.Charter;

import java.util.List;

/**
 * Exercise D03 — Recently viewed charters.
 *
 * <h2>Statement</h2>
 * A screen remembers the last {@code capacity} distinct charters a user viewed. Given the sequence of
 * views, return the charter ids from least to most recently viewed. Viewing a charter again makes it
 * the most recent; when the capacity is exceeded the least recently viewed one is dropped.
 *
 * <h2>Concepts</h2>
 * {@code LinkedHashMap} three-argument constructor with {@code accessOrder = true}; overriding {@code removeEldestEntry}; both {@code get} and {@code put} count as an access.
 *
 * <h2>Internals</h2>
 * TODO: fill after solving.
 * <h2>Anchor</h2>
 * TODO: your own image, written by you.
 */
public final class D03RecentlyViewedCharters {

    private D03RecentlyViewedCharters() {
    }

    public static List<String> viewOrder(int capacity, List<Charter> views) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
