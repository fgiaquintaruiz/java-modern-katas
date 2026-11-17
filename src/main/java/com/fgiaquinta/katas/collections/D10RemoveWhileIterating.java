package com.fgiaquinta.katas.collections;

import com.fgiaquinta.katas.domain.Charter;

import java.util.List;

/**
 * Exercise D10 — Remove while iterating.
 *
 * <h2>Statement</h2>
 * Remove from the given mutable list every charter lasting fewer than {@code minDays} days, in place,
 * and return the same list instance. Charters lasting exactly {@code minDays} stay, and the order of
 * the survivors does not change.
 *
 * <h2>Concepts</h2>
 * Fail-fast iterators and {@code modCount}; {@code ConcurrentModificationException}; {@code Iterator.remove} versus {@code Collection.removeIf}; why removing the second-to-last element inside a for-each slips through.
 *
 * <h2>Internals</h2>
 * TODO: fill after solving.
 * <h2>Anchor</h2>
 * TODO: your own image, written by you.
 */
public final class D10RemoveWhileIterating {

    private D10RemoveWhileIterating() {
    }

    public static List<Charter> removeShorterThan(List<Charter> charters, long minDays) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
