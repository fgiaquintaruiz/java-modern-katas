package com.fgiaquinta.katas.collections;

import java.util.List;

/**
 * Exercise D09 — Views versus copies.
 *
 * <h2>Statement</h2>
 * Given a mutable list of registrations (at least two elements), build five collections from it and
 * return them in a {@link Views}: a read-only wrapper of the list, an independent immutable copy, the
 * view of its first two elements, a fixed-size list over a new array holding the same elements, and
 * that array. Which ones follow later changes of the source is what the tests check.
 *
 * <h2>Concepts</h2>
 * {@code Collections.unmodifiableList} (view), {@code List.copyOf} (snapshot), {@code List.subList} (view, breaks on structural change of the source), {@code Arrays.asList} (fixed size, write-through to the array).
 *
 * <h2>Internals</h2>
 * TODO: fill after solving.
 * <h2>Anchor</h2>
 * TODO: your own image, written by you.
 */
public final class D09ViewsVersusCopies {

    private D09ViewsVersusCopies() {
    }

    public record Views(List<String> unmodifiableView,
                        List<String> copy,
                        List<String> firstTwoView,
                        List<String> asListOfArray,
                        String[] backingArray) {
    }

    public static Views build(List<String> source) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
