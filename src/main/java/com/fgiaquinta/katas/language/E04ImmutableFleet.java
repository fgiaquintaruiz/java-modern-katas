package com.fgiaquinta.katas.language;

import com.fgiaquinta.katas.domain.Aircraft;

import java.util.List;

/**
 * Exercise E04 — Immutable class by hand.
 *
 * <h2>Statement</h2>
 * Make {@link Fleet} immutable without using a record, then implement {@link #create}. Changing the list
 * passed to the constructor, or the list returned by {@code aircraft()}, must never change the fleet.
 *
 * <h2>Concepts</h2>
 * Final class, private final fields, no setters, defensive copy on the way in and on the way out ({@code List.copyOf}); why a final field alone is not immutability.
 *
 * <h2>Internals</h2>
 * TODO: fill after solving.
 * <h2>Anchor</h2>
 * TODO: your own image, written by you.
 */
public final class E04ImmutableFleet {

    private E04ImmutableFleet() {
    }

    /** Stub: the API is fixed by the test, the fields and the modifiers are yours. */
    public static class Fleet {
        public Fleet(String owner, List<Aircraft> aircraft) {
            throw new UnsupportedOperationException("Not solved yet");
        }

        public String owner() {
            throw new UnsupportedOperationException("Not solved yet");
        }

        public List<Aircraft> aircraft() {
            throw new UnsupportedOperationException("Not solved yet");
        }
    }

    public static Fleet create(String owner, List<Aircraft> aircraft) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
