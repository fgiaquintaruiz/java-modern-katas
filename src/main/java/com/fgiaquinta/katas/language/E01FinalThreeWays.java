package com.fgiaquinta.katas.language;

import java.util.List;

/**
 * Exercise E01 — {@code final} three ways.
 *
 * <h2>Statement</h2>
 * Design {@link Holder} and implement {@link #hold}: it returns a holder that keeps the given list, as it
 * is and not wrapped, in a field named {@code registrations}. The holder class cannot be extended, and it
 * declares a method {@code String describe()} that cannot be overridden.
 *
 * <h2>Concepts</h2>
 * {@code final} on a class, on a method and on a field; a final field freezes the reference, not the contents of the object it points to; {@code Modifier.isFinal} through reflection.
 *
 * <h2>Internals</h2>
 * TODO: fill after solving.
 * <h2>Anchor</h2>
 * TODO: your own image, written by you.
 */
public final class E01FinalThreeWays {

    private E01FinalThreeWays() {
    }

    /** Stub: the shape (fields, modifiers, methods) is yours to design. */
    public static class Holder {
    }

    public static Holder hold(List<String> registrations) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
